import java.util.ArrayList;

public class CampCommitteeMember implements CampCommitteeMemberStaffInterface, CampCommitteeMemberAttendeeInterface, CampCommitteeMemberCampInterface {

	private ArrayList<Suggestion> listOfSuggestions;
	private Camp CampCommitteeFor;
	private Performance performance;
	private int points;

	public CampCommitteeMember(ArrayList<Suggestion> listOfSuggestions, Camp CampCommitteeFor, Performance performance, int points) {
        this.listOfSuggestions = listOfSuggestions;
        this.CampCommitteeFor = CampCommitteeFor;
        this.performance = performance;
        this.points = points;
    }


	public ArrayList<Suggestion> getListOfSuggestions() {
		return this.listOfSuggestions;
	}

	public Camp getCampCommitteeFor() {
		return this.CampCommitteeFor;
	}

	public Performance getPerformance() {
		return this.performance;
	}

	public int getPoints() {
		return this.points;
	}

	/**
	 * 
	 * @param listOfSuggestions
	 */
	public void setListOfSuggestions(ArrayList<Suggestion> listOfSuggestions) {
		this.listOfSuggestions = listOfSuggestions;
	}

	/**
	 * 
	 * @param CampCommitteeFor
	 */
	public void setCampCommitteeFor(Camp CampCommitteeFor) {
		this.CampCommitteeFor = CampCommitteeFor;
	}

	/**
	 * 
	 * @param performance
	 */
	public void setPerformance(Performance performance) {
		this.performance = performance;
	}

	/**
	 * 
	 * @param points
	 */
	public void setPoints(int points) {
		this.points = points;
	}

	/**
	 * 
	 * @param Suggestion
	 */
	public void submitSuggestion(int Suggestion) {

	}

	public ArrayList<Suggestion> viewAllSuggestions() {
		// for(Suggestion text: listOfSuggestions){
			
		// }
	}

	/**
	 * 
	 * @param String
	 */
	public Suggestion viewSuggestionById(int String) {

	}

	/**
	 * 
	 * @param text
	 * @param Suggestion
	 */
	public void editSuggestionById(int text, int Suggestion) {

	}

	/**
	 * 
	 * @param String
	 */
	public void deleteSuggestionById(int String) {

	}

	public ArrayList<Enquiry> viewAllAttendeeEnquiries() {


	}

	/**
	 * 
	 * @param Enquiry
	 * @param text
	 * @param Attendee
	 */
	public void replyToAttendeeEnquiry(int Enquiry, int text, int Attendee) {

	}

	public void registerForCampAsCampCommittee() {

	}

	public String getCampDetails() {

	}

}