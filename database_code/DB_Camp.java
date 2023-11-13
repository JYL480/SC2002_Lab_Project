import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DB_Camp {
    private static final String FILE_PATH = DatabaseFilePaths.CAMP;

    public static void createCamp(Camp camp) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            // Find the last row number
            int lastRowNum = sheet.getLastRowNum();

            // Create a new row
            Row newRow = sheet.createRow(lastRowNum + 1);

            // Write camp data to the cells
            Cell idCell = newRow.createCell(0);
            idCell.setCellValue(camp.getId());

            Cell nameCell = newRow.createCell(1);
            nameCell.setCellValue(camp.getName());

            Cell isVisibleCell = newRow.createCell(2);
            isVisibleCell.setCellValue(camp.isVisible());

            Cell startDateCell = newRow.createCell(3);
            startDateCell.setCellValue(camp.getStartDate().toString()); // Assuming getStartDate returns DateTime as String

            Cell endDateCell = newRow.createCell(4);
            endDateCell.setCellValue(camp.getEndDate().toString()); // Assuming getEndDate returns DateTime as String

            Cell regClosingDateCell = newRow.createCell(5);
            regClosingDateCell.setCellValue(camp.getRegClosingDate().toString()); // Assuming getRegClosingDate returns DateTime as String

            Cell locationCell = newRow.createCell(6);
            locationCell.setCellValue(camp.getLocation());

            Cell totalSlotsCell = newRow.createCell(7);
            totalSlotsCell.setCellValue(camp.getTotalSlots());

            Cell campCommitteeSlotsCell = newRow.createCell(8);
            campCommitteeSlotsCell.setCellValue(camp.getCampCommitteeSlots());

            Cell descriptionCell = newRow.createCell(9);
            descriptionCell.setCellValue(camp.getDescription());

            // Save the changes to the file
            try (FileOutputStream fileOut = new FileOutputStream(FILE_PATH)) {
                workbook.write(fileOut);
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }
    }

    public static Camp readCamp(String campId) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String id = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (campId.equals(id)) {
                    // Found the camp
                    String name = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                    boolean isVisible = row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getBooleanCellValue();
                    // Assuming getStartDate, getEndDate, getRegClosingDate return DateTime as String
                    String startDate = row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                    String endDate = row.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                    String regClosingDate = row.getCell(5, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                    String location = row.getCell(6, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                    int totalSlots = (int) row.getCell(7, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue();
                    int campCommitteeSlots = (int) row.getCell(8, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue();
                    String description = row.getCell(9, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                    return new Camp(id, name, isVisible, startDate, endDate, regClosingDate, location, totalSlots, campCommitteeSlots, description);
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return null; // Camp not found
    }

    public static void updateCamp(Camp camp) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String id = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (camp.getId().equals(id)) {
                    // Update the camp data in the row
                    row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(camp.getName());
                    row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(camp.isVisible());
                    row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(camp.getStartDate().toString());
                    row.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(camp.getEndDate().toString());
                    row.getCell(5, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(camp.getRegClosingDate().toString());
                    row.getCell(6, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(camp.getLocation());
                    row.getCell(7, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(camp.getTotalSlots());
                    row.getCell(8, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(camp.getCampCommitteeSlots());
                    row.getCell(9, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(camp.getDescription());

                    // Save the changes to the file
                    try (FileOutputStream fileOut = new FileOutputStream(FILE_PATH)) {
                        workbook.write(fileOut);
                    }

                    return; // Camp updated
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        // Camp not found
    }

    public static void deleteCamp(String campId) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String id = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (campId.equals(id)) {
                    // Remove the row
                    iterator.remove();

                    // Save the changes to the file
                    try (FileOutputStream fileOut = new FileOutputStream(FILE_PATH)) {
                        workbook.write(fileOut);
                    }

                    return; // Camp deleted
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        // Camp not found
    }

    public static ArrayList<Camp> getAllCamps() {
        ArrayList<Camp> camps = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String id = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                String name = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                boolean isVisible = row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getBooleanCellValue();
                // Assuming getStartDate, getEndDate, getRegClosingDate return DateTime as String
                String startDate = row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                String endDate = row.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                String regClosingDate = row.getCell(5, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                String location = row.getCell(6, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                int totalSlots = (int) row.getCell(7, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue();
                int campCommitteeSlots = (int) row.getCell(8, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue();
                String description = row.getCell(9, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                camps.add(new Camp(id, name, isVisible, startDate, endDate, regClosingDate, location, totalSlots, campCommitteeSlots, description));
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return camps;
    }
}
