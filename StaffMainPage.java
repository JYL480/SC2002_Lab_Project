import java.util.Scanner;

public class StaffMainPage implements Page{

    @Override
    public Page show() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome Staff!");
            System.out.println("===== Staff Main Menu =====");
            System.out.println("1. Change Password");
            System.out.println("2. Create/Edit/View All Camps");
            System.out.println("3. View List of Students for a Camp");
            System.out.println("4. View Suggestions to Change Camp Details");
            System.out.println("5. Generate Reports");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            int choice; 
            try {
                choice = scanner.nextInt();
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid input.");
                return new StaffMainPage();
            }

            switch (choice) {
                case 1:
                    return new ChangePasswordPage();
                case 2:
                    return new handleCampsPage();
                case 3:
                    return new ViewCampRegistrationsPage();
                case 4:
                    return new ViewCampSuggestionsPage();
                case 5:
                    return new GenerateReportsPage();
                case 6:
                    System.out.println("Exiting Staff Main Menu...");
                    return new ExitPage();
                default:
                    System.out.println("Invalid choice. Please try again.");
                    return new StaffMainPage();
            }
        }
    }

}
