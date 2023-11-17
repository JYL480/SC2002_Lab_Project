import java.util.ArrayList;

public class CampCommitteeMember extends Student implements CampCommitteeMemberStaffInterface, CampCommitteeMemberAttendeeInterface, CampCommitteeMemberCampInterface {

	private int points;

	public CampCommitteeMember(String id, String password, String name, String email, boolean isCampCommittee, int points) {
        super(id, password, name, email, isCampCommittee);
        this.points = points;
    }

	public void setPoints(int points) {
		this.points = points;
	}

	public int getPoints() {
		return this.points;
	}
//	implements CampCommitteeMemberStaffInterface
	public void submitSuggestion(Suggestion suggestion) {
			// Implement logic to submit a suggestion
			DB_CCMIdToSuggestionId.createMapping(getId(), suggestion.getId());
			DB_Suggestion.createSuggestion(suggestion);
		}


	public ArrayList<Suggestion> viewAllSuggestions() {
		// Implement logic to view all suggestions
		ArrayList<Suggestion> suggestions = DB_Suggestion.getAllSuggestions();
        for(Suggestion s: suggestions){
            System.out.println("ID: "+ s.getId() + " Sugestion String: " + s.getSuggestionStr() + " Processed: " + s.getIsProcessed());
        }
        return suggestions;
	}

	
	public Suggestion viewSuggestionById(String suggestionID) {
		// Implement logic to view a suggestion by ID
		Suggestion s =DB_Suggestion.readSuggestion(suggestionID);
		System.out.println("ID: "+ s.getId() + " Sugestion String: " + s.getSuggestionStr() + " Processed: " + s.getIsProcessed());
		return s;
	}

	
	public void editSuggestionById(String text, Suggestion suggestion) {
		// Implement logic to edit a suggestion by ID
		Suggestion newS = new Suggestion(suggestion.getId(),false, text);	
		DB_Suggestion.updateSuggestion(newS);
	}

	
	public void deleteSuggestionById(String suggestionID) {
		// Implement logic to delete a suggestion by ID
		DB_CCMIdToSuggestionId.deleteMapping(getId(), suggestionID);
		DB_Suggestion.deleteSuggestion(suggestionID);
	}

	// implements CampCommitteeMemberAttendeeInterface
	public ArrayList<Enquiry> viewAllAttendeeEnquiries() {
		// Implement logic to view all attendee enquiries
		ArrayList<Enquiry> enquiries = DB_Enquiry.getAllEnquiries();
		for(Enquiry e: enquiries){
			System.out.println("ID: "+ e.id + " Subject: " + e.subject + " Description: " + e.description);
		}
		return enquiries;
	}

	
	public void replyToAttendeeEnquiry(Enquiry e, String text, Attendee a) {
		// Implement logic to reply to an attendee enquiry
		if(!e.getIsProcessed()){
			e.setReplyText(text);
			e.setRepliedByName(this.getName());
			e.setRepliedByStaff(true);
			e.setProcessed(true);
		}
		
	}

	//	implements CampCommitteeMemberCampInterface
	public void registerForCampAsCampCommittee(Camp camp) {
		// Implement logic to register for a camp as a camp committee member
		if(!DB_CCMIdToCampId.isExists(getId(), camp.getId())){
			DB_CCMIdToCampId.createMapping(getId(), camp.getId());
		}		
	}

	
	public String getCampDetails() {
		// Implement logic to get camp details
		DB_Camp.readCamp();
		//jalskdjls
	}
}