import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DB_Enquiry {
    private static final String FILE_PATH = DatabaseFilePaths.ENQUIRY;

    public static void createEnquiry(Enquiry enquiry) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            // Find the last row number
            int lastRowNum = sheet.getLastRowNum();

            // Create a new row
            Row newRow = sheet.createRow(lastRowNum + 1);

            // Write enquiry data to the cells
            Cell idCell = newRow.createCell(0);
            idCell.setCellValue(enquiry.getId());

            Cell subjectCell = newRow.createCell(1);
            subjectCell.setCellValue(enquiry.getSubject());

            Cell descriptionCell = newRow.createCell(2);
            descriptionCell.setCellValue(enquiry.getDescription());

            Cell isProcessedCell = newRow.createCell(3);
            isProcessedCell.setCellValue(enquiry.getIsProcessed());

            Cell replyTextCell = newRow.createCell(4);
            replyTextCell.setCellValue(enquiry.getReplyText());

            Cell repliedByNameCell = newRow.createCell(5);
            repliedByNameCell.setCellValue(enquiry.getRepliedByName());

            Cell repliedByStaffCell = newRow.createCell(6);
            repliedByStaffCell.setCellValue(enquiry.getRepliedByStaff());

            // Save the changes to the file
            try (FileOutputStream fileOut = new FileOutputStream(FILE_PATH)) {
                workbook.write(fileOut);
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }
    }

    public static Enquiry readEnquiry(String enquiryId) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String id = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (enquiryId.equals(id)) {
                    // Found the enquiry
                    String subject = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                    String description = row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                    boolean isProcessed = row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getBooleanCellValue();
                    String replyText = row.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                    String repliedByName = row.getCell(5, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                    boolean repliedByStaff = row.getCell(6, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getBooleanCellValue();

                    return new Enquiry(id, subject, description, isProcessed, replyText, repliedByName, repliedByStaff);
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return null; // Enquiry not found
    }

    public static void updateEnquiry(Enquiry enquiry) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String id = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (enquiry.getId().equals(id)) {
                    // Update the enquiry data in the row
                    row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(enquiry.getSubject());
                    row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(enquiry.getDescription());
                    row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(enquiry.getIsProcessed());
                    row.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(enquiry.getReplyText());
                    row.getCell(5, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(enquiry.getRepliedByName());
                    row.getCell(6, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(enquiry.getRepliedByStaff());

                    // Save the changes to the file
                    try (FileOutputStream fileOut = new FileOutputStream(FILE_PATH)) {
                        workbook.write(fileOut);
                    }

                    return; // Enquiry updated
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        // Enquiry not found
    }

    public static void deleteEnquiry(String enquiryId) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
            Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
                Row row = sheet.getRow(i);

                if (row != null) {
                    String id = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue().trim();

                    if (enquiryId.trim().equals(id)) {
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

                        return; // Enquiry deleted
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        // Enquiry not found
    }

    public static ArrayList<Enquiry> getAllEnquiries() {
        ArrayList<Enquiry> enquiries = new ArrayList<>();

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
                String subject = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                String description = row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                boolean isProcessed = row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getBooleanCellValue();
                String replyText = row.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                String repliedByName = row.getCell(5, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                boolean repliedByStaff = row.getCell(6, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getBooleanCellValue();

                enquiries.add(new Enquiry(id, subject, description, isProcessed, replyText, repliedByName, repliedByStaff));
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return enquiries;
    }

    // public static void main(String[] args) {
    //     // Test DB_Enquiry
    //     testDB_Enquiry();

    //     // Add tests for other classes as needed
    // }

    // private static void testDB_Enquiry() {
    //     // Dummy data
    //     String enquiryId1 = "enquiry1";
    //     String enquiryId2 = "enquiry2";

    //     // Test createEnquiry
    //     Enquiry enquiry1 = new Enquiry(enquiryId1, "Subject 1", "Description 1", false, null, null, false);
    //     Enquiry enquiry2 = new Enquiry(enquiryId2, "Subject 2", "Description 2", true, "Reply 2", "Staff Name", true);

    //     DB_Enquiry.createEnquiry(enquiry1);
    //     DB_Enquiry.createEnquiry(enquiry2);

    //     // Test readEnquiry
    //     Enquiry retrievedEnquiry1 = DB_Enquiry.readEnquiry(enquiryId1);
    //     System.out.println("Retrieved Enquiry 1: " + retrievedEnquiry1.getSubject());

    //     // Test updateEnquiry
    //     Enquiry updatedEnquiry1 = new Enquiry(enquiryId1, "Updated Subject 1", "Updated Description 1", true, "Updated Reply 1", "Updated Staff Name", true);
    //     DB_Enquiry.updateEnquiry(updatedEnquiry1);

    //     // Test getAllEnquiries
    //     System.out.println("All Enquiries:");
    //     ArrayList<Enquiry> allEnquiries = DB_Enquiry.getAllEnquiries();
    //     for (Enquiry enquiry : allEnquiries) {
    //         System.out.println(enquiry.getSubject());
    //     }

    //     // Test deleteEnquiry
    //     DB_Enquiry.deleteEnquiry(enquiryId1);

    //     // Test getAllEnquiries after deletion
    //     System.out.println("\nAll Enquiries after Deletion:");
    //     ArrayList<Enquiry> enquiriesAfterDeletion = DB_Enquiry.getAllEnquiries();
    //     for (Enquiry enquiry : enquiriesAfterDeletion) {
    //         System.out.println(enquiry.getSubject());
    //     }
    // }
}
