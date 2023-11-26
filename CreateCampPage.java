import java.time.LocalDate;
import java.util.Scanner;

public class CreateCampPage implements Page {

    @Override
    public Page show() {
        Scanner scanner = new Scanner(System.in);
        Staff staff = (Staff) CommandLineApp.LoggedInUser;

        System.out.println("===== Create Camp =====");

        // Gather information for the new camp
        System.out.print("Enter Camp Name: ");
        String campName = scanner.nextLine();

        System.out.print("Enter Camp Visibility (true/false): ");
        boolean isVisible = Boolean.parseBoolean(scanner.nextLine());

        System.out.print("Enter Camp Start Date (YYYY-MM-DD): ");
        String startDateString = scanner.nextLine();
        LocalDate startDate = LocalDate.parse(startDateString);

        System.out.print("Enter Camp End Date (YYYY-MM-DD): ");
        String endDateString = scanner.nextLine();
        LocalDate endDate = LocalDate.parse(endDateString);

        System.out.print("Enter Registration Closing Date (YYYY-MM-DD): ");
        String regClosingDateString = scanner.nextLine();
        LocalDate regClosingDate = LocalDate.parse(regClosingDateString);

        System.out.print("Enter Camp Location: ");
        String location = scanner.nextLine();

        System.out.print("Enter Total Slots: ");
        int totalSlots = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter Camp Committee Slots: ");
        int committeeSlots = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter Camp Description: ");
        String description = scanner.nextLine();

        System.out.print("Enter if Camp is Open to All (true/false): ");
        boolean isOpenToAll = Boolean.parseBoolean(scanner.nextLine());

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
