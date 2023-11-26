import java.util.ArrayList;
import java.util.Scanner;

public class AttendeeSubmitNewEnquiriesPage implements Page {

    public Page show() {
        Scanner scanner = new Scanner(System.in);
        Attendee attendee = (Attendee) CommandLineApp.LoggedInUser;
        ArrayList<Camp> attendeeCamps = attendee.viewListOfAvailCamps();

        System.out.println("===== Submit Enquiries =====");

        //Let user choose which camp from attendeeCamps
        System.out.println("Available Camps:");
        int index = 1;
        for (Camp camp : attendeeCamps) {
            System.out.println(index + ". " + camp.getName());
            index++;
        }

        // Let user choose a camp
        int selectedCampIndex;
        do {
            System.out.print("Enter the number of the camp to submit enquiry (0 to go back): ");
            try {
                selectedCampIndex = Integer.parseInt(scanner.nextLine());
                if (selectedCampIndex == 0) {
                    return new AttendeeEnquiryPage();
                } else if (selectedCampIndex >= 1 && selectedCampIndex <= attendeeCamps.size()) {
                    break;
                } else {
                    System.out.println("Invalid choice! Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Try again.");
            }
        } while (true);

        // Gather enquiry details
        System.out.print("Enter the subject of your enquiry: ");
        String subject = scanner.nextLine();

        System.out.print("Enter the description of your enquiry: ");
        String description = scanner.nextLine();

        // Create a new Enquiry object
        Enquiry newEnquiry = new Enquiry();
        newEnquiry.setSubject(subject);
        newEnquiry.setDescription(description);

        // Submit the enquiry
        attendee.submitEnquiry(newEnquiry);

        System.out.println("Enquiry submitted successfully!");

        System.out.println("Enter 0 to go back or any other number to submit another enquiry: ");
        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Redirecting back...");
            return new AttendeeEnquiryPage();
        }

        if (choice == 0) {
            return new AttendeeEnquiryPage();
        } else {
            return new AttendeeSubmitNewEnquiriesPage();
        }
    }
}
