import java.util.ArrayList;


import org.apache.poi.ss.usermodel.*;
public class DB_Staff extends DB_Base<Staff>{
    private static final String FILE_PATH = DatabaseFilePaths.STAFF;
    private static DB_Staff instance = new DB_Staff();

    public DB_Staff() {
        super(FILE_PATH);
    }

    @Override
    protected Staff createEntity(Row row) {
        String id = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
        String email = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
        String name = row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
        String password = row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
        boolean isNewLogin = row.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getBooleanCellValue();
        String facultyId = row.getCell(5, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

        return new Staff(id, password, name, email, isNewLogin, facultyId);
    }

    @Override
    protected void writeEntityToRow(Row row, Staff staff) {
        row.createCell(0).setCellValue(staff.getId());
        row.createCell(1).setCellValue(staff.getEmail());
        row.createCell(2).setCellValue(staff.getName());
        row.createCell(3).setCellValue(staff.getPassword());
        row.createCell(4).setCellValue(staff.isNewLogin());
        row.createCell(5).setCellValue(staff.getFacultyId());
    }

    public static void createStaff(Staff staff) {
        instance.create(staff);
    }

    public static Staff readStaff(String staffId) {
        return instance.read(staffId, 0);
    }

    public static Staff readStaffByEmail(String staffEmail) {
        return instance.read(staffEmail, 1);
    }

    public static void updateStaff(Staff staff) {
       instance.update(staff.getId(), 0, staff);
    }

    public static void deleteStaff(String staffId) {
        instance.delete(staffId, 0);
    }

    public static ArrayList<Staff> getAllStaff() {
        return instance.getAll();
    }

    // Additional methods specific to Staff functionality can be added as needed
    // public static void main(String[] args) {
    //     // Test createStaff
    //     Staff staffOne = new Staff("staff1", "password1", "John Doe", "john@example.com");
    //     Staff staffTwo = new Staff("staff2", "password2", "Jane Smith", "jane@example.com");

    //     DB_Staff.createStaff(staffOne);
    //     DB_Staff.createStaff(staffTwo);

    //     // Test readStaff
    //     Staff retrievedStaff = DB_Staff.readStaff("staff1");
    //     System.out.println("Retrieved Staff: " + retrievedStaff.getName());

    //     // Test updateStaff
    //     Staff updatedStaff = new Staff("staff1", "newPassword", "John Doe Updated", "john@example.com");
    //     DB_Staff.updateStaff(updatedStaff);

    //     // Test getAllStaff
    //     System.out.println("\nAll Staff:");
    //     ArrayList<Staff> allStaff = DB_Staff.getAllStaff();
    //     for (Staff staff : allStaff) {
    //         System.out.println(staff.getName());
    //     }

    //     // Test deleteStaff
    //     DB_Staff.deleteStaff("staff1");

    //     // Test getAllStaff after deletion
    //     System.out.println("\nAll Staff after Deletion:");
    //     ArrayList<Staff> staffAfterDeletion = DB_Staff.getAllStaff();
    //     for (Staff staff : staffAfterDeletion) {
    //         System.out.println(staff.getName());
    //     }
    // }

    public static void main(String[] args) {
        deleteStaff("a0881d6f93b64f29bfe02267cf9dc4c7");
    }
}
