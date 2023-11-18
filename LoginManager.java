import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileOutputStream;

/*
Might need a rewrite! if attendee table shows that it is alos a CCM, you need to retreive from CCM databaes shit like CCM points

*/

public class LoginManager {


    public static boolean isValidLoginAttendee(String username, String password) {
        return isValidLogin(username, password, DatabaseFilePaths.ATTENDEE);
    }

    public static boolean isValidLoginCCM(String username, String password) {
        return isValidLogin(username, password, DatabaseFilePaths.CCM);
    }

    public static boolean isValidLoginStaff(String username, String password) {
        return isValidLogin(username, password, DatabaseFilePaths.STAFF);
    }

    public static void updatePasswordAttendee(String username, String newPassword) {
        updatePassword(DatabaseFilePaths.ATTENDEE, username, newPassword);
    }

    public static void updatePasswordStaff(String username, String newPassword) {
        updatePassword(DatabaseFilePaths.STAFF, username, newPassword);
    }

    public static void updatePasswordCCM(String username, String newPassword) {
        updatePassword(DatabaseFilePaths.CCM, username, newPassword);
    }

    public static boolean isValidLogin(String username, String password, String filePath) {

        try (FileInputStream file = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming login details are in the first sheet
            for (Row row : sheet) {
                // Assuming the first cell contains the username and the second cell contains the password
                String storedUsername = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                String storedPassword = row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (username.equals(storedUsername) && password.equals(storedPassword)) {
                    return true; // Credentials are valid
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return false; // Credentials are not valid or an error occurred
    }

    private static void updatePassword(String filePath, String username, String newPassword) {
        try (FileInputStream file = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming login details are in the first sheet
            for (Row row : sheet) {
                // Assuming the first cell contains the username and the second cell contains the password
                Cell usernameCell = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                Cell passwordCell = row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

                if (username.equals(usernameCell.getStringCellValue())) {
                    // Found the username, update the password
                    passwordCell.setCellValue(newPassword);

                    // Save the changes to the file
                    try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                        workbook.write(fileOut);
                    }

                    return; // Password updated
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        // Username not found, or an error occurred
    }

    //public static void main(String[] args) {
        // Example usage
        //boolean result = isValidLoginStudent("sally@mail.com", "password1");
        // boolean result1 = isValidLoginStaff("mike@hotmail.com", "password");
        // System.out.println("Login result: " + result1);
        // boolean result2 = isValidLoginStaff("mike@hdotmail.com", "password");
        // System.out.println("Login result: " + result2);
        // result2 = isValidLoginCCM("sally@mail.com", "password1");
        // System.out.println("Login result: " + result2);
        // result2 = isValidLoginAttendee("sally@mail.com", "password1");
        // System.out.println("Login result: " + result2);

    //}
}
