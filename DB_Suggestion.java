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

            Cell suggestionStrCell = newRow.createCell(2);
            suggestionStrCell.setCellValue(suggestion.getSuggestionStr());

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
                    String suggestionStr = row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                    return new Suggestion(id, isProcessed, suggestionStr);
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
                    row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(suggestion.getSuggestionStr());

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
                        if (i < sheet.getLastRowNum()){
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
                String suggestionStr = row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                suggestions.add(new Suggestion(id, isProcessed, suggestionStr));
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return suggestions;
    }

    // public static void main(String[] args) {
    //     // Test createSuggestion
    //     Suggestion suggestionOne = new Suggestion("suggestion1", false, "Improve communication in meetings");
    //     Suggestion suggestionTwo = new Suggestion("suggestion2", true, "Great job on event planning");

    //     DB_Suggestion.createSuggestion(suggestionOne);
    //     DB_Suggestion.createSuggestion(suggestionTwo);

    //     // Test readSuggestion
    //     Suggestion retrievedSuggestion = DB_Suggestion.readSuggestion("suggestion1");
    //     System.out.println("Retrieved Suggestion: " + retrievedSuggestion.getSuggestionStr());

    //     // Test updateSuggestion
    //     Suggestion updatedSuggestion = new Suggestion("suggestion1", true, "Effective communication in meetings");
    //     DB_Suggestion.updateSuggestion(updatedSuggestion);

    //     // Test getAllSuggestions
    //     System.out.println("\nAll Suggestions:");
    //     ArrayList<Suggestion> allSuggestions = DB_Suggestion.getAllSuggestions();
    //     for (Suggestion suggestion : allSuggestions) {
    //         System.out.println(suggestion.getSuggestionStr());
    //     }

    //     // Test deleteSuggestion
    //     DB_Suggestion.deleteSuggestion("suggestion1");

    //     // Test getAllSuggestions after deletion
    //     System.out.println("\nAll Suggestions after Deletion:");
    //     ArrayList<Suggestion> suggestionsAfterDeletion = DB_Suggestion.getAllSuggestions();
    //     for (Suggestion suggestion : suggestionsAfterDeletion) {
    //         System.out.println(suggestion.getSuggestionStr());
    //     }
    // }
}
