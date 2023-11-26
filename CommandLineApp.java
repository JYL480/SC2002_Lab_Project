public class CommandLineApp {
    public static User LoggedInUser = null;
    public static UserType LoggedInUserType;

    public static void main(String[] args) {
        Page currentPage = new LoginPage();

        do {
            // Transition to the next page
            currentPage = currentPage.show();

        } while (!(currentPage instanceof ExitPage));

        System.out.println("Exiting application...");
    }
}
