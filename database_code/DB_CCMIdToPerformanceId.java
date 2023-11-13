import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DB_CCMIdToPerformanceId {
    private static final String FILE_PATH = DatabaseFilePaths.CCM_ID_TO_PERFORMANCE_ID;

    public static void createMapping(String ccmId, String performanceId) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            // Find the last row number
            int lastRowNum = sheet.getLastRowNum();

            // Create a new row
            Row newRow = sheet.createRow(lastRowNum + 1);

            // Write mapping data to the cells
            Cell ccmIdCell = newRow.createCell(0);
            ccmIdCell.setCellValue(ccmId);

            Cell performanceIdCell = newRow.createCell(1);
            performanceIdCell.setCellValue(performanceId);

            // Save the changes to the file
            try (FileOutputStream fileOut = new FileOutputStream(FILE_PATH)) {
                workbook.write(fileOut);
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }
    }

    public static ArrayList<String> getPerformanceIds(String ccmId) {
        ArrayList<String> performanceIds = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String id = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (ccmId.equals(id)) {
                    // Found a mapping for the ccmId
                    performanceIds.add(row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return performanceIds;
    }

    public static ArrayList<String> getCCMIds(String performanceId) {
        ArrayList<String> ccmIds = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String id = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (performanceId.equals(id)) {
                    // Found a mapping for the performanceId
                    ccmIds.add(row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return ccmIds;
    }

    public static void updateMapping(String ccmId, String newPerformanceId) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String id = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (ccmId.equals(id)) {
                    // Update the mapping data in the row
                    row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(newPerformanceId);

                    // Save the changes to the file
                    try (FileOutputStream fileOut = new FileOutputStream(FILE_PATH)) {
                        workbook.write(fileOut);
                    }

                    return; // Mapping updated
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        // Mapping not found
    }

    public static void deleteMapping(String ccmId) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String id = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (ccmId.equals(id)) {
                    // Remove the row
                    iterator.remove();

                    // Save the changes to the file
                    try (FileOutputStream fileOut = new FileOutputStream(FILE_PATH)) {
                        workbook.write(fileOut);
                    }

                    return; // Mapping deleted
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        // Mapping not found
    }

    public static ArrayList<String> getAllCCMIds() {
        ArrayList<String> ccmIds = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String id = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                ccmIds.add(id);
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return ccmIds;
    }

    public static ArrayList<String> getAllPerformanceIds() {
        ArrayList<String> performanceIds = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String id = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                performanceIds.add(id);
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return performanceIds;
    }
}
