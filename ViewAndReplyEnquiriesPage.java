import java.util.*;

public class ViewAndReplyEnquiriesPage implements Page{
    public Page show(){
        Scanner scanner = new Scanner(System.in);
        CampCommitteeMember ccm = (CampCommitteeMember) CommandLineApp.LoggedInUser;

        System.out.println("===== View and Reply to Student Enquiries =====");
        ArrayList<Enquiry> enquiries = ccm.viewAllAttendeeEnquiries();   
        int index = 1;
        for (Enquiry en: enquiries){ 
            System.out.println(index + ". " + en.getSubject());
            System.out.println("Description: " + en.getDescription());
            index++;
        }

        // Let user choose a camp
        int selectedEnquiryIndex;
        do {
            System.out.print("Enter the number of the enquiry to reply enquiry (0 to go back): ");
            try {
                selectedEnquiryIndex = Integer.parseInt(scanner.nextLine());
                if (selectedEnquiryIndex == 0) {
                    return new CCMMainPage();
                } else if (selectedEnquiryIndex >= 1 && selectedEnquiryIndex <= enquiries.size()) {
                    break;
                } else {
                    System.out.println("Invalid choice! Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Try again.");
            }
        } while (true);
        System.out.println("Key in reply text: ");
        String text = scanner.nextLine();
        
        ccm.replyToAttendeeEnquiry(enquiries.get(selectedEnquiryIndex-1), text);
        System.out.println("Enquiry replied successfully!");

        System.out.println("Enter 0 to go back or any other number to reply to another enquiry: ");
        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Redirecting back CCM Main Page...");
            return new CCMMainPage();
        }

        if (choice == 0) {
            return new CCMMainPage();
        } else {
            return new ViewAndReplyEnquiriesPage();
        }
    
    }
    }
