import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DB_CCMIdToFacultyId {
    private static final String FILE_PATH = DatabaseFilePaths.CCM_ID_TO_FACULTY_ID;

    public static void createMapping(String ccmId, String facultyId) {
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

    public static ArrayList<String> getCCMIds(String facultyId) {
        ArrayList<String> ccmIds = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String currentFacultyId = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (facultyId.equals(currentFacultyId)) {
                    // Matching faculty ID found, add corresponding CCM ID to the list
                    String currentCCMId = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                    ccmIds.add(currentCCMId);
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return ccmIds;
    }

    public static ArrayList<String> getFacultyIds(String ccmId) {
        ArrayList<String> facultyIds = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String currentCCMId = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (ccmId.equals(currentCCMId)) {
                    // Matching CCM ID found, add corresponding faculty ID to the list
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

    public static void deleteMapping(String ccmId, String facultyId) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
                Row row = sheet.getRow(i);

                if (row != null) {
                    String currentCCMId = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue().trim();
                    String currentFacultyId = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue().trim();

                    if (ccmId.trim().equals(currentCCMId) && facultyId.trim().equals(currentFacultyId)) {
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

    public static boolean isExists(String ccmId, String facultyId) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
                Row row = sheet.getRow(i);

                if (row != null) {
                    String currentCCMId = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue().trim();
                    String currentFacultyId = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue().trim();

                    if (ccmId.trim().equals(currentCCMId) && facultyId.trim().equals(currentFacultyId)) {
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
    //     // Test DB_CCMIdToFaculty
    //     testDB_CCMIdToFaculty();
    // }

    // private static void testDB_CCMIdToFaculty() {
    // // Dummy data
    //     String ccmId1 = "test1";
    //     String facultyId1 = "test2";
    //     String ccmId2 = "test3";
    //     String facultyId2 = "test4";

    //     // Test createMapping
    //     DB_CCMIdToFacultyId.createMapping(ccmId1, facultyId1);
    //     DB_CCMIdToFacultyId.createMapping(ccmId2, facultyId1);  // Creating a second mapping for the same facultyId

    //     // Test getFacultyIds
    //     ArrayList<String> facultyIds1 = DB_CCMIdToFacultyId.getFacultyIds(ccmId1);
    //     System.out.println("Faculty IDs for CCM ID " + ccmId1 + ": ");
    //     for (String facultyId : facultyIds1) {
    //         System.out.println(facultyId);
    //     }

    //     ArrayList<String> facultyIds2 = DB_CCMIdToFacultyId.getFacultyIds(ccmId2);
    //     System.out.println("Faculty IDs for CCM ID " + ccmId2 + ": ");
    //     for (String facultyId : facultyIds2) {
    //         System.out.println(facultyId);
    //     }

    //     // Test getCCMIds
    //     ArrayList<String> ccmIds1 = DB_CCMIdToFacultyId.getCCMIds(facultyId1);
    //     System.out.println("CCM IDs for Faculty ID " + facultyId1 + ": ");
    //     for (String ccmId : ccmIds1) {
    //         System.out.println(ccmId);
    //     }

    //     // Test createMapping for additional mappings
    //     DB_CCMIdToFacultyId.createMapping(ccmId1, facultyId2);
    //     DB_CCMIdToFacultyId.createMapping(ccmId2, facultyId2);

    //     ArrayList<String> ccmIds2 = DB_CCMIdToFacultyId.getCCMIds(facultyId2);
    //     System.out.println("CCM IDs for Faculty ID " + facultyId2 + ": ");
    //     for (String ccmId : ccmIds2) {
    //         System.out.println(ccmId);
    //     }

    //     // Test isExists
    //     boolean exists1 = DB_CCMIdToFacultyId.isExists(ccmId1, facultyId1);
    //     System.out.println("Mapping exists: " + exists1);

    //     boolean exists2 = DB_CCMIdToFacultyId.isExists(ccmId2, facultyId1);
    //     System.out.println("Second mapping exists: " + exists2);

    //     // Test deleteMapping
    //     DB_CCMIdToFacultyId.deleteMapping(ccmId1, facultyId1);

    //     // Test isExists after deletion
    //     exists1 = DB_CCMIdToFacultyId.isExists(ccmId1, facultyId1);
    //     System.out.println("Mapping exists after deletion: " + exists1);
    // }


}
