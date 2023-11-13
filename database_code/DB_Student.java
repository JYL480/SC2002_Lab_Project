import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DB_Student {
    private static final String FILE_PATH = DatabaseFilePaths.STUDENT;

    public static void createStudent(Student student) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            // Find the last row number
            int lastRowNum = sheet.getLastRowNum();

            // Create a new row
            Row newRow = sheet.createRow(lastRowNum + 1);

            // Write student data to the cells
            Cell idCell = newRow.createCell(0);
            idCell.setCellValue(student.getId());

            Cell emailCell = newRow.createCell(1);
            emailCell.setCellValue(student.getEmail());

            Cell nameCell = newRow.createCell(2);
            nameCell.setCellValue(student.getName());

            Cell facultyCell = newRow.createCell(3);
            facultyCell.setCellValue(student.getFaculty());

            Cell passwordCell = newRow.createCell(4);
            passwordCell.setCellValue(student.getPassword());

            Cell isCampCommitteeCell = newRow.createCell(5);
            isCampCommitteeCell.setCellValue(student.isCampCommittee());

            Cell pointsCell = newRow.createCell(6);
            pointsCell.setCellValue(student.getPoints());

            // Save the changes to the file
            try (FileOutputStream fileOut = new FileOutputStream(FILE_PATH)) {
                workbook.write(fileOut);
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }
    }

    public static Student readStudent(String studentId) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String id = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (studentId.equals(id)) {
                    // Found the student
                    String email = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                    String name = row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                    String faculty = row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                    String password = row.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                    boolean isCampCommittee = row.getCell(5, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getBooleanCellValue();
                    double points = row.getCell(6, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue();

                    return new Student(id, email, name, faculty, password, isCampCommittee, points);
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return null; // Student not found
    }

    public static void updateStudent(Student student) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String id = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (student.getId().equals(id)) {
                    // Update the student data in the row
                    row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(student.getEmail());
                    row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(student.getName());
                    row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(student.getFaculty());
                    row.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(student.getPassword());
                    row.getCell(5, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(student.isCampCommittee());
                    row.getCell(6, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(student.getPoints());

                    // Save the changes to the file
                    try (FileOutputStream fileOut = new FileOutputStream(FILE_PATH)) {
                        workbook.write(fileOut);
                    }

                    return; // Student updated
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        // Student not found
    }

    public static void deleteStudent(String studentId) {
        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String id = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (studentId.equals(id)) {
                    // Remove the row
                    iterator.remove();

                    // Save the changes to the file
                    try (FileOutputStream fileOut = new FileOutputStream(FILE_PATH)) {
                        workbook.write(fileOut);
                    }

                    return; // Student deleted
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        // Student not found
    }

    public static ArrayList<Student> getAllStudents() {
        ArrayList<Student> students = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String id = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                String email = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                String name = row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                String faculty = row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                String password = row.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                boolean isCampCommittee = row.getCell(5, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getBooleanCellValue();
                double points = row.getCell(6, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue();

                students.add(new Student(id, email, name, faculty, password, isCampCommittee, points));
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return students;
    }
}
