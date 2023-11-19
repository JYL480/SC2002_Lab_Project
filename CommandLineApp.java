public class CommandLineApp {
    public static User user = null;
    public static void main(String[] args) {
        String currentPage = "loginPage";

        do {
            switch (currentPage) {
                case "loginPage":
                    LoginPage loginPage = new LoginPage();
                    currentPage = loginPage.show();
                    break;
                case "changePassword":
                    ChangePasswordPage changePasswordPage = new ChangePasswordPage();
                    currentPage = changePasswordPage.show();
                    break;
                case "main":
                    //navigate to main page
                    break;
                default:
                    System.out.println("Invalid page. Exiting...");
                    break;
            }
        } while (!currentPage.equals("exit"));

        System.out.println("Exiting application...");
    }
}
