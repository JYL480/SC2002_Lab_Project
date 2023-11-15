import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DB_Performance {
    private static final String FILE_PATH = DatabaseFilePaths.PERFORMANCE;

    public static void createPerformance(Performance performance) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            // Find the last row number
            int lastRowNum = sheet.getLastRowNum();

            // Create a new row
            Row newRow = sheet.createRow(lastRowNum + 1);

            // Write performance data to the cells
            Cell idCell = newRow.createCell(0);
            idCell.setCellValue(performance.getId());

            Cell ratingCell = newRow.createCell(1);
            ratingCell.setCellValue(performance.getRating());

            Cell areasDoneWellCell = newRow.createCell(2);
            areasDoneWellCell.setCellValue(performance.getAreasDoneWell());

            Cell areasToImproveCell = newRow.createCell(3);
            areasToImproveCell.setCellValue(performance.getAreasToImprove());

            Cell isProcessedCell = newRow.createCell(4);
            isProcessedCell.setCellValue(performance.getIsProcessed());

            // Save the changes to the file
            try (FileOutputStream fileOut = new FileOutputStream(FILE_PATH)) {
                workbook.write(fileOut);
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }
    }

    public static Performance readPerformance(String performanceId) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String id = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (performanceId.equals(id)) {
                    // Found the performance
                    int rating = (int) row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue();
                    String areasDoneWell = row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                    String areasToImprove = row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                    boolean isProcessed = row.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getBooleanCellValue();

                    return new Performance(id, rating, areasDoneWell, areasToImprove, isProcessed);
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return null; // Performance not found
    }

    public static void updatePerformance(Performance performance) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String id = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (performance.getId().equals(id)) {
                    // Update the performance data in the row
                    row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(performance.getRating());
                    row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(performance.getAreasDoneWell());
                    row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(performance.getAreasToImprove());
                    row.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(performance.getIsProcessed());

                    // Save the changes to the file
                    try (FileOutputStream fileOut = new FileOutputStream(FILE_PATH)) {
                        workbook.write(fileOut);
                    }

                    return; // Performance updated
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        // Performance not found
    }

    public static void deletePerformance(String performanceId) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
            Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
                Row row = sheet.getRow(i);

                if (row != null) {
                    String id = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue().trim();

                    if (performanceId.trim().equals(id)) {
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

                        return; // Performance deleted
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        // Performance not found
    }

    public static ArrayList<Performance> getAllPerformances() {
        ArrayList<Performance> performances = new ArrayList<>();

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
                int rating = (int) row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue();
                String areasDoneWell = row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                String areasToImprove = row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                boolean isProcessed = row.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getBooleanCellValue();

                performances.add(new Performance(id, rating, areasDoneWell, areasToImprove, isProcessed));
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return performances;
    }
    
    // public static void main(String[] args) {
    //     // Test createPerformance
    //     Performance performanceOne = new Performance("performance1", 4, "Well done in teamwork", "Time management", true);
    //     Performance performanceTwo = new Performance("performance2", 5, "Excellent communication skills", "None", false);

    //     DB_Performance.createPerformance(performanceOne);
    //     DB_Performance.createPerformance(performanceTwo);

    //     // Test readPerformance
    //     Performance retrievedPerformance = DB_Performance.readPerformance("performance1");
    //     System.out.println("Retrieved Performance: " + retrievedPerformance.getAreasDoneWell());

    //     // Test updatePerformance
    //     Performance updatedPerformance = new Performance("performance1", 5, "Outstanding teamwork", "Time management", true);
    //     DB_Performance.updatePerformance(updatedPerformance);

    //     // Test getAllPerformances
    //     System.out.println("\nAll Performances:");
    //     ArrayList<Performance> allPerformances = DB_Performance.getAllPerformances();
    //     for (Performance performance : allPerformances) {
    //         System.out.println(performance.getAreasDoneWell());
    //     }

    //     // Test deletePerformance
    //     DB_Performance.deletePerformance("performance1");

    //     // Test getAllPerformances after deletion
    //     System.out.println("\nAll Performances after Deletion:");
    //     ArrayList<Performance> performancesAfterDeletion = DB_Performance.getAllPerformances();
    //     for (Performance performance : performancesAfterDeletion) {
    //         System.out.println(performance.getAreasDoneWell());
    //     }
    // }
}
