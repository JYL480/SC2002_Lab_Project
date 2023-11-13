import java.util.ArrayList;

public interface AttendeeEnquiryInterface {

	/**
	 * 
	 * @param Enquiry
	 */
	void submitEnquiry(int Enquiry);

	ArrayList<Enquiry> viewAllEnquires();

	/**
	 * 
	 * @param String
	 */
	Enquiry viewEnquirybyId(int String);

	/**
	 * 
	 * @param Enquiry
	 * @param parameter
	 */
	void editEnquiry(int Enquiry, int parameter);

	/**
	 * 
	 * @param Enquiry
	 */
	void deleteEnquiry(int Enquiry);

}