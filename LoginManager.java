import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class LoginManager {
    public static User obtainUserObject(String email, String password) {
        //check if user is an ccm or attendee
        User user = DB_Attendee.readAttendeeByEmail(email);
        if (user != null) {
            Attendee attendee = (Attendee) user;
            if(!attendee.getPassword().equals(password)) return null;
            if(attendee.getIsCampCommittee()) {
                CampCommitteeMember campCommitteeMember = DB_CCM.readCampCommitteeMember(attendee.getId());
                return campCommitteeMember;
            }
            return attendee;
        }
        //check if user is staff
        else {
            user = DB_Staff.readStaffByEmail(email);
            if (user != null) {
                Staff staff = (Staff) user;
                if (!staff.getPassword().equals(password)) return null;
                return staff;
            }
            return null;
        }
    }
}
