import java.util.Scanner;

public class handleCampsPage implements Page{
    public Page show() {
         Scanner scanner = new Scanner(System.in);

        System.out.println("===== Camps Menu =====");
        System.out.println("1. Create a Camp");
        System.out.println("2. Edit a Camp");
        System.out.println("3. View All Camps");
        System.out.println("4. Go Back to Main Menu");

        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character

        switch (choice) {
            case 1:
                return new CreateCampPage();
            case 2:
                return new EditCampPage();
            case 3:
                return new ViewAllCampsPage();
            case 4:
                return new StaffMainPage();  // Go back to the main menu
            default:
                System.out.println("Invalid choice. Try Again.");
                return new handleCampsPage();
        }
    }
}
