import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DB_CCM {
    private static final String FILE_PATH = DatabaseFilePaths.CCM;

    public static void createCampCommitteeMember(CampCommitteeMember ccm) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            // Find the last row number
            int lastRowNum = sheet.getLastRowNum();

            // Create a new row
            Row newRow = sheet.createRow(lastRowNum + 1);

            // Write CampCommitteeMember data to the cells
            Cell idCell = newRow.createCell(0);
            idCell.setCellValue(ccm.getId());

            Cell emailCell = newRow.createCell(1);
            emailCell.setCellValue(ccm.getEmail());

            Cell nameCell = newRow.createCell(2);
            nameCell.setCellValue(ccm.getName());

            Cell passwordCell = newRow.createCell(3);
            passwordCell.setCellValue(ccm.getPassword());

            // Save the changes to the file
            try (FileOutputStream fileOut = new FileOutputStream(FILE_PATH)) {
                workbook.write(fileOut);
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }
    }

    public static CampCommitteeMember readCampCommitteeMember(String ccmId) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String id = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (ccmId.equals(id)) {
                    // Found the CampCommitteeMember
                    String email = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                    String name = row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                    String password = row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                    return new CampCommitteeMember(id, password, name, email, true, 0); // Assuming isCampCommittee and points are fixed values
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return null; // CampCommitteeMember not found
    }

    public static void updateCampCommitteeMember(CampCommitteeMember ccm) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String id = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (ccm.getId().equals(id)) {
                    // Update the CampCommitteeMember data in the row
                    row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(ccm.getEmail());
                    row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(ccm.getName());
                    row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(ccm.getPassword());

                    // Save the changes to the file
                    try (FileOutputStream fileOut = new FileOutputStream(FILE_PATH)) {
                        workbook.write(fileOut);
                    }

                    return; // CampCommitteeMember updated
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        // CampCommitteeMember not found
    }

    public static void deleteCampCommitteeMember(String ccmId) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
            Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
                Row row = sheet.getRow(i);

                if (row != null) {
                    String id = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue().trim();

                    if (ccmId.trim().equals(id)) {
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

                        return; // CampCommitteeMember deleted
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        // CampCommitteeMember not found
    }

    public static ArrayList<CampCommitteeMember> getAllCampCommitteeMembers() {
        ArrayList<CampCommitteeMember> campCommitteeMembers = new ArrayList<>();

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

                campCommitteeMembers.add(new CampCommitteeMember(id, password, name, email, true, 0)); // Assuming isCampCommittee and points are fixed values
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return campCommitteeMembers;
    }

    // public static void main(String[] args) {
    //     // Test createCampCommitteeMember
    //     CampCommitteeMember newCCM = new CampCommitteeMember("CCM1", "password123", "John Doe", "john@example.com", true, 0);
    //     CampCommitteeMember newCCM2 = new CampCommitteeMember("CCM2", "password123", "Jane Doe", "john@example.com", true, 0);
    //     DB_CCM.createCampCommitteeMember(newCCM);
    //     DB_CCM.createCampCommitteeMember(newCCM2);

    //     // Test readCampCommitteeMember
    //     CampCommitteeMember retrievedCCM = DB_CCM.readCampCommitteeMember("CCM1");
    //     if (retrievedCCM != null) {
    //         System.out.println("Retrieved CampCommitteeMember: " + retrievedCCM.getName());
    //     } else {
    //         System.out.println("CampCommitteeMember not found.");
    //     }

    //     // Test updateCampCommitteeMember
    //     if (retrievedCCM != null) {
    //         retrievedCCM.setEmail("newemail@example.com");
    //         DB_CCM.updateCampCommitteeMember(retrievedCCM);
    //         System.out.println("CampCommitteeMember updated.");
    //     }

    //     // Test getAllCampCommitteeMembers
    //     System.out.println("\nAll CampCommitteeMembers:");
    //     ArrayList<CampCommitteeMember> allCCMs = DB_CCM.getAllCampCommitteeMembers();
    //     for (CampCommitteeMember ccm : allCCMs) {
    //         System.out.println(ccm.getName());
    //     }

    //     // Test deleteCampCommitteeMember
    //     DB_CCM.deleteCampCommitteeMember("CCM1");
    //     System.out.println("\nCampCommitteeMember deleted.");

    //     // Test getAllCampCommitteeMembers after deletion
    //     System.out.println("\nAll CampCommitteeMembers after Deletion:");
    //     ArrayList<CampCommitteeMember> ccmAfterDeletion = DB_CCM.getAllCampCommitteeMembers();
    //     for (CampCommitteeMember ccm : ccmAfterDeletion) {
    //         System.out.println(ccm.getName());
    //     }
    // }
}
