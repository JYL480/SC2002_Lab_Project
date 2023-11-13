import java.util.ArrayList;
public interface CampCommitteeMemberAttendeeInterface {

	ArrayList<Enquiry> viewAllAttendeeEnquiries();

	/**
	 * 
	 * @param Enquiry
	 * @param text
	 * @param Attendee
	 */
	void replyToAttendeeEnquiry(int Enquiry, int text, int Attendee);

}