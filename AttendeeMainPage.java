import java.util.Scanner;

public class AttendeeMainPage implements Page {
    public Page show() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Welcome Attendee!");
            System.out.println("===== Main Menu =====");
            System.out.println("1. Change Password");
            System.out.println("2. Browse Available Camps");
            System.out.println("3. Enquiries");
            System.out.println("4. View Registered Camps");
            System.out.println("5. Request to Withdraw from Camps");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    return new ChangePasswordPage();
                case 2:
                    return new StudentBrowseAvailableCampsPage();
                case 3:
                    return  new AttendeeEnquiryPage();
                case 4:
                    return new StudentViewRegisteredCampsPage();
                case 5:
                    return new StudentRequestWithdrawFromCampsPage();
                case 6:
                    System.out.println("Exiting Main Menu...");
                    return new ExitPage();
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
