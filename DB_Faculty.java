import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DB_Faculty {
    private static final String FILE_PATH = DatabaseFilePaths.FACULTY;

    public static void createFaculty(Faculty faculty) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            // Find the last row number
            int lastRowNum = sheet.getLastRowNum();

            // Create a new row
            Row newRow = sheet.createRow(lastRowNum + 1);

            // Write faculty data to the cells
            Cell idCell = newRow.createCell(0);
            idCell.setCellValue(faculty.getId());

            Cell nameCell = newRow.createCell(1);
            nameCell.setCellValue(faculty.getName());

            // Save the changes to the file
            try (FileOutputStream fileOut = new FileOutputStream(FILE_PATH)) {
                workbook.write(fileOut);
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }
    }

    public static Faculty readFaculty(String facultyId) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String id = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (facultyId.equals(id)) {
                    // Found the faculty member
                    String name = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                    return new Faculty(id, name);
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return null; // Faculty member not found
    }

    public static void updateFaculty(Faculty faculty) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String id = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (faculty.getId().equals(id)) {
                    // Update the faculty data in the row
                    row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(faculty.getName());

                    // Save the changes to the file
                    try (FileOutputStream fileOut = new FileOutputStream(FILE_PATH)) {
                        workbook.write(fileOut);
                    }

                    return; // Faculty member updated
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        // Faculty member not found
    }

    public static void deleteFaculty(String facultyId) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
            Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
                Row row = sheet.getRow(i);

                if (row != null) {
                    String id = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue().trim();

                    if (facultyId.trim().equals(id)) {
                        // Remove the row
                        sheet.removeRow(row);

                        // If the row is not the last row, shift the remaining rows up to fill the gap
                        int lastRowNum = sheet.getLastRowNum();
                        if (i < lastRowNum) {
                            sheet.shiftRows(i + 1, lastRowNum, -1);
                        }

                        // Save the changes to the file
                        try (FileOutputStream fileOut = new FileOutputStream(FILE_PATH)) {
                            workbook.write(fileOut);
                        }

                        // Adjust the row index after deletion
                        i--;

                        return; // Faculty member deleted
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        // Faculty member not found
    }


    public static ArrayList<Faculty> getAllFaculty() {
        ArrayList<Faculty> facultyList = new ArrayList<>();

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
                String name = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                facultyList.add(new Faculty(id, name));
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return facultyList;
    }

        // public static void main(String[] args) {
        // // Test createFaculty
        // createFaculty(new Faculty(RandomIdGenerator.generateRandomId(), "SCSE"));
        // createFaculty(new Faculty(RandomIdGenerator.generateRandomId(), "SPMS"));
        // createFaculty(new Faculty(RandomIdGenerator.generateRandomId(), "ADM"));
        // createFaculty(new Faculty(RandomIdGenerator.generateRandomId(), "EEE"));
        // createFaculty(new Faculty(RandomIdGenerator.generateRandomId(), "MSE"));
        // createFaculty(new Faculty(RandomIdGenerator.generateRandomId(), "MAE"));
        // createFaculty(new Faculty(RandomIdGenerator.generateRandomId(), "CEE"));
        // createFaculty(new Faculty(RandomIdGenerator.generateRandomId(), "CCEB"));
        // createFaculty(newFaculty);

        // // Test readFaculty
        // Faculty retrievedFaculty = readFaculty("TEST");
        // System.out.println("Retrieved Faculty: " + retrievedFaculty.getName());

        // // Test updateFaculty
        // Faculty updatedFaculty = new Faculty("TEST", "SPMS");
        // updateFaculty(updatedFaculty);

        // // Test readFaculty after update
        // Faculty updatedRetrievedFaculty = readFaculty("TEST");
        // System.out.println("Updated Retrieved Faculty: " + updatedRetrievedFaculty.getName());

        // // Test getAllFaculty
        // System.out.println("All Faculty:");
        // ArrayList<Faculty> allFaculty = getAllFaculty();
        // for (Faculty faculty : allFaculty) {
        //     System.out.println("ID: " + faculty.getId() + ", Name: " + faculty.getName());
        // }

        // // Test deleteFaculty
        // deleteFaculty("TEST");

        // // Test getAllFaculty after deletion
        // System.out.println("\nAll Faculty after Deletion:");
        // ArrayList<Faculty> facultyAfterDeletion = getAllFaculty();
        // for (Faculty faculty : facultyAfterDeletion) {
        //     System.out.println("ID: " + faculty.getId() + ", Name: " + faculty.getName());
        // }
    // }

}
