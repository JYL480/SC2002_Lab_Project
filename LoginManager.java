
public class LoginManager {
    public static boolean validateUser(String email, String password) {
        if (CommandLineApp.LoggedInUserType == UserType.ATTENDEE || CommandLineApp.LoggedInUserType == UserType.CCM) {
            Student student = DB_Student.readStudentByEmail(email);
            if (student == null) return false;
            else if (student != null && student.getPassword().equals(password)) {
                if (CommandLineApp.LoggedInUserType == UserType.ATTENDEE) CommandLineApp.LoggedInUser = student;
                else {
                    if (student.getIsCampCommittee()) CommandLineApp.LoggedInUser = new CampCommitteeMember(student, DB_CCMIdToPoints.getPoints(student.getId()));
                    else return false;
                }
                return true;
            } else {
                return false;
            }
        }
        else {
            
            Staff staff = DB_Staff.readStaffByEmail(email);
            if (staff == null) return false;
            else if (staff != null && staff.getPassword().equals(password)) {
                CommandLineApp.LoggedInUser = staff;
                return true;
            } else {
                System.out.println(staff.getPassword());
                return false;
            }
        }
    }
}
