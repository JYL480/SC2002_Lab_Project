import java.util.ArrayList;

import org.apache.poi.ss.usermodel.*;

public class DB_Student extends DB_Base<Student> {
    private static final String FILE_PATH = DatabaseFilePaths.STUDENT;
    private static final DB_Student instance = new DB_Student();

    public DB_Student() {
        super(FILE_PATH);
    }

    @Override
    protected Attendee createEntity(Row row) {
        String id = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
        String email = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
        String name = row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
        String password = row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
        boolean isCampCommittee = row.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getBooleanCellValue();
        boolean isNewLogin = row.getCell(5, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getBooleanCellValue();
        String facultyId = row.getCell(6, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
        return new Attendee(id, password, name, email, isCampCommittee, isNewLogin, facultyId);
        
    }

    @Override
    protected void writeEntityToRow(Row row, Student student) {
        row.createCell(0).setCellValue(student.getId());
        row.createCell(1).setCellValue(student.getEmail());
        row.createCell(2).setCellValue(student.getName());
        row.createCell(3).setCellValue(student.getPassword());
        row.createCell(4).setCellValue(student.getIsCampCommittee());
        row.createCell(5).setCellValue(student.isNewLogin());
        row.createCell(6).setCellValue(student.getFacultyId());

    }
    
    public static void createStudent(Student student) {
        instance.create(student);
    }

    public static Student readStudent(String studentId) {
        return instance.read(studentId, 0);
    }

    public static Student readStudentByEmail(String studentEmail) {
        return instance.read(studentEmail, 1);

    }

    public static void updateStudent(Student student, int columnIndex) {
        instance.update(student.getId(), columnIndex, student);
    }

    public static void deleteStudent(String studentId) {
        instance.delete(studentId, 0);
    }

    public static ArrayList<Student> getAllStudents() {
        return instance.getAll();
    }



    // Add other methods as needed
    // public static void main(String[] args) {
    //     // Test createAttendee
    //     Attendee newAttendee = new Attendee("TEST123", "testpassword", "Test User", "test@example.com", false);
    //     Attendee newAttendee2 = new Attendee("TasdfT123", "testpassword", "Giga User", "test@example.com", false);

    //     DB_Attendee.createAttendee(newAttendee);
    //      DB_Attendee.createAttendee(newAttendee2);

    //     // Test readAttendee
    //     Attendee retrievedAttendee = DB_Attendee.readAttendee("TEST123");
    //     System.out.println("Retrieved Attendee: " + retrievedAttendee.getName() + " - " + retrievedAttendee.getEmail());

    //     // Test updateAttendee
    //     Attendee updatedAttendee = new Attendee("TEST123", "newpassword", "Updated User", "updated@example.com", false);
    //     DB_Attendee.updateAttendee(updatedAttendee);

    //     // Test readAttendee after update
    //     Attendee updatedRetrievedAttendee = DB_Attendee.readAttendee("TEST123");
    //     System.out.println("Updated Retrieved Attendee: " + updatedRetrievedAttendee.getName() + " - " + updatedRetrievedAttendee.getEmail());

    //     // Test getAllAttendees
    //     System.out.println("All Attendees:");
    //     ArrayList<Attendee> allAttendees = DB_Attendee.getAllAttendees();
    //     for (Attendee attendee : allAttendees) {
    //         System.out.println("ID: " + attendee.getId() + ", Name: " + attendee.getName() + ", Email: " + attendee.getEmail());
    //     }

    //     // Test deleteAttendee
    //     DB_Attendee.deleteAttendee("TEST123");

    //     // Test getAllAttendees after deletion
    //     System.out.println("\nAll Attendees after Deletion:");
    //     ArrayList<Attendee> attendeesAfterDeletion = DB_Attendee.getAllAttendees();
    //     for (Attendee attendee : attendeesAfterDeletion) {
    //         System.out.println("ID: " + attendee.getId() + ", Name: " + attendee.getName() + ", Email: " + attendee.getEmail());
    //     }
    // }


    // public static void main(String[] args) {
    //         try (FileInputStream file = new FileInputStream(FILE_PATH);
    //              Workbook workbook = new XSSFWorkbook(file)) {
        
    //             Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet
        
    //             int lastRowNum = sheet.getLastRowNum();
        
    //             for (int i = 0; i <= lastRowNum; i++) {
    //                 Row row = sheet.getRow(i);
        
    //                 if (row != null) {
    //                     sheet.removeRow(row);
        
    //                     // If the row is not the last row, shift the remaining rows up to fill the gap
    //                     if (i < lastRowNum) {
    //                         sheet.shiftRows(i + 1, lastRowNum, -1);
    //                     }
        
    //                     // Adjust the row index after deletion
    //                     i--;
    //                 }
    //             }
        
    //             // Save the changes to the file
    //             try (FileOutputStream fileOut = new FileOutputStream(FILE_PATH)) {
    //                 workbook.write(fileOut);
    //             }
        
    //         } catch (IOException e) {
    //             e.printStackTrace(); // Handle the exception according to your needs
    //         }
    // }

    // public static void main(String[] args) {
    //      try (FileInputStream file = new FileInputStream(FILE_PATH);
    //          Workbook workbook = new XSSFWorkbook(file)) {

    //         Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

    //         Iterator<Row> iterator = sheet.iterator();
    //         iterator.next();
    //             Row row = iterator.next();
    //             row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue("john.doe@mail.com");
    //             row = iterator.next();
    //             row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue("jane.smith@mail.com");
    
    //         try (FileOutputStream fileOut = new FileOutputStream(FILE_PATH)) {
    //                 workbook.write(fileOut);
    //             }

    //     } catch (IOException e) {
    //         e.printStackTrace(); // Handle the exception according to your needs
    //     }

    //     return; // Attendee not found
    // }
}
