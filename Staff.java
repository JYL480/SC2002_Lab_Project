import java.util.ArrayList;


public class Staff extends User implements StaffAttendeeEnquiryInterface, StaffCampCommitteeInterface, StaffCampInterface, StaffReportInterface {


	public Staff(String userID, String password, String name, String faculty, String email) {
        super(userID, password, name, faculty, email);
    }

	/**
	 * 
	 * @param Camp
	 */
	public ArrayList<Enquiry> viewAllAttendeeEnquiriesByCamp(int Camp) {
		// TODO - implement Staff.viewAllAttendeeEnquiriesByCamp
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param Enquiry
	 * @param String
	 */
	public void replyToAttendeeEnquiry(int Enquiry, int String) {
		// TODO - implement Staff.replyToAttendeeEnquiry
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param Camp
	 */
	public ArrayList<Suggestion> viewCommitteeMembersSuggetionsByCamp(int Camp) {
		// TODO - implement Staff.viewCommitteeMembersSuggetionsByCamp
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param CampCommitteeMember
	 * @param Performance
	 */
	public void givePerformanceReview(int CampCommitteeMember, int Performance) {
		// TODO - implement Staff.givePerformanceReview
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param CampCommitteeMember
	 * @param Performance
	 */
	public void editPerformanceReview(int CampCommitteeMember, int Performance) {
		// TODO - implement Staff.editPerformanceReview
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param CampCommitteeMember
	 */
	public void deletePerformanceReview(int CampCommitteeMember) {
		// TODO - implement Staff.deletePerformanceReview
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param Camp
	 */
	public void createCamp(int Camp) {
		// TODO - implement Staff.createCamp
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param Camp
	 */
	public void deleteCamp(int Camp) {
		// TODO - implement Staff.deleteCamp
		throw new UnsupportedOperationException();
	}

	public ArrayList<Camp> viewAllCamps() {
		// TODO - implement Staff.viewAllCamps
		throw new UnsupportedOperationException();
	}

	public ArrayList<Camp> viewSelfCreatedCamps() {
		// TODO - implement Staff.viewSelfCreatedCamps
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param Camp
	 * @param parameter
	 */
	public void editCamp(int Camp, int parameter) {
		// TODO - implement Staff.editCamp
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param Camp
	 */
	public void generateReportOfStudentsAttendingSelfCreatedCamp(int Camp) {
		// TODO - implement Staff.generateReportOfStudentsAttendingSelfCreatedCamp
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param Camp
	 */
	public void generatePerformanceReportOfCampCommitteMembers(int Camp) {
		// TODO - implement Staff.generatePerformanceReportOfCampCommitteMembers
		throw new UnsupportedOperationException();
	}

}