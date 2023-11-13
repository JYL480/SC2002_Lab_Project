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
            newRow.createCell(0).setCellValue(enquiry.id);
            newRow.createCell(1).setCellValue(enquiry.subject);
            newRow.createCell(2).setCellValue(enquiry.description);
            newRow.createCell(3).setCellValue(enquiry.isProcessed);
            newRow.createCell(4).setCellValue(enquiry.replyText);
            newRow.createCell(5).setCellValue(enquiry.repliedByName);
            newRow.createCell(6).setCellValue(enquiry.repliedByStaff);

            // Save the changes to the file
            try (FileOutputStream fileOut = new FileOutputStream(FILE_PATH)) {
                workbook.write(fileOut);
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }
    }

    public static Enquiry getEnquiry(String id) {
        Enquiry enquiry = new Enquiry();

        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String rowId = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (id.equals(rowId)) {
                    // Found enquiry data for the given id
                    enquiry.id = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                    enquiry.subject = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                    enquiry.description = row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                    enquiry.isProcessed = row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getBooleanCellValue();
                    enquiry.replyText = row.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                    enquiry.repliedByName = row.getCell(5, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                    enquiry.repliedByStaff = row.getCell(6, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getBooleanCellValue();

                    return enquiry;
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return enquiry; // Enquiry data not found
    }

    public static void updateEnquiry(Enquiry updatedEnquiry) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String rowId = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (updatedEnquiry.id.equals(rowId)) {
                    // Update the enquiry data in the row
                    row.getCell(1).setCellValue(updatedEnquiry.subject);
                    row.getCell(2).setCellValue(updatedEnquiry.description);
                    row.getCell(3).setCellValue(updatedEnquiry.isProcessed);
                    row.getCell(4).setCellValue(updatedEnquiry.replyText);
                    row.getCell(5).setCellValue(updatedEnquiry.repliedByName);
                    row.getCell(6).setCellValue(updatedEnquiry.repliedByStaff);

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

    public static void deleteEnquiry(String id) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String rowId = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (id.equals(rowId)) {
                    // Remove the row
                    iterator.remove();

                    // Save the changes to the file
                    try (FileOutputStream fileOut = new FileOutputStream(FILE_PATH)) {
                        workbook.write(fileOut);
                    }

                    return; // Enquiry deleted
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        // Enquiry not found
    }

    public static ArrayList<Enquiry> getAllEnquiries() {
        ArrayList<Enquiry> allEnquiries = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();

                Enquiry enquiry = new Enquiry();
                enquiry.id = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                enquiry.subject = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                enquiry.description = row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                enquiry.isProcessed = row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getBooleanCellValue();
                enquiry.replyText = row.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                enquiry.repliedByName = row.getCell(5, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                enquiry.repliedByStaff = row.getCell(6, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getBooleanCellValue();

                allEnquiries.add(enquiry);
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return allEnquiries;
    }
}
