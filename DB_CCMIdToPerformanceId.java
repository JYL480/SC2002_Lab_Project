import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DB_CCMIdToPerformanceId {
    private static final String FILE_PATH = DatabaseFilePaths.CCM_ID_TO_PERFORMANCE_ID;

    public static void createMapping(String ccmId, String performanceId) {
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

            Cell performanceIdCell = newRow.createCell(1);
            performanceIdCell.setCellValue(performanceId);

            // Save the changes to the file
            try (FileOutputStream fileOut = new FileOutputStream(FILE_PATH)) {
                workbook.write(fileOut);
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }
    }

    public static ArrayList<String> getPerformanceIds(String ccmId) {
        ArrayList<String> performanceIds = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String currentCCMId = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (ccmId.equals(currentCCMId)) {
                    // Matching CCM ID found, add corresponding Performance ID to the list
                    String currentPerformanceId = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                    performanceIds.add(currentPerformanceId);
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return performanceIds;
    }

    public static ArrayList<String> getCCMIds(String performanceId) {
        ArrayList<String> ccmIds = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String currentPerformanceId = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (performanceId.equals(currentPerformanceId)) {
                    // Matching Performance ID found, add corresponding CCM ID to the list
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

    public static void deleteMapping(String ccmId, String performanceId) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
                Row row = sheet.getRow(i);

                if (row != null) {
                    String currentCCMId = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue().trim();
                    String currentPerformanceId = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue().trim();

                    if (ccmId.trim().equals(currentCCMId) && performanceId.trim().equals(currentPerformanceId)) {
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

    public static boolean isExists(String ccmId, String performanceId) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
                Row row = sheet.getRow(i);

                if (row != null) {
                    String currentCCMId = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue().trim();
                    String currentPerformanceId = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue().trim();

                    if (ccmId.trim().equals(currentCCMId) && performanceId.trim().equals(currentPerformanceId)) {
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
    //     testDB_CCMIdToPerformanceId();
    // }

    // private static void testDB_CCMIdToPerformanceId() {
    //     // Dummy data
    //     String ccmId1 = "ccm1";
    //     String performanceId1 = "performance1";
    //     String ccmId2 = "ccm2";
    //     String performanceId2 = "performance2";

    //     // Test createMapping
    //     DB_CCMIdToPerformanceId.createMapping(ccmId1, performanceId1);
    //     DB_CCMIdToPerformanceId.createMapping(ccmId2, performanceId1);  // Creating a second mapping for the same performanceId

    //     // Test getPerformanceIds
    //     ArrayList<String> performanceIds1 = DB_CCMIdToPerformanceId.getPerformanceIds(ccmId1);
    //     System.out.println("Performance IDs for CCM ID " + ccmId1 + ": ");
    //     for (String performanceId : performanceIds1) {
    //         System.out.println(performanceId);
    //     }

    //     ArrayList<String> performanceIds2 = DB_CCMIdToPerformanceId.getPerformanceIds(ccmId2);
    //     System.out.println("Performance IDs for CCM ID " + ccmId2 + ": ");
    //     for (String performanceId : performanceIds2) {
    //         System.out.println(performanceId);
    //     }

    //     // Test getCCMIds
    //     ArrayList<String> ccmIds1 = DB_CCMIdToPerformanceId.getCCMIds(performanceId1);
    //     System.out.println("CCM IDs for Performance ID " + performanceId1 + ": ");
    //     for (String ccmId : ccmIds1) {
    //         System.out.println(ccmId);
    //     }

    //     // Test createMapping for additional mappings
    //     DB_CCMIdToPerformanceId.createMapping(ccmId1, performanceId2);
    //     DB_CCMIdToPerformanceId.createMapping(ccmId2, performanceId2);

    //     ArrayList<String> ccmIds2 = DB_CCMIdToPerformanceId.getCCMIds(performanceId2);
    //     System.out.println("CCM IDs for Performance ID " + performanceId2 + ": ");
    //     for (String ccmId : ccmIds2) {
    //         System.out.println(ccmId);
    //     }

    //     // Test isExists
    //     boolean exists1 = DB_CCMIdToPerformanceId.isExists(ccmId1, performanceId1);
    //     System.out.println("Mapping exists: " + exists1);

    //     boolean exists2 = DB_CCMIdToPerformanceId.isExists(ccmId2, performanceId1);
    //     System.out.println("Second mapping exists: " + exists2);

    //     // Test deleteMapping
    //     DB_CCMIdToPerformanceId.deleteMapping(ccmId1, performanceId1);

    //     // Test isExists after deletion
    //     exists1 = DB_CCMIdToPerformanceId.isExists(ccmId1, performanceId1);
    //     System.out.println("Mapping exists after deletion: " + exists1);
    // }
}
