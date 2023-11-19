public class FirstTimeChangePasswordPage {
    public String show() {
        User user = CommandLineApp.user;
        if (user instanceof Staff) {
            //check if it is first time user
            if (true) {
                //take user input to change password and update database
                //provide option to exit and to return to login
            }
            return "main"; //go to main
        }
        else {
            // check if it is first time user
            if (true) {
                //take user input to change password and update database
                //provide option to exit and to return to login
            }
            return "main"; //go to main
        }
    }
}
