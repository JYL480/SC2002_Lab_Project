import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class LoginManager {

    public static boolean isValidLoginAttendee(String username, String password) {
        try (FileInputStream file = new FileInputStream(DatabaseFilePaths.ATTENDEE);
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

    public static boolean isValidLoginCCM(String username, String password) {
        try (FileInputStream file = new FileInputStream(DatabaseFilePaths.CCM);
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

    public static boolean isValidLoginStaff(String username, String password) {
    try (FileInputStream file = new FileInputStream(DatabaseFilePaths.STAFF);
        Workbook workbook = new XSSFWorkbook(file)) {

        Sheet sheet = workbook.getSheetAt(0); // Assuming login details are in the first sheet
        for (Row row : sheet) {
            // Assuming the first cell contains the username and the second cell contains the password
            String storedUsername = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
            String storedPassword = row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
            //System.out.println(storedPassword);
            if (username.equals(storedUsername) && password.equals(storedPassword)) {
                //System.out.println(storedUsername);
                return true; // Credentials are valid
            }
        }
    } catch (IOException e) {
        e.printStackTrace(); // Handle the exception according to your needs
    }

    return false; // Credentials are not valid or an error occurred
    }

    public static void main(String[] args) {
        // Example usage
        //boolean result = isValidLoginStudent("sally@mail.com", "password1");
        boolean result1 = isValidLoginStaff("mike@hotmail.com", "password");
        System.out.println("Login result: " + result1);
        boolean result2 = isValidLoginStaff("mike@hdotmail.com", "password");
        System.out.println("Login result: " + result2);
        result2 = isValidLoginCCM("sally@mail.com", "password1");
        System.out.println("Login result: " + result2);
        result2 = isValidLoginAttendee("sally@mail.com", "password1");
        System.out.println("Login result: " + result2);
    }
}
