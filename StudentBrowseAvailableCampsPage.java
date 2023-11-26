import java.util.ArrayList;
import java.util.Scanner;

public class StudentBrowseAvailableCampsPage implements Page{

    public Page show() {
        User user = CommandLineApp.LoggedInUser;
        Scanner scanner = new Scanner(System.in);

        Student student = (Student) user;
        ArrayList<Camp> campList = student.viewListOfAvailCamps();

        System.out.println("===== Available Camps =====");
        int count = 1;

        for (Camp camp : campList) {
            System.out.println(count + ". " + camp.getName());
            count++;
        }

        System.out.println("Enter the number of the camp to view details (0 to go back): ");
        int choice = -1;
        int option;

        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            return new StudentBrowseAvailableCampsPage();
        }

        if (choice == 0) {
            return new MainMenuPage();
        }

        if (choice >= 1 && choice <= campList.size()) {
            viewCampDetails(campList.get(choice - 1));
            System.out.println("Enter 1 to register for the selected camp as attendee or 2 to register as committee member (0 to go back): ");
            try {
            option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                return new StudentBrowseAvailableCampsPage();
            }
            if (option == 1) {
                boolean registrationSuccess = student.registerForCampAsAttendee(campList.get(choice - 1).getId());
                if(registrationSuccess) System.out.println("Successfully registered to " + campList.get(choice - 1).getName() + "!");
                else System.out.println("Failed to registered to " + campList.get(choice - 1).getName() + "!");
            }
            else if (option == 2) {
                boolean registrationSuccess = student.registerForCampAsCCM(campList.get(choice - 1).getId());
                if(registrationSuccess) System.out.println("Successfully registered to " + campList.get(choice - 1).getName() + "!");
                else System.out.println("Failed to registered to " + campList.get(choice - 1).getName() + "!");
            }
            else if (option != 0) System.out.println("Invalid choice. Please try again.");

        } else {
            System.out.println("Invalid choice. Please try again.");
        }

        return new StudentBrowseAvailableCampsPage();
    }

    private void viewCampDetails(Camp camp) {
        System.out.println("===== Camp Details =====");
        System.out.println("Name: " + camp.getName());
        System.out.println("Location: " + camp.getLocation());
        System.out.println("Start Date: " + camp.getStartDate());
        System.out.println("End Date: " + camp.getEndDate());
        System.out.println("Total Slots: " + camp.getTotalSlots());
        System.out.println("Camp Committee Slots: " + camp.getCampCommitteeSlots());
        System.out.println("isOpenToAll: " + camp.isOpenToAll());
        System.out.println("Description: ");
        System.out.println(camp.getDescription());
        System.out.println("==============");
    }
}

