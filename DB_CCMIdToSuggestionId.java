import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DB_CCMIdToSuggestionId {
    private static final String FILE_PATH = DatabaseFilePaths.CCM_ID_TO_SUGGESTION_ID;

    public static void createMapping(String ccmId, String suggestionId) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            // Find the last row number
            int lastRowNum = sheet.getLastRowNum();

            // Create a new row
            Row newRow = sheet.createRow(lastRowNum + 1);

            // Write mapping data to the cells
            Cell ccmIdCell = newRow.createCell(0);
            ccmIdCell.setCellValue(ccmId);

            Cell suggestionIdCell = newRow.createCell(1);
            suggestionIdCell.setCellValue(suggestionId);

            // Save the changes to the file
            try (FileOutputStream fileOut = new FileOutputStream(FILE_PATH)) {
                workbook.write(fileOut);
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }
    }

    public static ArrayList<String> getSuggestionIds(String ccmId) {
        ArrayList<String> suggestionIds = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String currentCCMId = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (ccmId.equals(currentCCMId)) {
                    // Matching CCM ID found, add corresponding Suggestion ID to the list
                    String currentSuggestionId = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                    suggestionIds.add(currentSuggestionId);
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return suggestionIds;
    }

    public static ArrayList<String> getCCMIds(String suggestionId) {
        ArrayList<String> ccmIds = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String currentSuggestionId = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (suggestionId.equals(currentSuggestionId)) {
                    // Matching Suggestion ID found, add corresponding CCM ID to the list
                    String currentCCMId = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                    ccmIds.add(currentCCMId);
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return ccmIds;
    }

    // Additional methods (create/update/delete) can be added as needed

    public static void deleteMapping(String ccmId, String suggestionId) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
                Row row = sheet.getRow(i);

                if (row != null) {
                    String currentCCMId = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue().trim();
                    String currentSuggestionId = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue().trim();

                    if (ccmId.trim().equals(currentCCMId) && suggestionId.trim().equals(currentSuggestionId)) {
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

    public static boolean isExists(String ccmId, String suggestionId) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
                Row row = sheet.getRow(i);

                if (row != null) {
                    String currentCCMId = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue().trim();
                    String currentSuggestionId = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue().trim();

                    if (ccmId.trim().equals(currentCCMId) && suggestionId.trim().equals(currentSuggestionId)) {
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
        testDB_CCMIdToSuggestionId();
    }

    private static void testDB_CCMIdToSuggestionId() {
        // Dummy data
        String ccmId1 = "ccm1";
        String suggestionId1 = "suggestion1";
        String ccmId2 = "ccm2";
        String suggestionId2 = "suggestion2";

        // Test createMapping
        DB_CCMIdToSuggestionId.createMapping(ccmId1, suggestionId1);
        DB_CCMIdToSuggestionId.createMapping(ccmId2, suggestionId1);  // Creating a second mapping for the same suggestionId

        // Test getSuggestionIds
        ArrayList<String> suggestionIds1 = DB_CCMIdToSuggestionId.getSuggestionIds(ccmId1);
        System.out.println("Suggestion IDs for CCM ID " + ccmId1 + ": ");
        for (String suggestionId : suggestionIds1) {
            System.out.println(suggestionId);
        }

        ArrayList<String> suggestionIds2 = DB_CCMIdToSuggestionId.getSuggestionIds(ccmId2);
        System.out.println("Suggestion IDs for CCM ID " + ccmId2 + ": ");
        for (String suggestionId : suggestionIds2) {
            System.out.println(suggestionId);
        }

        // Test getCCMIds
        ArrayList<String> ccmIds1 = DB_CCMIdToSuggestionId.getCCMIds(suggestionId1);
        System.out.println("CCM IDs for Suggestion ID " + suggestionId1 + ": ");
        for (String ccmId : ccmIds1) {
            System.out.println(ccmId);
        }

        // Test createMapping for additional mappings
        DB_CCMIdToSuggestionId.createMapping(ccmId1, suggestionId2);
        DB_CCMIdToSuggestionId.createMapping(ccmId2, suggestionId2);

        ArrayList<String> ccmIds2 = DB_CCMIdToSuggestionId.getCCMIds(suggestionId2);
        System.out.println("CCM IDs for Suggestion ID " + suggestionId2 + ": ");
        for (String ccmId : ccmIds2) {
            System.out.println(ccmId);
        }

        // Test isExists
        boolean exists1 = DB_CCMIdToSuggestionId.isExists(ccmId1, suggestionId1);
        System.out.println("Mapping exists: " + exists1);

        boolean exists2 = DB_CCMIdToSuggestionId.isExists(ccmId2, suggestionId1);
        System.out.println("Second mapping exists: " + exists2);

        // Test deleteMapping
        DB_CCMIdToSuggestionId.deleteMapping(ccmId1, suggestionId1);

        // Test isExists after deletion
        exists1 = DB_CCMIdToSuggestionId.isExists(ccmId1, suggestionId1);
        System.out.println("Mapping exists after deletion: " + exists1);
    }
}
