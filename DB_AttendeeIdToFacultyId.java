import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DB_AttendeeIdToFacultyId {
    private static final String FILE_PATH = DatabaseFilePaths.ATTENDEE_ID_TO_FACULTY_ID;

    public static void createMapping(String attendeeId, String facultyId) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            // Find the last row number
            int lastRowNum = sheet.getLastRowNum();

            // Create a new row
            Row newRow = sheet.createRow(lastRowNum + 1);

            // Write mapping data to the cells
            Cell attendeeIdCell = newRow.createCell(0);
            attendeeIdCell.setCellValue(attendeeId);

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

    public static ArrayList<String> getAttendeeIds(String facultyId) {
        ArrayList<String> attendeeIds = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String currentFacultyId = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (facultyId.equals(currentFacultyId)) {
                    // Matching faculty ID found, add corresponding attendee ID to the list
                    String currentAttendeeId = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                    attendeeIds.add(currentAttendeeId);
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return attendeeIds;
    }

    public static ArrayList<String> getFacultyIds(String attendeeId) {
        ArrayList<String> facultyIds = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String currentAttendeeId = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (attendeeId.equals(currentAttendeeId)) {
                    // Matching attendee ID found, add corresponding faculty ID to the list
                    String currentFacultyId = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                    facultyIds.add(currentFacultyId);
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return facultyIds;
    }

    // Additional methods (create/update/delete) can be added as needed

    public static void deleteMapping(String attendeeId, String facultyId) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
                Row row = sheet.getRow(i);

                if (row != null) {
                    String currentAttendeeId = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue().trim();
                    String currentFacultyId = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue().trim();

                    if (attendeeId.trim().equals(currentAttendeeId) && facultyId.trim().equals(currentFacultyId)) {
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

    public static boolean isExists(String attendeeId, String facultyId) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
                Row row = sheet.getRow(i);

                if (row != null) {
                    String currentAttendeeId = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue().trim();
                    String currentFacultyId = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue().trim();

                    if (attendeeId.trim().equals(currentAttendeeId) && facultyId.trim().equals(currentFacultyId)) {
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
    //     // Assuming camps already exist in DB_Camp

    //     // Test DB_Attendee
    //     DB_AttendeeIdToFacultyId.createMapping("test","test");
    //     DB_AttendeeIdToFacultyId.createMapping("test1","test1");
    //     DB_AttendeeIdToFacultyId.createMapping("test2","test2");
    //     System.out.println(DB_AttendeeIdToFacultyId.isExists("test1","test1"));
    //     System.out.println(DB_AttendeeIdToFacultyId.isExists("test1","test2"));
    //     DB_AttendeeIdToFacultyId.deleteMapping("test2","test2");


    // }

}
