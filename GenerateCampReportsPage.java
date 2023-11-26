import java.util.Scanner;

public class GenerateCampReportsPage implements Page {
    public Page show(){
        CampCommitteeMember ccm = (CampCommitteeMember) CommandLineApp.LoggedInUser;

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("===== Generate Camp Reports =====");
            System.out.println("1. Student Enrolled Camp Report");
            System.out.println("2. Return to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    Camp camp = ccm.getCampDetails();
                    System.out.println("Generating Report!");
                    ccm.generateReportOfStudentsAttendingWithCCMCamp(camp);
                    break;
                case 2:
                    System.out.println("Returning to Main Menu.");
                    return new CCMMainPage();
                    
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
                
                }
                
        }
        
    }
}
