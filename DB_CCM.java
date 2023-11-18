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

            Cell pointsCell = newRow.createCell(1);
            pointsCell.setCellValue(ccm.getPoints());

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
                    int points = (int) row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue();
                    Attendee attendee = DB_Attendee.readAttendee(ccmId);
                    return new CampCommitteeMember(id, 
                                                    attendee.getPassword(),
                                                    attendee.getName(),
                                                    attendee.getEmail(), 
                                                    attendee.getIsCampCommittee(), 
                                                    points); 
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
                    row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(ccm.getPoints());

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
                int points = (int) row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue();

                Attendee attendee = DB_Attendee.readAttendee(id);
                campCommitteeMembers.add(new CampCommitteeMember(
                    id, 
                     attendee.getPassword(),
                     attendee.getName(),
                     attendee.getEmail(), 
                      attendee.getIsCampCommittee(), 
                    points)); 
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return campCommitteeMembers;
    }

    // public static void main(String[] args) {
    //     // Test createCampCommitteeMember
    //     CampCommitteeMember newCCM = new CampCommitteeMember("CCM1", 0);
    //     CampCommitteeMember newCCM2 = new CampCommitteeMember("CCM2", 0);
    //     DB_CCM.createCampCommitteeMember(newCCM);
    //     DB_CCM.createCampCommitteeMember(newCCM2);

    //     // Test readCampCommitteeMember
    //     CampCommitteeMember retrievedCCM = DB_CCM.readCampCommitteeMember("CCM1");
    //     if (retrievedCCM != null) {
    //         System.out.println("Retrieved CampCommitteeMember: " + retrievedCCM.getId());
    //     } else {
    //         System.out.println("CampCommitteeMember not found.");
    //     }

    //     // Test updateCampCommitteeMember
    //     if (retrievedCCM != null) {
    //         retrievedCCM.setPoints(100);
    //         DB_CCM.updateCampCommitteeMember(retrievedCCM);
    //         System.out.println("CampCommitteeMember updated.");
    //     }

    //    
}