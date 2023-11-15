import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DB_CampIdToFacultyId {
    private static final String FILE_PATH = DatabaseFilePaths.CAMP_ID_TO_FACULTY_ID;

    public static void createMapping(String campId, String facultyId) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            // Find the last row number
            int lastRowNum = sheet.getLastRowNum();

            // Create a new row
            Row newRow = sheet.createRow(lastRowNum + 1);

            // Write mapping data to the cells
            Cell campIdCell = newRow.createCell(0);
            campIdCell.setCellValue(campId);

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

    public static ArrayList<String> getCampIds(String facultyId) {
        ArrayList<String> campIds = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String currentFacultyId = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (facultyId.equals(currentFacultyId)) {
                    // Matching faculty ID found, add corresponding camp ID to the list
                    String currentCampId = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                    campIds.add(currentCampId);
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return campIds;
    }

    public static ArrayList<String> getFacultyIds(String campId) {
        ArrayList<String> facultyIds = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String currentCampId = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (campId.equals(currentCampId)) {
                    // Matching camp ID found, add corresponding faculty ID to the list
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

public static void deleteMapping(String campId, String facultyId) {
    try (FileInputStream file = new FileInputStream(FILE_PATH);
         Workbook workbook = new XSSFWorkbook(file)) {

        Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

        for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
            Row row = sheet.getRow(i);

            if (row != null) {
                String currentCampId = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue().trim();
                String currentFacultyId = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue().trim();

                if (campId.trim().equals(currentCampId) && facultyId.trim().equals(currentFacultyId)) {
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

public static void deleteMappingsByCampId(String campId) {
    try (FileInputStream file = new FileInputStream(FILE_PATH);
         Workbook workbook = new XSSFWorkbook(file)) {

        Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

        for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
            Row row = sheet.getRow(i);

            if (row != null) {
                String currentCampId = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue().trim();

                if (campId.trim().equals(currentCampId)) {
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
                }
            }
        }

    } catch (IOException e) {
        e.printStackTrace(); // Handle the exception according to your needs
    }
}


public static boolean isExists(String campId, String facultyId) {
    try (FileInputStream file = new FileInputStream(FILE_PATH);
         Workbook workbook = new XSSFWorkbook(file)) {

        Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

        for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
            Row row = sheet.getRow(i);

            if (row != null) {
                String currentCampId = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue().trim();
                String currentFacultyId = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue().trim();

                if (campId.trim().equals(currentCampId) && facultyId.trim().equals(currentFacultyId)) {
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
//         // Test createMapping
//         DB_CampIdToFacultyId.createMapping("d0ec9903fd224708a8379cca20c4ce6c", "98d0e59407f946b7aed49150ceba8627");
//         DB_CampIdToFacultyId.createMapping("d0ec9903fd224708a8379cca20c4ce6c", "test");
//         System.out.println(DB_CampIdToFacultyId.isExists("d0ec9903fd224708a8379cca20c4ce6c", "98d0e59407f946b7aed49150ceba8627"));
//          System.out.println(DB_CampIdToFacultyId.isExists("d0ec9903fd224708a8379cca20c4ce6c", "98d0e59407f946b7aed4915d0ceba8627"));

//         // Test getCampIds
//         System.out.println("Camp IDs for faculty ID '98d0e59407f946b7aed49150ceba8627':");
//         ArrayList<String> campIdsForFaculty = DB_CampIdToFacultyId.getCampIds("98d0e59407f946b7aed49150ceba8627");
//         for (String campId : campIdsForFaculty) {
//             System.out.println(campId);
//         }

//         // Test getFacultyIds
//         System.out.println("\nFaculty IDs for camp ID 'd0ec9903fd224708a8379cca20c4ce6c':");
//         ArrayList<String> facultyIdsForCamp = DB_CampIdToFacultyId.getFacultyIds("d0ec9903fd224708a8379cca20c4ce6c");
//         for (String facultyId : facultyIdsForCamp) {
//             System.out.println(facultyId);
//         }

//         // Test deleteMapping
//         DB_CampIdToFacultyId.deleteMapping("d0ec9903fd224708a8379cca20c4ce6c", "test");

//         // Test getCampIds after deletion
//         System.out.println("\nCamp IDs for faculty ID 'c9d8e441332d46bbb9655b8239c26e94' after deletion:");
//         ArrayList<String> campIdsAfterDeletion = DB_CampIdToFacultyId.getCampIds("c9d8e441332d46bbb9655b8239c26e94");
//         for (String campId : campIdsAfterDeletion) {
//             System.out.println(campId);
//         }
//     }

}
