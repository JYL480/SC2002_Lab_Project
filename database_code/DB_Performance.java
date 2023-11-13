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
            newRow.createCell(0).setCellValue(performance.id);
            newRow.createCell(1).setCellValue(performance.rating);
            newRow.createCell(2).setCellValue(performance.areasDoneWell);
            newRow.createCell(3).setCellValue(performance.areasToImprove);
            newRow.createCell(4).setCellValue(performance.isProcessed);

            // Save the changes to the file
            try (FileOutputStream fileOut = new FileOutputStream(FILE_PATH)) {
                workbook.write(fileOut);
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }
    }

    public static Performance getPerformance(String id) {
        Performance performance = new Performance();

        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String rowId = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (id.equals(rowId)) {
                    // Found performance data for the given id
                    performance.id = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                    performance.rating = (int) row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue();
                    performance.areasDoneWell = row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                    performance.areasToImprove = row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                    performance.isProcessed = row.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getBooleanCellValue();

                    return performance;
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return performance; // Performance data not found
    }

    public static void updatePerformance(Performance updatedPerformance) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String rowId = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (updatedPerformance.id.equals(rowId)) {
                    // Update the performance data in the row
                    row.getCell(1).setCellValue(updatedPerformance.rating);
                    row.getCell(2).setCellValue(updatedPerformance.areasDoneWell);
                    row.getCell(3).setCellValue(updatedPerformance.areasToImprove);
                    row.getCell(4).setCellValue(updatedPerformance.isProcessed);

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

    public static void deletePerformance(String id) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String rowId = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (id.equals(rowId)) {
                    // Remove the row
                    iterator.remove();

                    // Save the changes to the file
                    try (FileOutputStream fileOut = new FileOutputStream(FILE_PATH)) {
                        workbook.write(fileOut);
                    }

                    return; // Performance deleted
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        // Performance not found
    }

    public static ArrayList<Performance> getAllPerformances() {
        ArrayList<Performance> allPerformances = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();

                Performance performance = new Performance();
                performance.id = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                performance.rating = (int) row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue();
                performance.areasDoneWell = row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                performance.areasToImprove = row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                performance.isProcessed = row.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getBooleanCellValue();

                allPerformances.add(performance);
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return allPerformances;
    }
}
