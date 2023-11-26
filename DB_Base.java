import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class DB_Base<T> {
    protected String filePath;

    public DB_Base(String filePath) {
        this.filePath = filePath;
    }

    protected abstract T createEntity(Row row);

    protected abstract void writeEntityToRow(Row row, T entity);

    public void create(T entity) {
        try (FileInputStream file = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            Row newRow = sheet.createRow(lastRowNum + 1);

            writeEntityToRow(newRow, entity);

            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }
    }

    public T read(String entityId, int idColumnIndex) {
        try (FileInputStream file = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {
                Row row = iterator.next();
                String id = row.getCell(idColumnIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (entityId.equals(id)) {
                    return createEntity(row);
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return null;
    }

    public void update(String entityId, int idColumnIndex, T updatedEntity) {
        try (FileInputStream file = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {
                Row row = iterator.next();
                String id = row.getCell(idColumnIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (entityId.equals(id)) {
                    writeEntityToRow(row, updatedEntity);

                    try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                        workbook.write(fileOut);
                    }

                    return;
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }
    }

    public void delete(String entityId, int idColumnIndex) {
        try (FileInputStream file = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {
                Row row = iterator.next();
                String id = row.getCell(idColumnIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (entityId.equals(id)) {
                    sheet.removeRow(row);

                    if (row.getRowNum() < sheet.getLastRowNum()) {
                        sheet.shiftRows(row.getRowNum() + 1, sheet.getLastRowNum(), -1);
                    }

                    try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                        workbook.write(fileOut);
                    }

                    return;
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }
    }

    public ArrayList<T> getAll() {
        ArrayList<T> entities = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {
                Row row = iterator.next();

                if (row.getRowNum() == 0) {
                    continue;
                }

                entities.add(createEntity(row));
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return entities;
    }

    public ArrayList<T> getAllById(String entityId, int idColumnIndex) {
        ArrayList<T> matchingEntities = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {
                Row row = iterator.next();
                String id = row.getCell(idColumnIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (entityId.equals(id)) {
                    matchingEntities.add(createEntity(row));
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return matchingEntities;
    }

    public void deleteIdMapping(String id1, String id2, int id1ColumnIndex, int id2ColumnIndex) {
        try (FileInputStream file = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {
                Row row = iterator.next();
                String value1 = row.getCell(id1ColumnIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                String value2 = row.getCell(id2ColumnIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (id1.equals(value1) && id2.equals(value2)) {
                    sheet.removeRow(row);

                    if (row.getRowNum() < sheet.getLastRowNum()) {
                        sheet.shiftRows(row.getRowNum() + 1, sheet.getLastRowNum(), -1);
                    }

                    try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                        workbook.write(fileOut);
                    }

                    return; // Assuming there's at most one matching row, so we can exit after deletion
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }
    }

    public void deleteMappingsById(String id, int idColumnIndex) {
        try (FileInputStream file = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {
                Row row = iterator.next();
                String value = row.getCell(idColumnIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (id.equals(value)) {
                    sheet.removeRow(row);

                    if (row.getRowNum() < sheet.getLastRowNum()) {
                        sheet.shiftRows(row.getRowNum() + 1, sheet.getLastRowNum(), -1);
                    }
                }
            }

            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }
    }

    public T getMapping(String id1, String id2, int id1ColumnIndex, int id2ColumnIndex) {
        try (FileInputStream file = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {
                Row row = iterator.next();
                String value1 = row.getCell(id1ColumnIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                String value2 = row.getCell(id2ColumnIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (id1.equals(value1) && id2.equals(value2)) {
                    return createEntity(row); // Mapping exists
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return null; // Mapping does not exist
    }

    public static void main(String[] args) {
        String filePath = DatabaseFilePaths.ATTENDEE_ID_TO_CAMP_ID;
         try (FileInputStream file = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = sheet.iterator();
            Row row = iterator.next();
            row.createCell(2).setCellValue("isWithdrawn");
            while (iterator.hasNext()) {
                row = iterator.next();
                row.createCell(2).setCellValue(false);
                }
            } 
        catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

         // Mapping does not exist
    }
}
