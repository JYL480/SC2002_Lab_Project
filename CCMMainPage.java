
import java.util.Scanner;

public class CCMMainPage implements Page {
    
    @Override
    public Page show() {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("Welcome Camp Committee Member!");
            System.out.println("===== Main Menu =====");
            System.out.println("1. Submit Suggestions for Camp Details");
            System.out.println("2. View and Reply to Student Enquiries");
            System.out.println("3. View, Edit, and Delete Unprocessed Suggestions");
            System.out.println("4. Generate Camp Reports");
            System.out.println("5. Change Password");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            int choice; 
            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                case 1:
                    // Logic for submitting suggestions for camp details
                    return new SubmitSuggestionsPage();
                case 2:
                    // Logic for viewing and replying to student enquiries
                    return new ViewAndReplyEnquiriesPage();
                case 3:
                    // Logic for viewing, editing, and deleting unprocessed suggestions
                    return new EditDeleteSuggestionsPage();
                case 4:
                    return new ViewAllCcmSuggestionsPage();
                case 5:
                    // Logic for generating camp reports
                    return new GenerateCampReportsPage();
                case 6:
                    return new ChangePasswordPage();
                case 7:
                    System.out.println("Exiting Main Menu...");
                    return new ExitPage();
                default:
                    System.out.println("Invalid choice. Please try again.");
                    return new CCMMainPage();
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid input.");
                scanner.nextLine(); 
                return new CCMMainPage();
            }

            
        }
    }
}

