import java.time.LocalDate;
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
            System.out.print("\nPress 1 and edit the camp, or any other key to go back: ");
            String confirmationChoice = scanner.nextLine();

            if (confirmationChoice.equals("1")) {
                // Gather information for editing the camp
                System.out.print("Enter New Camp Name (blank for no change): ");
                String newCampName = scanner.nextLine();
                if (!newCampName.isBlank()) {
                    selectedCamp.setName(newCampName);
                }

                // Editing Camp Visibility
                boolean newVisibility;
                do {
                    System.out.print("Enter New Camp Visibility (true/false, blank for no change): ");
                    String newVisibilityString = scanner.nextLine();
                    if (newVisibilityString.isBlank()) {
                        break; // No change, exit the loop
                    }
                    try {
                        if(newVisibilityString.equals("true")) {
                            newVisibility = true;
                            selectedCamp.setIsVisible(newVisibility);
                            break;
                        }
                        else if (newVisibilityString.equals("false")) {
                            newVisibility = false;
                            selectedCamp.setIsVisible(newVisibility);
                            break;
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid input. Please enter 'true' or 'false'.");
                    }
                } while (true);

                // Editing Camp Start Date
                LocalDate newStartDate;
                do {
                    try {
                        System.out.print("Enter New Camp Start Date (YYYY-MM-DD, blank for no change): ");
                        String newStartDateString = scanner.nextLine();
                        if (newStartDateString.isBlank()) {
                            break; // No change, exit the loop
                        }
                        newStartDate = LocalDate.parse(newStartDateString);
                        selectedCamp.setStartDate(newStartDate);
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid date format. Please use YYYY-MM-DD.");
                    }
                } while (true);

                // Editing Camp End Date
                LocalDate newEndDate;
                do {
                    try {
                        System.out.print("Enter New Camp End Date (YYYY-MM-DD, blank for no change): ");
                        String newEndDateString = scanner.nextLine();
                        if (newEndDateString.isBlank()) {
                            break; // No change, exit the loop
                        }
                        newEndDate = LocalDate.parse(newEndDateString);
                        selectedCamp.setEndDate(newEndDate);
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid date format. Please use YYYY-MM-DD.");
                    }
                } while (true);

                // Editing Registration Closing Date
                LocalDate newRegClosingDate;
                do {
                    try {
                        System.out.print("Enter New Registration Closing Date (YYYY-MM-DD, blank for no change): ");
                        String newRegClosingDateString = scanner.nextLine();
                        if (newRegClosingDateString.isBlank()) {
                            break; // No change, exit the loop
                        }
                        newRegClosingDate = LocalDate.parse(newRegClosingDateString);
                        selectedCamp.setRegClosingDate(newRegClosingDate);
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid date format. Please use YYYY-MM-DD.");
                    }
                } while (true);

                // Editing Camp Location
                System.out.print("Enter New Camp Location (blank for no change): ");
                String newLocation = scanner.nextLine();
                if (!newLocation.isBlank()) {
                    selectedCamp.setLocation(newLocation);
                }

                // Editing Total Slots
                int newTotalSlots;
                do {
                    try {
                        System.out.print("Enter New Total Slots (blank for no change): ");
                        String newTotalSlotsString = scanner.nextLine();
                        if (newTotalSlotsString.isBlank()) {
                            break; // No change, exit the loop
                        }
                        newTotalSlots = Integer.parseInt(newTotalSlotsString);
                        selectedCamp.setTotalSlots(newTotalSlots);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid integer.");
                    }
                } while (true);

                // Editing Committee Slots
                int newCommitteeSlots;
                do {
                    try {
                        System.out.print("Enter New Committee Slots (blank for no change): ");
                        String newCommitteeSlotsString = scanner.nextLine();
                        if (newCommitteeSlotsString.isBlank()) {
                            break; // No change, exit the loop
                        }
                        newCommitteeSlots = Integer.parseInt(newCommitteeSlotsString);
                        selectedCamp.setCampCommitteeSlots(newCommitteeSlots);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid integer.");
                    }
                } while (true);

                // Editing Camp Description
                System.out.print("Enter New Camp Description (blank for no change): ");
                String newDescription = scanner.nextLine();
                if (!newDescription.isBlank()) {
                    selectedCamp.setDescription(newDescription);
                }

                // Editing Open to All
                boolean newOpenToAll;
                do {
                    System.out.print("Enter New Open to All (true/false, blank for no change): ");
                    String newOpenToAllString = scanner.nextLine();
                    if (newOpenToAllString.isBlank()) {
                        break; // No change, exit the loop
                    }
                    try {
                        newOpenToAll = Boolean.parseBoolean(newOpenToAllString);
                        selectedCamp.setOpenToAll(newOpenToAll);
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid input. Please enter 'true' or 'false'.");
                    }
                } while (true);

                // Display the updated camp details
                System.out.println("\n===== Updated Camp Details =====");
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

                // Ask for final confirmation to update
                System.out.print("\nPress 1 to confirm and update the camp, or any other key to discard changes: ");
                String finalConfirmationChoice = scanner.nextLine();

                if (finalConfirmationChoice.equals("1")){
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

