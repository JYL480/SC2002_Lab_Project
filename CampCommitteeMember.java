import java.util.ArrayList;

public class CampCommitteeMember extends Student implements CampCommitteeMemberStaffInterface, CampCommitteeMemberAttendeeInterface, CampCommitteeMemberCampInterface {

	private int points;

	public CampCommitteeMember(String userID, String password, String name, String faculty, String email, boolean isCampCommittee, int points) {
        super(userID, password, name, faculty, email, isCampCommittee);
        this.points = points;
    }

	/**
	 * 
	 * @param points
	 */
	public void setPoints(int points) {
		this.points = points;
	}

	public int getPoints() {
		return this.points;
	}

	/**
	 * 
	 * @param Suggestion
	 */
	public void submitSuggestion(int Suggestion) {
		// TODO - implement CampCommitteeMember.submitSuggestion
		throw new UnsupportedOperationException();
	}

	public ArrayList<Suggestion> viewAllSuggestions() {
		// TODO - implement CampCommitteeMember.viewAllSuggestions
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param String
	 */
	public Suggestion viewSuggestionById(int String) {
		// TODO - implement CampCommitteeMember.viewSuggestionById
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param text
	 * @param Suggestion
	 */
	public void editSuggestionById(int text, int Suggestion) {
		// TODO - implement CampCommitteeMember.editSuggestionById
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param String
	 */
	public void deleteSuggestionById(int String) {
		// TODO - implement CampCommitteeMember.deleteSuggestionById
		throw new UnsupportedOperationException();
	}

	public ArrayList<Enquiry> viewAllAttendeeEnquiries() {
		// TODO - implement CampCommitteeMember.viewAllAttendeeEnquiries
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param Enquiry
	 * @param text
	 * @param Attendee
	 */
	public void replyToAttendeeEnquiry(int Enquiry, int text, int Attendee) {
		// TODO - implement CampCommitteeMember.replyToAttendeeEnquiry
		throw new UnsupportedOperationException();
	}

	public void registerForCampAsCampCommittee() {
		// TODO - implement CampCommitteeMember.registerForCampAsCampCommittee
		throw new UnsupportedOperationException();
	}

	public String getCampDetails() {
		// TODO - implement CampCommitteeMember.getCampDetails
		throw new UnsupportedOperationException();
	}

}