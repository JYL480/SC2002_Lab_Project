import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DB_StaffIdToCampId {
    private static final String FILE_PATH = DatabaseFilePaths.STAFF_ID_TO_CAMP_ID;

    public static void createMapping(String staffId, String campId) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            // Find the last row number
            int lastRowNum = sheet.getLastRowNum();

            // Create a new row
            Row newRow = sheet.createRow(lastRowNum + 1);

            // Write mapping data to the cells
            Cell staffIdCell = newRow.createCell(0);
            staffIdCell.setCellValue(staffId);

            Cell campIdCell = newRow.createCell(1);
            campIdCell.setCellValue(campId);

            // Save the changes to the file
            try (FileOutputStream fileOut = new FileOutputStream(FILE_PATH)) {
                workbook.write(fileOut);
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }
    }

    public static ArrayList<String> getCampIds(String staffId) {
        ArrayList<String> campIds = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String currentStaffId = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (staffId.equals(currentStaffId)) {
                    // Matching Staff ID found, add corresponding Camp ID to the list
                    String currentCampId = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                    campIds.add(currentCampId);
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return campIds;
    }

    public static ArrayList<String> getStaffIds(String campId) {
        ArrayList<String> staffIds = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String currentCampId = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (campId.equals(currentCampId)) {
                    // Matching Camp ID found, add corresponding Staff ID to the list
                    String currentStaffId = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                    staffIds.add(currentStaffId);
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return staffIds;
    }

    // Additional methods (create/update/delete) can be added as needed

    public static void deleteMapping(String staffId, String campId) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
                Row row = sheet.getRow(i);

                if (row != null) {
                    String currentStaffId = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue().trim();
                    String currentCampId = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue().trim();

                    if (staffId.trim().equals(currentStaffId) && campId.trim().equals(currentCampId)) {
                        // Remove the row
                        sheet.removeRow(row);

                        // If the row is not the last row, shift the remaining rows up to fill the gap
                        if (i < sheet.getLastRowNum()) {
                            sheet.shiftRows(i + 1, sheet.getLastRowNum(), -1);
                        }

                        // Save the changes to the file
                        try (FileOutputStream fileOut = new FileOutputStream(FILE_PATH)) {
                            workbook.write(fileOut);
                        }

                        // Adjust the row index after deletion
                        i--;

                        return; // Mapping deleted
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        // Mapping not found
    }

    public static boolean isExists(String staffId, String campId) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
                Row row = sheet.getRow(i);

                if (row != null) {
                    String currentStaffId = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue().trim();
                    String currentCampId = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue().trim();

                    if (staffId.trim().equals(currentStaffId) && campId.trim().equals(currentCampId)) {
                        return true; // Mapping exists
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        // Mapping not found
        return false;
    }

    // public static void main(String[] args) {
    //     testDB_StaffIdToCampId();
    // }

    // private static void testDB_StaffIdToCampId() {
    //     // Dummy data
    //     String staffId1 = "staff1";
    //     String campId1 = "camp1";
    //     String staffId2 = "staff2";
    //     String campId2 = "camp2";

    //     // Test createMapping
    //     DB_StaffIdToCampId.createMapping(staffId1, campId1);
    //     DB_StaffIdToCampId.createMapping(staffId2, campId1);  // Creating a second mapping for the same campId

    //     // Test getCampIds
    //     ArrayList<String> campIds1 = DB_StaffIdToCampId.getCampIds(staffId1);
    //     System.out.println("Camp IDs for Staff ID " + staffId1 + ": ");
    //     for (String campId : campIds1) {
    //         System.out.println(campId);
    //     }

    //     ArrayList<String> campIds2 = DB_StaffIdToCampId.getCampIds(staffId2);
    //     System.out.println("Camp IDs for Staff ID " + staffId2 + ": ");
    //     for (String campId : campIds2) {
    //         System.out.println(campId);
    //     }

    //     // Test getStaffIds
    //     ArrayList<String> staffIds1 = DB_StaffIdToCampId.getStaffIds(campId1);
    //     System.out.println("Staff IDs for Camp ID " + campId1 + ": ");
    //     for (String staffId : staffIds1) {
    //         System.out.println(staffId);
    //     }

    //     // Test createMapping for additional mappings
    //     DB_StaffIdToCampId.createMapping(staffId1, campId2);
    //     DB_StaffIdToCampId.createMapping(staffId2, campId2);

    //     ArrayList<String> staffIds2 = DB_StaffIdToCampId.getStaffIds(campId2);
    //     System.out.println("Staff IDs for Camp ID " + campId2 + ": ");
    //     for (String staffId : staffIds2) {
    //         System.out.println(staffId);
    //     }

    //     // Test isExists
    //     boolean exists1 = DB_StaffIdToCampId.isExists(staffId1, campId1);
    //     System.out.println("Mapping exists: " + exists1);

    //     boolean exists2 = DB_StaffIdToCampId.isExists(staffId2, campId1);
    //     System.out.println("Second mapping exists: " + exists2);

    //     // Test deleteMapping
    //     DB_StaffIdToCampId.deleteMapping(staffId1, campId1);

    //     // Test isExists after deletion
    //     exists1 = DB_StaffIdToCampId.isExists(staffId1, campId1);
    //     System.out.println("Mapping exists after deletion: " + exists1);
    // }
}
