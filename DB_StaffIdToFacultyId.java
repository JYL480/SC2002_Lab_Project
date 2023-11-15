import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DB_StaffIdToFacultyId {
    private static final String FILE_PATH = DatabaseFilePaths.STAFF_ID_TO_FACULTY_ID;

    public static void createMapping(String staffId, String facultyId) {
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

            Cell facultyIdCell = newRow.createCell(1);
            facultyIdCell.setCellValue(facultyId);

            // Save the changes to the file
            try (FileOutputStream fileOut = new FileOutputStream(FILE_PATH)) {
                workbook.write(fileOut);
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }
    }

    public static ArrayList<String> getFacultyIds(String staffId) {
        ArrayList<String> facultyIds = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String currentStaffId = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (staffId.equals(currentStaffId)) {
                    // Matching Staff ID found, add corresponding Faculty ID to the list
                    String currentFacultyId = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                    facultyIds.add(currentFacultyId);
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return facultyIds;
    }

    public static ArrayList<String> getStaffIds(String facultyId) {
        ArrayList<String> staffIds = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String currentFacultyId = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (facultyId.equals(currentFacultyId)) {
                    // Matching Faculty ID found, add corresponding Staff ID to the list
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

    public static void deleteMapping(String staffId, String facultyId) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
                Row row = sheet.getRow(i);

                if (row != null) {
                    String currentStaffId = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue().trim();
                    String currentFacultyId = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue().trim();

                    if (staffId.trim().equals(currentStaffId) && facultyId.trim().equals(currentFacultyId)) {
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

    public static boolean isExists(String staffId, String facultyId) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
                Row row = sheet.getRow(i);

                if (row != null) {
                    String currentStaffId = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue().trim();
                    String currentFacultyId = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue().trim();

                    if (staffId.trim().equals(currentStaffId) && facultyId.trim().equals(currentFacultyId)) {
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

    public static void main(String[] args) {
        testDB_StaffIdToFacultyId();
    }

    private static void testDB_StaffIdToFacultyId() {
        // Dummy data
        String staffId1 = "staff1";
        String facultyId1 = "faculty1";
        String staffId2 = "staff2";
        String facultyId2 = "faculty2";

        // Test createMapping
        DB_StaffIdToFacultyId.createMapping(staffId1, facultyId1);
        DB_StaffIdToFacultyId.createMapping(staffId2, facultyId1);  // Creating a second mapping for the same facultyId

        // Test getFacultyIds
        ArrayList<String> facultyIds1 = DB_StaffIdToFacultyId.getFacultyIds(staffId1);
        System.out.println("Faculty IDs for Staff ID " + staffId1 + ": ");
        for (String facultyId : facultyIds1) {
            System.out.println(facultyId);
        }

        ArrayList<String> facultyIds2 = DB_StaffIdToFacultyId.getFacultyIds(staffId2);
        System.out.println("Faculty IDs for Staff ID " + staffId2 + ": ");
        for (String facultyId : facultyIds2) {
            System.out.println(facultyId);
        }

        // Test getStaffIds
        ArrayList<String> staffIds1 = DB_StaffIdToFacultyId.getStaffIds(facultyId1);
        System.out.println("Staff IDs for Faculty ID " + facultyId1 + ": ");
        for (String staffId : staffIds1) {
            System.out.println(staffId);
        }

        // Test createMapping for additional mappings
        DB_StaffIdToFacultyId.createMapping(staffId1, facultyId2);
        DB_StaffIdToFacultyId.createMapping(staffId2, facultyId2);

        ArrayList<String> staffIds2 = DB_StaffIdToFacultyId.getStaffIds(facultyId2);
        System.out.println("Staff IDs for Faculty ID " + facultyId2 + ": ");
        for (String staffId : staffIds2) {
            System.out.println(staffId);
        }

        // Test isExists
        boolean exists1 = DB_StaffIdToFacultyId.isExists(staffId1, facultyId1);
        System.out.println("Mapping exists: " + exists1);

        boolean exists2 = DB_StaffIdToFacultyId.isExists(staffId2, facultyId1);
        System.out.println("Second mapping exists: " + exists2);

        // Test deleteMapping
        DB_StaffIdToFacultyId.deleteMapping(staffId1, facultyId1);

        // Test isExists after deletion
        exists1 = DB_StaffIdToFacultyId.isExists(staffId1, facultyId1);
        System.out.println("Mapping exists after deletion: " + exists1);
    }
}
