import java.util.ArrayList;

public interface CampCommitteeMemberStaffInterface {


	public void submitSuggestion(Suggestion suggestion);

	public ArrayList<Suggestion> viewAllSuggestions();

	public Suggestion viewSuggestionById(String suggestionID);

	public boolean editSuggestion(Suggestion newSuggestion);

	public void deleteSuggestionById(String suggestionID);

}