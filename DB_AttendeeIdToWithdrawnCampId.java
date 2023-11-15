import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DB_AttendeeIdToWithdrawnCampId {
    private static final String FILE_PATH = DatabaseFilePaths.ATTENDEE_ID_TO_WITHDRAWN_CAMP_ID;

    public static void createMapping(String attendeeId, String withdrawnCampId) {
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

            Cell withdrawnCampIdCell = newRow.createCell(1);
            withdrawnCampIdCell.setCellValue(withdrawnCampId);

            // Save the changes to the file
            try (FileOutputStream fileOut = new FileOutputStream(FILE_PATH)) {
                workbook.write(fileOut);
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }
    }

    public static ArrayList<String> getWithdrawnCampIds(String attendeeId) {
        ArrayList<String> withdrawnCampIds = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String currentAttendeeId = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (attendeeId.equals(currentAttendeeId)) {
                    // Matching Attendee ID found, add corresponding Withdrawn Camp ID to the list
                    String currentWithdrawnCampId = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                    withdrawnCampIds.add(currentWithdrawnCampId);
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return withdrawnCampIds;
    }

    public static ArrayList<String> getAttendeeIds(String withdrawnCampId) {
        ArrayList<String> attendeeIds = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String currentWithdrawnCampId = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (withdrawnCampId.equals(currentWithdrawnCampId)) {
                    // Matching Withdrawn Camp ID found, add corresponding Attendee ID to the list
                    String currentAttendeeId = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                    attendeeIds.add(currentAttendeeId);
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return attendeeIds;
    }

    // Additional methods (create/update/delete) can be added as needed

    public static void deleteMapping(String attendeeId, String withdrawnCampId) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
                Row row = sheet.getRow(i);

                if (row != null) {
                    String currentAttendeeId = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue().trim();
                    String currentWithdrawnCampId = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue().trim();

                    if (attendeeId.trim().equals(currentAttendeeId) && withdrawnCampId.trim().equals(currentWithdrawnCampId)) {
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

    public static boolean isExists(String attendeeId, String withdrawnCampId) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
                Row row = sheet.getRow(i);

                if (row != null) {
                    String currentAttendeeId = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue().trim();
                    String currentWithdrawnCampId = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue().trim();

                    if (attendeeId.trim().equals(currentAttendeeId) && withdrawnCampId.trim().equals(currentWithdrawnCampId)) {
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
    //     testDB_AttendeeIdToWithdrawnCampId();
    // }

    // private static void testDB_AttendeeIdToWithdrawnCampId() {
    //     // Dummy data
    //     String attendeeId1 = "attendee1";
    //     String withdrawnCampId1 = "withdrawnCamp1";
    //     String attendeeId2 = "attendee2";
    //     String withdrawnCampId2 = "withdrawnCamp2";

    //     // Test createMapping
    //     DB_AttendeeIdToWithdrawnCampId.createMapping(attendeeId1, withdrawnCampId1);
    //     DB_AttendeeIdToWithdrawnCampId.createMapping(attendeeId2, withdrawnCampId1);  // Creating a second mapping for the same withdrawnCampId

    //     // Test getWithdrawnCampIds
    //     ArrayList<String> withdrawnCampIds1 = DB_AttendeeIdToWithdrawnCampId.getWithdrawnCampIds(attendeeId1);
    //     System.out.println("Withdrawn Camp IDs for Attendee ID " + attendeeId1 + ": ");
    //     for (String withdrawnCampId : withdrawnCampIds1) {
    //         System.out.println(withdrawnCampId);
    //     }

    //     ArrayList<String> withdrawnCampIds2 = DB_AttendeeIdToWithdrawnCampId.getWithdrawnCampIds(attendeeId2);
    //     System.out.println("Withdrawn Camp IDs for Attendee ID " + attendeeId2 + ": ");
    //     for (String withdrawnCampId : withdrawnCampIds2) {
    //         System.out.println(withdrawnCampId);
    //     }

    //     // Test getAttendeeIds
    //     ArrayList<String> attendeeIds1 = DB_AttendeeIdToWithdrawnCampId.getAttendeeIds(withdrawnCampId1);
    //     System.out.println("Attendee IDs for Withdrawn Camp ID " + withdrawnCampId1 + ": ");
    //     for (String attendeeId : attendeeIds1) {
    //         System.out.println(attendeeId);
    //     }

    //     // Test createMapping for additional mappings
    //     DB_AttendeeIdToWithdrawnCampId.createMapping(attendeeId1, withdrawnCampId2);
    //     DB_AttendeeIdToWithdrawnCampId.createMapping(attendeeId2, withdrawnCampId2);

    //     ArrayList<String> attendeeIds2 = DB_AttendeeIdToWithdrawnCampId.getAttendeeIds(withdrawnCampId2);
    //     System.out.println("Attendee IDs for Withdrawn Camp ID " + withdrawnCampId2 + ": ");
    //     for (String attendeeId : attendeeIds2) {
    //         System.out.println(attendeeId);
    //     }

    //     // Test isExists
    //     boolean exists1 = DB_AttendeeIdToWithdrawnCampId.isExists(attendeeId1, withdrawnCampId1);
    //     System.out.println("Mapping exists: " + exists1);

    //     boolean exists2 = DB_AttendeeIdToWithdrawnCampId.isExists(attendeeId2, withdrawnCampId1);
    //     System.out.println("Second mapping exists: " + exists2);

    //     // Test deleteMapping
    //     DB_AttendeeIdToWithdrawnCampId.deleteMapping(attendeeId1, withdrawnCampId1);

    //     // Test isExists after deletion
    //     exists1 = DB_AttendeeIdToWithdrawnCampId.isExists(attendeeId1, withdrawnCampId1);
    //     System.out.println("Mapping exists after deletion: " + exists1);
    // }
}
