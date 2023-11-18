import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class LoginManager {

    public static boolean isValidLoginStudent(String email, String password) {
        return isValidLogin(email, password, DatabaseFilePaths.ATTENDEE);
    }

    public static boolean isValidLoginStaff(String email, String password) {
        return isValidLogin(email, password, DatabaseFilePaths.STAFF);
    }

    public static void updatePasswordStudent(String email, String newPassword) {
        updatePassword(DatabaseFilePaths.ATTENDEE, email, newPassword);
    }

    public static void updatePasswordStaff(String email, String newPassword) {
        updatePassword(DatabaseFilePaths.STAFF, email, newPassword);
    }

    public static boolean isValidLogin(String email, String password, String filePath) {
        try (FileInputStream file = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming login details are in the first sheet
            for (Row row : sheet) {
                String storedEmail = getStringCellValue(row.getCell(1));
                String storedPassword = getStringCellValue(row.getCell(3));

                if (email.equals(storedEmail) && password.equals(storedPassword)) {
                    return true; // Credentials are valid
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return false; // Credentials are not valid or an error occurred
    }

    private static void updatePassword(String filePath, String email, String newPassword) {
        try (FileInputStream file = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming login details are in the first sheet
            for (Row row : sheet) {
                Cell emailCell = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                Cell passwordCell = row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

                if (email.equals(getStringCellValue(emailCell))) {
                    // Found the email, update the password
                    passwordCell.setCellValue(newPassword);
                    saveWorkbook(workbook, filePath);
                    return; // Password updated
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        // Email not found, or an error occurred
    }
    public static Attendee readAttendeeByEmail(String attendeeEmail) {
        try (FileInputStream file = new FileInputStream(DatabaseFilePaths.ATTENDEE);
            Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String email = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (attendeeEmail.equals(email)) {
                    // Found the attendee
                    String id = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                    String name = row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                    String password = row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                    boolean isCampCommittee = row.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getBooleanCellValue();

                    return new Attendee(id, password, name, email, isCampCommittee);
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return null; // Attendee not found
    }



    public static Staff readStaffByEmail(String staffEmail) {
        try (FileInputStream file = new FileInputStream(DatabaseFilePaths.STAFF);
            Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String email = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (staffEmail.equals(email)) {
                    // Found the staff
                    String id = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                    String name = row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                    String password = row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                    return new Staff(id, password, name, email);
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return null; // Staff not found
    }



    private static String getStringCellValue(Cell cell) {
        return cell.getStringCellValue().trim();
    }

    private static void saveWorkbook(Workbook workbook, String filePath) throws IOException {
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        }
    }

    // public static void main(String[] args) {
    //     // Attendee attendee = new Attendee("A123456", "password123", "John Doe", "john.doe@email.com", false);
    //     // Attendee anotherAttendee = new Attendee("B789012", "securePass", "Jane Smith", "jane.smith@email.com", true);
    //     // DB_Attendee.createAttendee(attendee);
    //     // DB_Attendee.createAttendee(anotherAttendee);
    //     // CampCommitteeMember ccm = new CampCommitteeMember("B789012", "securePass", "Jane Smith", "jane.smith@email.com", true, 5);
    //     // DB_CCM.createCampCommitteeMember(ccm);
    //     Attendee a = readAttendeeByEmail("jane.smith@email.com");
    //     CampCommitteeMember ccm = DB_CCM.readCampCommitteeMember(a.getId());
    //     System.out.println(ccm.getName());


    // }
}
