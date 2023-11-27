import java.util.Scanner;

public class GenerateReportsPage implements Page {
    public Page show() {
        Staff staff = (Staff) CommandLineApp.LoggedInUser;

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("===== Generate Camp Reports =====");
            System.out.println("1. Student Attendee Report");
            System.out.println("2. Camp Committee Member Performance Report");
            System.out.println("3. Report with Filter by Camp Name");
            System.out.println("4. Report with Filter by Location");
            System.out.println("5. Report of Camp Committee Members Attending Self-Created Camp");
            System.out.println("6. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Generating Report!");
                    staff.generateReportOfStudentsAttendingSelfCreatedCamp(staff);
                    break;
                case 2:
                    System.out.println("Generating Camp Committee Member Performance Report!");
                    staff.generatePerformanceReportOfCampCommitteeMembers(staff);
                    break;
                case 3:
                    System.out.print("Enter Camp Name: ");
                    String nameFilter = scanner.nextLine();
                    System.out.println("Generating camp filtered by name!");
                    staff.generateReportWithFilterByCampName(staff, nameFilter);
                    break;
                case 4:
                    System.out.print("Enter Location: ");
                    String locationFilter = scanner.nextLine();
                    System.out.println("Generating camp filtered by location!");
                    staff.generateReportFilterByLocation(staff, locationFilter);
                    break;
                case 5:
                    System.out.println("Generating Report of Camp Committee Members Attending Self-Created Camp!");
                    staff.generateReportOfCCMAttendingSelfCreatedCamp(staff);
                    break;
                case 6:
                    System.out.println("Returning to Main Menu.");
                    return new StaffMainPage();
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

    }


}
