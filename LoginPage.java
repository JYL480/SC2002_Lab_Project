import java.util.Scanner;

public class LoginPage implements Page{
    public void loginInfoInput() {
        System.out.println("========= Login Page =========");
        Scanner scanner = new Scanner(System.in);

        //UserTypeInput
        System.out.println("Login As: ");
        System.out.println("1. Attendee");
        System.out.println("2. Camp committee member");
        System.out.println("3. Staff");
        System.out.println("Enter choice: ");
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1:
                CommandLineApp.LoggedInUserType = UserType.ATTENDEE;
                break;
            case 2:
                CommandLineApp.LoggedInUserType = UserType.CCM;
                break;
            case 3:
                CommandLineApp.LoggedInUserType = UserType.STAFF;
                break;
            default:
                break;
        }
        //UserID input
        System.out.println("Enter User ID: ");
        String userID = scanner.nextLine();
        String email = userID + "@mail.com";

        //password input
        System.out.println("Enter password: ");
        String password = scanner.nextLine();

        //validate login
        boolean validated = LoginManager.validateUser(email, password);
        if (!validated) {
            System.out.println("Invalid credentials!");
        }
        else {
            System.out.println("Login Successful!");
            System.out.println("Signing in...");
        }
        
    }

    public Page show() {
        System.out.println("Welcome");

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Press 1 to proceed to login");
            System.out.println("Press any other key to exit");
            System.out.print("Enter choice: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    loginInfoInput();
                    break;
                default:
                    return new ExitPage();
            }
        } while (CommandLineApp.LoggedInUser == null && choice == 1);
        
        // find out whether this is the first time the user is logging in
        if (CommandLineApp.LoggedInUser.isNewLogin()) {
            System.out.println("Seems like you are a new user. Please change your password before you proceed");
            return new ChangePasswordPage();
        }
        return new MainMenuPage();
    }
}
