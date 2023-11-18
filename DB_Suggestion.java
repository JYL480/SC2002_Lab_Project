import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DB_Suggestion {
    private static final String FILE_PATH = DatabaseFilePaths.SUGGESTION;

    public static void createSuggestion(Suggestion suggestion) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            // Find the last row number
            int lastRowNum = sheet.getLastRowNum();

            // Create a new row
            Row newRow = sheet.createRow(lastRowNum + 1);

            // Write suggestion data to the cells
            Cell idCell = newRow.createCell(0);
            idCell.setCellValue(suggestion.getId());

            Cell isProcessedCell = newRow.createCell(1);
            isProcessedCell.setCellValue(suggestion.getIsProcessed());

            Cell isApprovedCell = newRow.createCell(2); // Assuming isApproved is the next column
            isApprovedCell.setCellValue(suggestion.getIsApproved());

            Cell campIdCell = newRow.createCell(3);
            campIdCell.setCellValue(suggestion.getCampId());

            Cell newCampNameCell = newRow.createCell(4);
            newCampNameCell.setCellValue(suggestion.getNewCampname());

            Cell newCampIsVisibleCell = newRow.createCell(5);
            newCampIsVisibleCell.setCellValue(suggestion.isNewCampisVisible());

            Cell newCampStartDateCell = newRow.createCell(6);
            newCampStartDateCell.setCellValue(suggestion.getNewCampStartDate());

            Cell newCampEndDateCell = newRow.createCell(7);
            newCampEndDateCell.setCellValue(suggestion.getNewCampEndDate());

            Cell newRegClosingDateCell = newRow.createCell(8);
            newRegClosingDateCell.setCellValue(suggestion.getNewRegClosingDate());

            Cell newLocationCell = newRow.createCell(9);
            newLocationCell.setCellValue(suggestion.getNewLocation());

            Cell newTotalSlotsCell = newRow.createCell(10);
            newTotalSlotsCell.setCellValue(suggestion.getNewTotalSlots());

            Cell newCampCommitteeSlotsCell = newRow.createCell(11);
            newCampCommitteeSlotsCell.setCellValue(suggestion.getNewCampCommitteeSlots());

            Cell newDescriptionCell = newRow.createCell(12);
            newDescriptionCell.setCellValue(suggestion.getNewDescription());

            // Save the changes to the file
            try (FileOutputStream fileOut = new FileOutputStream(FILE_PATH)) {
                workbook.write(fileOut);
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }
    }

    public static Suggestion readSuggestion(String suggestionId) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String id = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (suggestionId.equals(id)) {
                    // Found the suggestion
                    boolean isProcessed = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getBooleanCellValue();
                    boolean isApproved = row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getBooleanCellValue();
                    String campId = row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                    String newCampName = row.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                    boolean newCampIsVisible = row.getCell(5, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getBooleanCellValue();
                    String newCampStartDate = row.getCell(6, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                    String newCampEndDate = row.getCell(7, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                    String newRegClosingDate = row.getCell(8, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                    String newLocation = row.getCell(9, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                    int newTotalSlots = (int) row.getCell(10, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue();
                    int newCampCommitteeSlots = (int) row.getCell(11, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue();
                    String newDescription = row.getCell(12, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                    return new Suggestion(suggestionId, isProcessed, isApproved, campId, newCampName, newCampIsVisible,
                            newCampStartDate, newCampEndDate, newRegClosingDate, newLocation, newTotalSlots,
                            newCampCommitteeSlots, newDescription);
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return null; // Suggestion not found
    }

    public static void updateSuggestion(Suggestion suggestion) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String id = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (suggestion.getId().equals(id)) {
                    // Update the suggestion data in the row
                    row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(suggestion.getIsProcessed());
                    row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(suggestion.getIsApproved());
                    row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(suggestion.getCampId());
                    row.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(suggestion.getNewCampname());
                    row.getCell(5, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(suggestion.isNewCampisVisible());
                    row.getCell(6, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(suggestion.getNewCampStartDate());
                    row.getCell(7, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(suggestion.getNewCampEndDate());
                    row.getCell(8, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(suggestion.getNewRegClosingDate());
                    row.getCell(9, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(suggestion.getNewLocation());
                    row.getCell(10, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(suggestion.getNewTotalSlots());
                    row.getCell(11, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(suggestion.getNewCampCommitteeSlots());
                    row.getCell(12, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(suggestion.getNewDescription());

                    // Save the changes to the file
                    try (FileOutputStream fileOut = new FileOutputStream(FILE_PATH)) {
                        workbook.write(fileOut);
                    }

                    return; // Suggestion updated
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        // Suggestion not found
    }

    public static void deleteSuggestion(String suggestionId) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
                Row row = sheet.getRow(i);

                if (row != null) {
                    String id = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue().trim();

                    if (suggestionId.trim().equals(id)) {
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

                        return; // Suggestion deleted
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        // Suggestion not found
    }

    public static ArrayList<Suggestion> getAllSuggestions() {
        ArrayList<Suggestion> suggestions = new ArrayList<>();

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
                boolean isProcessed = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getBooleanCellValue();
                boolean isApproved = row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getBooleanCellValue();
                String campId = row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                String newCampName = row.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                boolean newCampIsVisible = row.getCell(5, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getBooleanCellValue();
                String newCampStartDate = row.getCell(6, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                String newCampEndDate = row.getCell(7, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                String newRegClosingDate = row.getCell(8, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                String newLocation = row.getCell(9, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                int newTotalSlots = (int) row.getCell(10, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue();
                int newCampCommitteeSlots = (int) row.getCell(11, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue();
                String newDescription = row.getCell(12, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                suggestions.add(new Suggestion(id, isProcessed, isApproved, campId, newCampName, newCampIsVisible,
                        newCampStartDate, newCampEndDate, newRegClosingDate, newLocation, newTotalSlots,
                        newCampCommitteeSlots, newDescription));
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return suggestions;
    }

    // public static void main(String[] args) {
    //     // Test createSuggestion
    //     Suggestion suggestionOne = new Suggestion(RandomIdGenerator.generateRandomId(), false, true, "campId1",
    //             "New Camp One", true, "2023-08-01", "2023-08-15", "2023-07-15", "New Location One", 50, 5, "New Description One");
    //     Suggestion suggestionTwo = new Suggestion(RandomIdGenerator.generateRandomId(), true, false, "campId2",
    //             "New Camp Two", false, "2023-08-01", "2023-08-15", "2023-07-15", "New Location Two", 75, 8, "New Description Two");
    //     Suggestion suggestionThree = new Suggestion(RandomIdGenerator.generateRandomId(), false, true, "campId3",
    //             "New Camp Three", true, "2023-08-01", "2023-08-15", "2023-07-15", "New Location Three", 60, 6, "New Description Three");

    //     DB_Suggestion.createSuggestion(suggestionOne);
    //     DB_Suggestion.createSuggestion(suggestionTwo);
    //     DB_Suggestion.createSuggestion(suggestionThree);

    //     // Test readSuggestion
    //     Suggestion retrievedSuggestion = DB_Suggestion.readSuggestion(suggestionOne.getId());
    //     System.out.println("Retrieved Suggestion: " + retrievedSuggestion.getNewCampname());

    //     // Test updateSuggestion
    //     Suggestion updatedSuggestion = new Suggestion(suggestionOne.getId(), true, false, "updatedCampId",
    //             "Updated Camp", false, "2023-09-01", "2023-09-15", "2023-08-15", "Updated Location", 80, 7, "udpated descrition");

    //     DB_Suggestion.updateSuggestion(updatedSuggestion);

    //     // Test getAllSuggestions
    //     System.out.println("All Suggestions:");
    //     ArrayList<Suggestion> allSuggestions = DB_Suggestion.getAllSuggestions();
    //     for (Suggestion suggestion : allSuggestions) {
    //         System.out.println(suggestion.getNewCampname());
    //     }

    //     // Test deleteSuggestion
    //     DB_Suggestion.deleteSuggestion(suggestionTwo.getId());

    //     // Test getAllSuggestions after deletion
    //     System.out.println("\nAll Suggestions after Deletion:");
    //     ArrayList<Suggestion> suggestionsAfterDeletion = DB_Suggestion.getAllSuggestions();
    //     for (Suggestion suggestion : suggestionsAfterDeletion) {
    //         System.out.println(suggestion.getNewCampname());
    //     }
    // }
}
