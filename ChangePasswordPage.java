import java.util.Scanner;

public class ChangePasswordPage implements Page{
    public Page show(){
        // take user input and change password in database
        User user = CommandLineApp.LoggedInUser;
        Scanner scanner = new Scanner(System.in);
        System.out.println("======== Change password ==========");
        System.out.println("Press 1 to proceed with changing the password");
        System.out.println("Press any other key to exit");
        System.out.print("Enter choice: ");
        int choice = Integer.parseInt(scanner.nextLine());
        if (choice == 1) {
            System.out.println("Enter current password: ");
            String currentPassword = scanner.nextLine();
            if (CommandLineApp.LoggedInUserType == UserType.STAFF) {
                Staff staff = (Staff) user;
                if (currentPassword.equals(staff.getPassword())) {
                    System.out.print("Enter new password: ");
                    String newPassword1 = scanner.nextLine();
                    while (newPassword1.equals(currentPassword)) {
                        System.out.println("Error! Please do not reuse old passord");
                        System.out.println("Enter new password: ");
                        newPassword1 = scanner.nextLine();
                    }
                    System.out.print("Re-enter new password: ");
                    String newPassword2 = scanner.nextLine();
                    if (newPassword1.equals(newPassword2)) {
                        staff.changePassword(newPassword2);
                        System.out.println("Password changed succsefully!");
                    }
                    else {
                        System.out.println("Passwords do not match! Please try again");
                        return new ChangePasswordPage();
                    }
                }
                else {
                    System.out.println("Incorrect password! Please try again");
                    return new ChangePasswordPage();
                }
            }
            else {
                Student student = (Student) user;
                if (currentPassword.equals(student.getPassword())) {
                    System.out.print("Enter new password: ");
                    String newPassword1 = scanner.nextLine();
                    while (newPassword1.equals(currentPassword)) {
                        System.out.println("Error! Please do not reuse old passord");
                        System.out.println("Enter new password: ");
                        newPassword1 = scanner.nextLine();
                    }
                    System.out.print("Re-enter new password: ");
                    String newPassword2 = scanner.nextLine();
                    if (newPassword1.equals(newPassword2)) {
                        student.changePassword(newPassword2);
                        System.out.println("Password changed succsefully!");
                    }
                    else {
                        System.out.println("Passwords do not match! Please try again");
                        return new ChangePasswordPage();
                    }
                }
                else {
                    System.out.println("Incorrect password! Please try again");
                    return new ChangePasswordPage();
                }
            }
        }
        System.out.println("redirecting to login page...");
        CommandLineApp.LoggedInUser = null;
        CommandLineApp.LoggedInUserType = null;
        return new LoginPage();
    }
    
}
