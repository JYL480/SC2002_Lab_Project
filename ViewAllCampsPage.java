
import java.util.ArrayList;

public class ViewAllCampsPage implements Page {

    @Override
    public Page show() {
        Staff staff = (Staff) CommandLineApp.LoggedInUser;

        System.out.println("===== View All Camps =====");

        // Get the list of all camps
        ArrayList<Object[]> campStaffFacultyArrays = staff.viewAllCamps();

        // Display camp details
        for (Object[] campStaffFaculty : campStaffFacultyArrays) {
            Camp camp = (Camp) campStaffFaculty[0];
            Staff campStaff = (Staff) campStaffFaculty[1];
            Faculty faculty = (Faculty) campStaffFaculty[2];

            // System.out.println("Camp Name: " + camp.getName());
            // System.out.println("Staff Name: " + campStaff.getName());
            // System.out.println("Faculty Name: " + faculty.getName());
            System.out.println("Camp ID: " + camp.getId());
            System.out.println("Camp Name: " + camp.getName());
            System.out.println("Staff Name: " + campStaff.getName());
            System.out.println("Faculty Name: " + faculty.getName());
            System.out.println("Is Visible: " + camp.getIsVisible());
            System.out.println("Start Date: " + camp.getStartDate());
            System.out.println("End Date: " + camp.getEndDate());
            System.out.println("Registration Closing Date: " + camp.getRegClosingDate());
            System.out.println("Location: " + camp.getLocation());
            System.out.println("Total Slots: " + camp.getTotalSlots());
            System.out.println("Camp Committee Slots: " + camp.getCampCommitteeSlots());
            System.out.println("Description: " + camp.getDescription());
            System.out.println("Is Open To All: " + camp.isOpenToAll());

            System.out.println("----------------------------");
        }

        return new handleCampsPage();
    }
}