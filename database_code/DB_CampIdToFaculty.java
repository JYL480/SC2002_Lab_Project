import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DB_CampIdToFaculty {
    private static final String FILE_PATH = DatabaseFilePaths.CAMP_ID_TO_FACULTY;

    public static void createMapping(String campId, String faculty) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            // Find the last row number
            int lastRowNum = sheet.getLastRowNum();

            // Create a new row
            Row newRow = sheet.createRow(lastRowNum + 1);

            // Write mapping data to the cells
            Cell campIdCell = newRow.createCell(0);
            campIdCell.setCellValue(campId);

            Cell facultyCell = newRow.createCell(1);
            facultyCell.setCellValue(faculty);

            // Save the changes to the file
            try (FileOutputStream fileOut = new FileOutputStream(FILE_PATH)) {
                workbook.write(fileOut);
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }
    }

    public static ArrayList<String> getFaculties(String campId) {
        ArrayList<String> faculties = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String id = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (campId.equals(id)) {
                    // Found a mapping for the campId
                    faculties.add(row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return faculties;
    }

    public static ArrayList<String> getCampIds(String faculty) {
        ArrayList<String> campIds = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String id = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (faculty.equals(id)) {
                    // Found a mapping for the faculty
                    campIds.add(row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return campIds;
    }

    public static void updateMapping(String campId, String newFaculty) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String id = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (campId.equals(id)) {
                    // Update the mapping data in the row
                    row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(newFaculty);

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

    public static void deleteMapping(String campId) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String id = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (campId.equals(id)) {
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

    public static ArrayList<String> getAllCampIds() {
        ArrayList<String> campIds = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String id = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                campIds.add(id);
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return campIds;
    }

    public static ArrayList<String> getAllFaculties() {
        ArrayList<String> faculties = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String id = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                faculties.add(id);
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return faculties;
    }
}
