
public class MainMenuPage implements Page {
    @Override
    public Page show() {
        if (CommandLineApp.LoggedInUserType == UserType.ATTENDEE) return new AttendeeMainPage();
        if (CommandLineApp.LoggedInUserType == UserType.CCM) return new CCMMainPage();
        if (CommandLineApp.LoggedInUserType == UserType.STAFF) return new StaffMainPage();
        return new ExitPage();
    }
}
