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
                String id = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (attendeeId.equals(id)) {
                    // Found a mapping for the attendeeId
                    enquiryIds.add(row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
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
                String id = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (enquiryId.equals(id)) {
                    // Found a mapping for the enquiryId
                    attendeeIds.add(row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return attendeeIds;
    }

    public static void updateMapping(String attendeeId, String newEnquiryId) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String id = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (attendeeId.equals(id)) {
                    // Update the mapping data in the row
                    row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(newEnquiryId);

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

    public static void deleteMapping(String attendeeId) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String id = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (attendeeId.equals(id)) {
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

    public static ArrayList<String> getAllAttendeeIds() {
        ArrayList<String> attendeeIds = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String id = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                attendeeIds.add(id);
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return attendeeIds;
    }

    public static ArrayList<String> getAllEnquiryIds() {
        ArrayList<String> enquiryIds = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String id = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                enquiryIds.add(id);
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return enquiryIds;
    }
}
