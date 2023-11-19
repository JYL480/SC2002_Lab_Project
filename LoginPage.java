import java.util.Scanner;

public class LoginPage {
    public void loginInfoInput() {
        System.out.println("========= Login Page =========");
        Scanner scanner = new Scanner(System.in);

        //UserID input
        System.out.println("Enter User ID: ");
        String userID = scanner.nextLine();
        String email = userID + "@mail.com";

        //password input
        System.out.println("Enter password: ");
        String password = scanner.nextLine();

        //validate login
        User user = LoginManager.obtainUserObject(email, password);
        if (user == null) {
            System.out.println("Invalid credentials!");
        }
        else {
            CommandLineApp.user = user;
            System.out.println("Login Successful!");
            System.out.println("Signing in...");
        }
        scanner.close();
    }

    public String show() {
        System.out.println("Welcome");

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Press 1 to proceed to login");
            System.out.println("Press any other key to exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    LoginPage loginPage = new LoginPage();
                    loginPage.loginInfoInput();
                    break;
                default:
                    return "exit";
            }
        } while (CommandLineApp.user == null && choice == 1);
        scanner.close();
        // find out whether this is the first time the user is logging in 
        if (true) {
            System.out.println("Seems like you are a new user. Please change your password before you proceed");
            return "changePassword";
        }
        return "main";
    }
}
