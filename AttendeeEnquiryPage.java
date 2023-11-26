
import java.util.Scanner;

public class AttendeeEnquiryPage implements Page {

    public Page show() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("===== Attendee Enquiry Page =====");
        System.out.println("1. View All Pending Enquiries");
        System.out.println("2. Edit an Enquiry");
        System.out.println("3. Submit a new Enquiry");
        System.out.println("0. Go back to the main menu");

        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            return new AttendeeEnquiryPage();
        }

        switch (choice) {
            case 0:
                return new MainMenuPage();
            case 1:
                return new AttendeeViewAllEnquiresPage();
            case 2:
                return new AttendeeEditEnquiryPage();
            case 3:
                return new AttendeeSubmitNewEnquiriesPage();
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }

        return new AttendeeEnquiryPage();
    }
}

