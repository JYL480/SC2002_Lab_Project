import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DB_AttendeeIdToEnquiryId {
    private static final String FILE_PATH = DatabaseFilePaths.ATTENDEE_ID_TO_ENQUIRY_ID;

    public static void createMapping(String attendeeId, String enquiryId) {
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

            Cell enquiryIdCell = newRow.createCell(1);
            enquiryIdCell.setCellValue(enquiryId);

            // Save the changes to the file
            try (FileOutputStream fileOut = new FileOutputStream(FILE_PATH)) {
                workbook.write(fileOut);
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }
    }

    public static ArrayList<String> getEnquiryIds(String attendeeId) {
        ArrayList<String> enquiryIds = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String currentAttendeeId = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (attendeeId.equals(currentAttendeeId)) {
                    // Matching Attendee ID found, add corresponding Enquiry ID to the list
                    String currentEnquiryId = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                    enquiryIds.add(currentEnquiryId);
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return enquiryIds;
    }

    public static ArrayList<String> getAttendeeIds(String enquiryId) {
        ArrayList<String> attendeeIds = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String currentEnquiryId = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (enquiryId.equals(currentEnquiryId)) {
                    // Matching Enquiry ID found, add corresponding Attendee ID to the list
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

    public static void deleteMapping(String attendeeId, String enquiryId) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
                Row row = sheet.getRow(i);

                if (row != null) {
                    String currentAttendeeId = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue().trim();
                    String currentEnquiryId = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue().trim();

                    if (attendeeId.trim().equals(currentAttendeeId) && enquiryId.trim().equals(currentEnquiryId)) {
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

    public static boolean isExists(String attendeeId, String enquiryId) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
                Row row = sheet.getRow(i);

                if (row != null) {
                    String currentAttendeeId = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue().trim();
                    String currentEnquiryId = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue().trim();

                    if (attendeeId.trim().equals(currentAttendeeId) && enquiryId.trim().equals(currentEnquiryId)) {
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
    //     testDB_AttendeeIdToEnquiryId();
    // }

    // private static void testDB_AttendeeIdToEnquiryId() {
    //     // Dummy data
    //     String attendeeId1 = "attendee1";
    //     String enquiryId1 = "enquiry1";
    //     String attendeeId2 = "attendee2";
    //     String enquiryId2 = "enquiry2";

    //     // Test createMapping
    //     DB_AttendeeIdToEnquiryId.createMapping(attendeeId1, enquiryId1);
    //     DB_AttendeeIdToEnquiryId.createMapping(attendeeId2, enquiryId1);  // Creating a second mapping for the same enquiryId

    //     // Test getEnquiryIds
    //     ArrayList<String> enquiryIds1 = DB_AttendeeIdToEnquiryId.getEnquiryIds(attendeeId1);
    //     System.out.println("Enquiry IDs for Attendee ID " + attendeeId1 + ": ");
    //     for (String enquiryId : enquiryIds1) {
    //         System.out.println(enquiryId);
    //     }

    //     ArrayList<String> enquiryIds2 = DB_AttendeeIdToEnquiryId.getEnquiryIds(attendeeId2);
    //     System.out.println("Enquiry IDs for Attendee ID " + attendeeId2 + ": ");
    //     for (String enquiryId : enquiryIds2) {
    //         System.out.println(enquiryId);
    //     }

    //     // Test getAttendeeIds
    //     ArrayList<String> attendeeIds1 = DB_AttendeeIdToEnquiryId.getAttendeeIds(enquiryId1);
    //     System.out.println("Attendee IDs for Enquiry ID " + enquiryId1 + ": ");
    //     for (String attendeeId : attendeeIds1) {
    //         System.out.println(attendeeId);
    //     }

    //     // Test createMapping for additional mappings
    //     DB_AttendeeIdToEnquiryId.createMapping(attendeeId1, enquiryId2);
    //     DB_AttendeeIdToEnquiryId.createMapping(attendeeId2, enquiryId2);

    //     ArrayList<String> attendeeIds2 = DB_AttendeeIdToEnquiryId.getAttendeeIds(enquiryId2);
    //     System.out.println("Attendee IDs for Enquiry ID " + enquiryId2 + ": ");
    //     for (String attendeeId : attendeeIds2) {
    //         System.out.println(attendeeId);
    //     }

    //     // Test isExists
    //     boolean exists1 = DB_AttendeeIdToEnquiryId.isExists(attendeeId1, enquiryId1);
    //     System.out.println("Mapping exists: " + exists1);

    //     boolean exists2 = DB_AttendeeIdToEnquiryId.isExists(attendeeId2, enquiryId1);
    //     System.out.println("Second mapping exists: " + exists2);

    //     // Test deleteMapping
    //     DB_AttendeeIdToEnquiryId.deleteMapping(attendeeId1, enquiryId1);

    //     // Test isExists after deletion
    //     exists1 = DB_AttendeeIdToEnquiryId.isExists(attendeeId1, enquiryId1);
    //     System.out.println("Mapping exists after deletion: " + exists1);
    // }
}
