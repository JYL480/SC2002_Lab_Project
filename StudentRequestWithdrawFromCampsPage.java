import java.util.ArrayList;
import java.util.Scanner;

public class StudentRequestWithdrawFromCampsPage implements Page {

    @Override
    public Page show() {
        User user = CommandLineApp.LoggedInUser;
        Student student = (Student) user;
        Attendee attendee = new Attendee(student);

        System.out.println("===== Withdraw from Registered Camps =====");

        ArrayList<Camp> attendeeCamps = attendee.viewRegisteredCampsAsAttendee();

        if (attendeeCamps != null && !attendeeCamps.isEmpty()) {
            System.out.println("Camps Registered as Attendee:");
            for (int i = 0; i < attendeeCamps.size(); i++) {
                System.out.println((i + 1) + ". " + attendeeCamps.get(i).getName());
            }

            int choice = getCampChoiceFromUser(attendeeCamps.size());

            if (choice != 0) {
                Camp selectedCamp = attendeeCamps.get(choice - 1);
                attendee.withdrawFromCampAsAttendee(selectedCamp.getId());
                System.out.println("Withdrawal from " + selectedCamp.getName() + " successful!");
            }
        } else {
            System.out.println("No camps registered as Attendee. Nothing to withdraw.");
        }

        // You may want to add logic to go back to the main menu or another page.
        return new AttendeeMainPage();
    }

    private int getCampChoiceFromUser(int maxChoice) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("Enter the number of the camp to withdraw from (0 to go back): ");
                int choice = Integer.parseInt(scanner.nextLine());

                if (choice >= 0 && choice <= maxChoice) {
                    return choice;
                } else {
                    System.out.println("Invalid choice! Please enter a valid number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }
}
