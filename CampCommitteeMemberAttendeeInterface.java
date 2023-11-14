import java.util.ArrayList;
public interface CampCommitteeMemberAttendeeInterface {

	ArrayList<Enquiry> viewAllAttendeeEnquiries();

	void replyToAttendeeEnquiry(Enquiry e, String text, Attendee a);

}