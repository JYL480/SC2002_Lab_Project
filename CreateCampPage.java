import java.time.LocalDate;
import java.util.Scanner;

public class CreateCampPage implements Page {

    @Override
    public Page show() {
        Scanner scanner = new Scanner(System.in);
        Staff staff = (Staff) CommandLineApp.LoggedInUser;

        System.out.println("===== Create Camp =====");

        // Gather information for the new camp
        String campName;
        do {
            System.out.print("Enter Camp Name: ");
            campName = scanner.nextLine();
        } while (campName.isEmpty());

        // Camp Visibility
        boolean isVisible;
        do {
            System.out.print("Enter Camp Visibility (true/false): ");
            try {
                isVisible = Boolean.parseBoolean(scanner.nextLine());
                break; // Break out of the loop if parsing is successful
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter 'true' or 'false'.");
            }
        } while (true);

        // Camp Start Date
        LocalDate startDate;
        do {
            try {
                System.out.print("Enter Camp Start Date (YYYY-MM-DD): ");
                String startDateString = scanner.nextLine();
                startDate = LocalDate.parse(startDateString);
                break;
            } catch (Exception e) {
                System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            }
        } while (true);

        // Camp End Date
        LocalDate endDate;
        do {
            try {
                System.out.print("Enter Camp End Date (YYYY-MM-DD): ");
                String endDateString = scanner.nextLine();
                endDate = LocalDate.parse(endDateString);
                break;
            } catch (Exception e) {
                System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            }
        } while (true);

        // Registration Closing Date
        LocalDate regClosingDate;
        do {
            try {
                System.out.print("Enter Registration Closing Date (YYYY-MM-DD): ");
                String regClosingDateString = scanner.nextLine();
                regClosingDate = LocalDate.parse(regClosingDateString);
                break;
            } catch (Exception e) {
                System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            }
        } while (true);

        // Camp Location
        System.out.print("Enter Camp Location: ");
        String location = scanner.nextLine();

        // Total Slots
        int totalSlots;
        do {
            try {
                System.out.print("Enter Total Slots: ");
                totalSlots = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        } while (true);

        // Camp Committee Slots
        int committeeSlots;
        do {
            try {
                System.out.print("Enter Camp Committee Slots: ");
                committeeSlots = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        } while (true);

        // Camp Description
        System.out.print("Enter Camp Description: ");
        String description = scanner.nextLine();

        // Camp Open to All
        boolean isOpenToAll;
        do {
            System.out.print("Enter if Camp is Open to All (true/false): ");
            try {
                isOpenToAll = Boolean.parseBoolean(scanner.nextLine());
                break; // Break out of the loop if parsing is successful
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter 'true' or 'false'.");
            }
        } while (true);

        // Display the camp details for confirmation
        System.out.println("\n===== Camp Details =====");
        System.out.println("Name: " + campName);
        System.out.println("Visibility: " + isVisible);
        System.out.println("Start Date: " + startDate);
        System.out.println("End Date: " + endDate);
        System.out.println("Registration Closing Date: " + regClosingDate);
        System.out.println("Location: " + location);
        System.out.println("Total Slots: " + totalSlots);
        System.out.println("Committee Slots: " + committeeSlots);
        System.out.println("Description: " + description);
        System.out.println("Open to All: " + isOpenToAll);

        // Ask for confirmation
        System.out.print("\nPress 1 to confirm and create the camp, or any other key to go back: ");
        String confirmationChoice = scanner.nextLine();

        if (confirmationChoice.equals("1")) {
            // Create the new camp
            Camp newCamp = new Camp(
                    RandomIdGenerator.generateRandomId(), // Generate a random ID
                    campName,
                    isVisible,
                    startDate,
                    endDate,
                    regClosingDate,
                    location,
                    totalSlots,
                    committeeSlots,
                    description,
                    staff.getFacultyId(),
                    staff.getId(),
                    isOpenToAll
            );

            // Save the new camp to the database
            staff.createCamp(newCamp);

            System.out.println("Camp created successfully!");
        } else {
            System.out.println("Camp creation canceled. Returning to the previous menu.");
        }

        return new handleCampsPage();
    }
}
