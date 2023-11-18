import java.util.ArrayList;
public interface CampCommitteeMemberAttendeeInterface {

	public ArrayList<Enquiry> viewAllAttendeeEnquiries();

	public void replyToAttendeeEnquiry(Enquiry e, String text);

}