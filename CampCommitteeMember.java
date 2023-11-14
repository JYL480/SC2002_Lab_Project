import java.util.ArrayList;

public class CampCommitteeMember extends Student implements CampCommitteeMemberStaffInterface, CampCommitteeMemberAttendeeInterface, CampCommitteeMemberCampInterface {

	private int points;

	public CampCommitteeMember(String id, String password, String name, String faculty, String email, boolean isCampCommittee, int points) {
        super(id, password, name, faculty, email, isCampCommittee);
        this.points = points;
    }

	public void setPoints(int points) {
		this.points = points;
	}

	public int getPoints() {
		return this.points;
	}

	public void submitSuggestion(Suggestion suggestion) {
			// Implement logic to submit a suggestion
		}

	@Override
	public ArrayList<Suggestion> viewAllSuggestions() {
		// Implement logic to view all suggestions
		return new ArrayList<>();
	}

	@Override
	public Suggestion viewSuggestionById(String suggestionID) {
		// Implement logic to view a suggestion by ID
		return new Suggestion(); // Replace with actual logic
	}

	@Override
	public void editSuggestionById(String text, Suggestion suggestion) {
		// Implement logic to edit a suggestion by ID
	}

	@Override
	public void deleteSuggestionById(String suggestionID) {
		// Implement logic to delete a suggestion by ID
	}

	@Override
	public ArrayList<Enquiry> viewAllAttendeeEnquiries() {
		// Implement logic to view all attendee enquiries
		return new ArrayList<>();
	}

	@Override
	public void replyToAttendeeEnquiry(Enquiry e, String text, Attendee a) {
		// Implement logic to reply to an attendee enquiry
	}

	@Override
	public void registerForCampAsCampCommittee() {
		// Implement logic to register for a camp as a camp committee member
	}

	@Override
	public String getCampDetails() {
		// Implement logic to get camp details
		return "Camp details"; // Replace with actual logic
	}
	}