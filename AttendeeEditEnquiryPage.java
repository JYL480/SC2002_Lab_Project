
import java.util.ArrayList;
import java.util.Scanner;

public class AttendeeEditEnquiryPage implements Page {

    @Override
    public Page show() {
        Scanner scanner = new Scanner(System.in);
        Attendee attendee = (Attendee) CommandLineApp.LoggedInUser;

        System.out.println("===== Edit Enquiry (Pending enquiries only) =====");
        ArrayList<Enquiry> enquiries = attendee.viewAllEnquires();
        ArrayList<Enquiry> pendingEnquiries = new ArrayList<>();

        for (Enquiry enquiry : enquiries) {
            if(!enquiry.getIsProcessed()) pendingEnquiries.add(enquiry);
        }

        int index = 1;
        for (Enquiry enquiry : pendingEnquiries) {
            System.out.println(index + ". Camp: " + enquiry.getCamp().getName()+" Subject: " + enquiry.getSubject());
            index++;
        }
        while(true) {
            System.out.println("Enter the number of the enquiry to edit (0 to go back): ");
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice == 0) {
                    return new AttendeeEnquiryPage();
                } else if (choice >= 1 && choice <= pendingEnquiries.size()) {
                    Enquiry selectedEnquiry = pendingEnquiries.get(choice - 1);
                    editSelectedEnquiry(selectedEnquiry, attendee);
                    return new AttendeeEnquiryPage();
                } else {
                    System.out.println("Invalid choice! Try again.");
                }
            } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Try again.");
            }
        }
    }    

    private void editSelectedEnquiry(Enquiry selectedEnquiry, Attendee attendee) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Current Subject: " + selectedEnquiry.getSubject());
        System.out.println("Current Description: " + selectedEnquiry.getDescription());

        System.out.println("Do you want to delete this enquiry?");
        System.out.print("Enter '1' to delete, or any other key to continue editing: ");
    
        String deleteChoice = scanner.nextLine();
        if (deleteChoice.equals("1")) {
            attendee.deleteEnquiry(selectedEnquiry);
            System.out.println("Enquiry deleted successfully!");
        }
        else {
            System.out.print("Enter the new subject (press Enter to keep it the same): ");
            String newSubject = scanner.nextLine();
            if (!newSubject.isEmpty()) {
                selectedEnquiry.setSubject(newSubject);
            }

            System.out.print("Enter the new description (press Enter to keep it the same): ");
            String newDescription = scanner.nextLine();
            if (!newDescription.isEmpty()) {
                selectedEnquiry.setDescription(newDescription);
            }

            attendee.editEnquiry(selectedEnquiry);
            System.out.println("Enquiry edited successfully!");
        }
    }
}

