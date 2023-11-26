import java.util.ArrayList;
import java.util.Scanner;

public class StudentViewRegisteredCampsPage implements Page {

    @Override
    public Page show() {
        User user = CommandLineApp.LoggedInUser;
        Student student = (Student) user;
        Camp ccmCamp = null;
        ArrayList<Camp> attendeeCamps = null;

        if (student.getIsCampCommittee()) {
            CampCommitteeMember campCommitteeMember = new CampCommitteeMember(student, DB_CCMIdToPoints.getPoints(student.getId()));
            ccmCamp = campCommitteeMember.getCampDetails();
            System.out.println("===== Camps Registered as Camp Committee Member =====");
            if (ccmCamp != null) {
                printCampDetails(ccmCamp);
            } else {
                System.out.println("No camps registered as Camp Committee Member.");
            }
        }

        Attendee attendee = new Attendee(student);
        attendeeCamps = attendee.viewRegisteredCampsAsAttendee();
        System.out.println("===== Camps Registered as Attendee =====");
        if (attendeeCamps != null && !attendeeCamps.isEmpty()) {
            for (Camp camp : attendeeCamps) {
                printCampDetails(camp);
            }
        } else {
            System.out.println("No camps registered as Attendee.");
        }

        // You may want to add logic to go back to the main menu or another page.
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press '1' to go back to the main menu: ");
        while (true) {
            String input = scanner.nextLine();
            if ("1".equals(input.trim())) {
                return new AttendeeMainPage();
            } else {
                System.out.println("Invalid input! Press '1' to go back to the main menu: ");
            }
        }
    }

    private void printCampDetails(Camp camp) {
        System.out.println("Camp Name: " + camp.getName());
        System.out.println("Location: " + camp.getLocation());
        System.out.println("Start Date: " + camp.getStartDate());
        System.out.println("End Date: " + camp.getEndDate());
        System.out.println("==========================");
    }
}
