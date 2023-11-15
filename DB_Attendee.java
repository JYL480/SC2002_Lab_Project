import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DB_Attendee {
    private static final String FILE_PATH = DatabaseFilePaths.ATTENDEE;

    public static void createAttendee(Attendee attendee) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            // Find the last row number
            int lastRowNum = sheet.getLastRowNum();

            // Create a new row
            Row newRow = sheet.createRow(lastRowNum + 1);

            // Write attendee data to the cells
            Cell idCell = newRow.createCell(0);
            idCell.setCellValue(attendee.getId());

            Cell emailCell = newRow.createCell(1);
            emailCell.setCellValue(attendee.getEmail());

            Cell nameCell = newRow.createCell(2);
            nameCell.setCellValue(attendee.getName());

            Cell passwordCell = newRow.createCell(3);
            passwordCell.setCellValue(attendee.getPassword());

            Cell isCampCommitteeCell = newRow.createCell(4);
            isCampCommitteeCell.setCellValue(attendee.getIsCampCommittee());

            // Save the changes to the file
            try (FileOutputStream fileOut = new FileOutputStream(FILE_PATH)) {
                workbook.write(fileOut);
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }
    }

    public static Attendee readAttendee(String attendeeId) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String id = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (attendeeId.equals(id)) {
                    // Found the attendee
                    String email = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                    String name = row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                    String password = row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                    boolean isCampCommittee = row.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getBooleanCellValue();

                    return new Attendee(id, password, name, email, isCampCommittee);
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return null; // Attendee not found
    }

    public static void updateAttendee(Attendee attendee) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String id = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (attendee.getId().equals(id)) {
                    // Update the attendee data in the row
                    row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(attendee.getEmail());
                    row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(attendee.getName());
                    row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(attendee.getPassword());
                    row.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(attendee.getIsCampCommittee());


                    // Save the changes to the file
                    try (FileOutputStream fileOut = new FileOutputStream(FILE_PATH)) {
                        workbook.write(fileOut);
                    }

                    return; // Attendee updated
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        // Attendee not found
    }

    public static void deleteAttendee(String attendeeId) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
            Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
                Row row = sheet.getRow(i);

                if (row != null) {
                    String id = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue().trim();

                    if (attendeeId.trim().equals(id)) {
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

                        return; // Attendee deleted
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        // Attendee not found
    }

    public static ArrayList<Attendee> getAllAttendees() {
        ArrayList<Attendee> attendees = new ArrayList<>();

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
                boolean isCampCommittee = row.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getBooleanCellValue();

                attendees.add(new Attendee(id, password, name, email, isCampCommittee));
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return attendees;
    }



    // Add other methods as needed
    // public static void main(String[] args) {
    //     // Test createAttendee
    //     Attendee newAttendee = new Attendee("TEST123", "testpassword", "Test User", "test@example.com", false);
    //     Attendee newAttendee2 = new Attendee("TasdfT123", "testpassword", "Giga User", "test@example.com", false);

    //     DB_Attendee.createAttendee(newAttendee);
    //      DB_Attendee.createAttendee(newAttendee2);

    //     // Test readAttendee
    //     Attendee retrievedAttendee = DB_Attendee.readAttendee("TEST123");
    //     System.out.println("Retrieved Attendee: " + retrievedAttendee.getName() + " - " + retrievedAttendee.getEmail());

    //     // Test updateAttendee
    //     Attendee updatedAttendee = new Attendee("TEST123", "newpassword", "Updated User", "updated@example.com", false);
    //     DB_Attendee.updateAttendee(updatedAttendee);

    //     // Test readAttendee after update
    //     Attendee updatedRetrievedAttendee = DB_Attendee.readAttendee("TEST123");
    //     System.out.println("Updated Retrieved Attendee: " + updatedRetrievedAttendee.getName() + " - " + updatedRetrievedAttendee.getEmail());

    //     // Test getAllAttendees
    //     System.out.println("All Attendees:");
    //     ArrayList<Attendee> allAttendees = DB_Attendee.getAllAttendees();
    //     for (Attendee attendee : allAttendees) {
    //         System.out.println("ID: " + attendee.getId() + ", Name: " + attendee.getName() + ", Email: " + attendee.getEmail());
    //     }

    //     // Test deleteAttendee
    //     DB_Attendee.deleteAttendee("TEST123");

    //     // Test getAllAttendees after deletion
    //     System.out.println("\nAll Attendees after Deletion:");
    //     ArrayList<Attendee> attendeesAfterDeletion = DB_Attendee.getAllAttendees();
    //     for (Attendee attendee : attendeesAfterDeletion) {
    //         System.out.println("ID: " + attendee.getId() + ", Name: " + attendee.getName() + ", Email: " + attendee.getEmail());
    //     }
    // }

}
