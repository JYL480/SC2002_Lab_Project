import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class LoginManager {

    public static boolean isValidLoginStudent(String email, String password) {
        Attendee attendee = DB_Attendee.readAttendeeByEmail(email);
        if(attendee == null)    return false; 
        if(!attendee.getPassword().equals(password))  return false;
        return true; 
    }

    public static boolean isValidLoginStaff(String email, String password) {
        Staff staff = DB_Staff.readStaffByEmail(email);
        if(staff == null)   return false;
        if(!staff.getPassword().equals(password))   return false;
        return true; 
    }


    // public static void main(String[] args) {
        // Attendee attendee = new Attendee("A123456", "password123", "John Doe", "john.doe@email.com", false);
        // Attendee anotherAttendee = new Attendee("B789012", "securePass", "Jane Smith", "jane.smith@email.com", true);
        // DB_Attendee.createAttendee(attendee);
        // DB_Attendee.createAttendee(anotherAttendee);
        // CampCommitteeMember ccm = new CampCommitteeMember("B789012", "securePass", "Jane Smith", "jane.smith@email.com", true, 5);
        // DB_CCM.createCampCommitteeMember(ccm);
        // Attendee a = DB_Attendee.readAttendeeByEmail("jane.smith@email.com");
        // CampCommitteeMember ccm = DB_CCM.readCampCommitteeMember(a.getId());
        // System.out.println(ccm.getName());
        // System.out.println(isValidLoginStudent("john.doe@email.com", "password123"));


    // }
}
