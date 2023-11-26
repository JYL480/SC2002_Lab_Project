import java.util.ArrayList;
import java.util.Scanner;

public class EditCampPage implements Page {

    @Override
    public Page show() {
        Scanner scanner = new Scanner(System.in);
        Staff staff = (Staff) CommandLineApp.LoggedInUser;

        System.out.println("===== Edit Camp =====");

        // Get the list of camps created by the staff
        ArrayList<Camp> staffCamps = staff.viewSelfCreatedCamps();

        // Display the list of camps
        System.out.println("===== Your Camps =====");
        for (int i = 0; i < staffCamps.size(); i++) {
            System.out.println((i + 1) + ". " + staffCamps.get(i).getName());
        }

        // Ask the user to choose a camp to edit
        System.out.print("Enter the number of the camp to edit (0 to go back): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (choice >= 1 && choice <= staffCamps.size()) {
            Camp selectedCamp = staffCamps.get(choice - 1);

            // Display the current camp details
            System.out.println("\n===== Current Camp Details =====");
            System.out.println("Name: " + selectedCamp.getName());
            System.out.println("Visibility: " + selectedCamp.getIsVisible());
            System.out.println("Start Date: " + selectedCamp.getStartDate());
            System.out.println("End Date: " + selectedCamp.getEndDate());
            System.out.println("Registration Closing Date: " + selectedCamp.getRegClosingDate());
            System.out.println("Location: " + selectedCamp.getLocation());
            System.out.println("Total Slots: " + selectedCamp.getTotalSlots());
            System.out.println("Committee Slots: " + selectedCamp.getCampCommitteeSlots());
            System.out.println("Description: " + selectedCamp.getDescription());
            System.out.println("Open to All: " + selectedCamp.isOpenToAll());

            // Ask for confirmation to edit
            System.out.print("\nPress 1 to confirm and edit the camp, or any other key to go back: ");
            String confirmationChoice = scanner.nextLine();

            if (confirmationChoice.equals("1")) {
                // Gather information for editing the camp
                System.out.print("Enter New Camp Name (blank for no change): ");
                String newCampName = scanner.nextLine();
                if (!newCampName.isBlank()) {
                    selectedCamp.setName(newCampName);
                }

                System.out.print("Enter New Camp Visibility (true/false, blank for no change): ");
                String newVisibilityString = scanner.nextLine();
                if (!newVisibilityString.isBlank()) {
                    selectedCamp.setIsVisible(Boolean.parseBoolean(newVisibilityString));
                }

                // Repeat the process for other fields...

                // Update the camp
                boolean success = staff.editCamp(selectedCamp);

                if (success) {
                    System.out.println("Camp edited successfully!");
                } else {
                    System.out.println("Failed to edit the camp. Please try again.");
                }
            } else {
                System.out.println("Camp editing canceled. Returning to the previous menu.");
            }
        } else if (choice == 0) {
            // User chose to go back
            System.out.println("Returning to the previous menu.");
        } else {
            System.out.println("Invalid choice. Returning to the previous menu.");
        }

        return new handleCampsPage();
    }
}

