public class MainPage {
    public String show() {
        User user = CommandLineApp.user;
        if (user instanceof Attendee) {
            Attendee attendee = (Attendee) user;
            return AttendeeMainShow(attendee);
        }
        if (user instanceof CampCommitteeMember) {
            CampCommitteeMember campCommitteeMember = (CampCommitteeMember) user;
            return CCMMainShow(campCommitteeMember);
        }
        Staff staff = (Staff) user;
        return StaffMainShow(staff);
        
    }

    private String AttendeeMainShow(Attendee attendee) {
        return "exit";
    }

    private String CCMMainShow(CampCommitteeMember campCommitteeMember) {
        return "exit";
    }

    private String StaffMainShow(Staff staff) {
        return "exit";
    }
}
