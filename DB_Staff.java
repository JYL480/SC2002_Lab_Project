import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DB_Staff {
    private static final String FILE_PATH = DatabaseFilePaths.STAFF;

    public static void createStaff(Staff staff) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            // Find the last row number
            int lastRowNum = sheet.getLastRowNum();

            // Create a new row
            Row newRow = sheet.createRow(lastRowNum + 1);

            // Write staff data to the cells
            Cell idCell = newRow.createCell(0);
            idCell.setCellValue(staff.getId());

            Cell emailCell = newRow.createCell(1);
            emailCell.setCellValue(staff.getEmail());

            Cell nameCell = newRow.createCell(2);
            nameCell.setCellValue(staff.getName());

            Cell passwordCell = newRow.createCell(3);
            passwordCell.setCellValue(staff.getPassword());

            // Save the changes to the file
            try (FileOutputStream fileOut = new FileOutputStream(FILE_PATH)) {
                workbook.write(fileOut);
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }
    }

    public static Staff readStaff(String staffId) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String id = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (staffId.equals(id)) {
                    // Found the staff
                    String email = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                    String name = row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                    String password = row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                    return new Staff(id, password, name, email);
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return null; // Staff not found
    }

    public static void updateStaff(Staff staff) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String id = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (staff.getId().equals(id)) {
                    // Update the staff data in the row
                    row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(staff.getEmail());
                    row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(staff.getName());
                    row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(staff.getPassword());

                    // Save the changes to the file
                    try (FileOutputStream fileOut = new FileOutputStream(FILE_PATH)) {
                        workbook.write(fileOut);
                    }

                    return; // Staff updated
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        // Staff not found
    }

    public static void deleteStaff(String staffId) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
            Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
                Row row = sheet.getRow(i);

                if (row != null) {
                    String id = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue().trim();

                    if (staffId.trim().equals(id)) {
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

                        return; // Staff deleted
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        // Staff not found
    }

    public static ArrayList<Staff> getAllStaff() {
        ArrayList<Staff> staffList = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();

                // Skip the header row
                if (row.getRowNum() == 0) {
                    continue;
                }

                String id = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                String email = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                String name = row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                String password = row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                staffList.add(new Staff(id, password, name, email));
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return staffList;
    }

    // Additional methods specific to Staff functionality can be added as needed
    // public static void main(String[] args) {
    //     // Test createStaff
    //     Staff staffOne = new Staff("staff1", "password1", "John Doe", "john@example.com");
    //     Staff staffTwo = new Staff("staff2", "password2", "Jane Smith", "jane@example.com");

    //     DB_Staff.createStaff(staffOne);
    //     DB_Staff.createStaff(staffTwo);

    //     // Test readStaff
    //     Staff retrievedStaff = DB_Staff.readStaff("staff1");
    //     System.out.println("Retrieved Staff: " + retrievedStaff.getName());

    //     // Test updateStaff
    //     Staff updatedStaff = new Staff("staff1", "newPassword", "John Doe Updated", "john@example.com");
    //     DB_Staff.updateStaff(updatedStaff);

    //     // Test getAllStaff
    //     System.out.println("\nAll Staff:");
    //     ArrayList<Staff> allStaff = DB_Staff.getAllStaff();
    //     for (Staff staff : allStaff) {
    //         System.out.println(staff.getName());
    //     }

    //     // Test deleteStaff
    //     DB_Staff.deleteStaff("staff1");

    //     // Test getAllStaff after deletion
    //     System.out.println("\nAll Staff after Deletion:");
    //     ArrayList<Staff> staffAfterDeletion = DB_Staff.getAllStaff();
    //     for (Staff staff : staffAfterDeletion) {
    //         System.out.println(staff.getName());
    //     }
    // }
}
