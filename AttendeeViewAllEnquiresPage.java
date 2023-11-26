import java.util.ArrayList;
import java.util.Scanner;

public class AttendeeViewAllEnquiresPage implements Page {

    public Page show() {
        Scanner scanner = new Scanner(System.in);
        Attendee attendee = (Attendee) CommandLineApp.LoggedInUser;

        System.out.println("===== View All Enquiries =====");
        ArrayList<Enquiry> enquiries = attendee.viewAllEnquires();

        int index = 1;
        for (Enquiry enquiry : enquiries) {
            
            System.out.println(index + ". Camp: " + enquiry.getCamp().getName());
            System.out.println(" Subject: " + enquiry.getSubject());
            index++;
        }

        System.out.println("Enter the number of the enquiry to view details (0 to go back): ");
        while (true) {
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice == 0) {
                    return new AttendeeEnquiryPage();
                } else if (choice >= 1 && choice <= enquiries.size()) {
                    Enquiry selectedEnquiry = enquiries.get(choice - 1);
                    viewEnquiryDetails(selectedEnquiry);
                } else {
                    System.out.println("Invalid choice! Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Try again.");
            }
        }
    }

    private void viewEnquiryDetails(Enquiry enquiry) {
        System.out.println("===== Enquiry Details =====");
        System.out.println("Camp: " + enquiry.getCamp().getName());
        System.out.println("Subject: " + enquiry.getSubject());
        System.out.println("Description: " + enquiry.getDescription());
        System.out.println("Processed: " + (enquiry.getIsProcessed() ? "Yes" : "No"));

        if (enquiry.getIsProcessed()) {
            System.out.println("Reply Text: " + enquiry.getReplyText());
            System.out.println("Replied By: " + (enquiry.getRepliedByStaff() ? "Staff" : "Unknown"));
        } else {
            System.out.println("Reply Text: NA");
            System.out.println("Replied By: NA");
        }

        System.out.println("==========================");
    }
}
