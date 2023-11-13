import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class LoginManager {

    public static boolean isValidLoginStudent(String username, String password) {
        try (FileInputStream file = new FileInputStream(DatabaseFilePaths.STUDENT);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming login details are in the first sheet
            for (Row row : sheet) {
                // Assuming the first cell contains the username and the second cell contains the password
                String storedUsername = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                String storedPassword = row.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

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
            String storedPassword = row.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

            if (username.equals(storedUsername) && password.equals(storedPassword)) {
                return true; // Credentials are valid
            }
        }
    } catch (IOException e) {
        e.printStackTrace(); // Handle the exception according to your needs
    }

    return false; // Credentials are not valid or an error occurred
    }

    // public static void main(String[] args) {
    //     // Example usage
    //     //boolean result = isValidLoginStudent("sally@mail.com", "password1");
    //     boolean result = isValidLoginStaff("mike@hotmail.com", "password");
    //     System.out.println("Login result: " + result);
    // }
}
