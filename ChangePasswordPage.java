import java.util.Scanner;

public class ChangePasswordPage {
    public String show(){
        // take user input and change password in database
        User user = CommandLineApp.user;
        Scanner scanner = new Scanner(System.in);
        System.out.println("======== Change password ==========");
        System.out.println("Press 1 to proceed with changing the password");
        System.out.println("Press any other key to exit");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 1) {
            System.out.println("Enter current password: ");
            String currentPassword = scanner.nextLine();
            if (user instanceof Staff) {
                Staff staff = (Staff) user;
                if (currentPassword.equals(staff.getPassword())) {
                    System.out.print("Enter new password: ");
                    String newPassword1 = scanner.nextLine();
                    System.out.print("Re-enter new password: ");
                    String newPassword2 = scanner.nextLine();
                    scanner.close();
                    if (newPassword1.equals(newPassword2)) {
                        staff.changePassword(newPassword2);
                        System.out.println("Password changed succsefully!");
                    }
                    else {
                        System.out.println("Passwords do not match! Please try again");
                        return "changePassword";
                    }
                }
                else {
                    System.out.println("Incorrect password! Please try again");
                    scanner.close();
                    return "changePassword";
                }
            }
            else {
                Attendee attendee = (Attendee) user;
                if (currentPassword.equals(attendee.getPassword())) {
                    System.out.print("Enter new password: ");
                    String newPassword1 = scanner.nextLine();
                    System.out.print("Re-enter new password: ");
                    String newPassword2 = scanner.nextLine();
                    scanner.close();
                    if (newPassword1.equals(newPassword2)) {
                        attendee.changePassword(newPassword2);
                        System.out.println("Password changed succsefully!");
                    }
                    else {
                        System.out.println("Passwords do not match! Please try again");
                        return "changePassword";
                    }
                }
                else {
                    System.out.println("Incorrect password! Please try again");
                    scanner.close();
                    return "changePassword";
                }
            }
        }
        System.out.println("redirecting to login page...");
        return "loginPage";
    }
    
}
