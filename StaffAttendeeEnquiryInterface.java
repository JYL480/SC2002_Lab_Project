
import java.util.ArrayList;

public interface StaffAttendeeEnquiryInterface {

	/**
	 * 
	 * @param Camp
	 */
	ArrayList<Enquiry> viewAllAttendeeEnquiriesByCamp(int Camp);

	/**
	 * 
	 * @param Enquiry
	 * @param String
	 */
	void replyToAttendeeEnquiry(int Enquiry, int String);

}