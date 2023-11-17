import java.util.ArrayList;

public interface CampCommitteeMemberStaffInterface {


	public void submitSuggestion(Suggestion suggestion);

	public ArrayList<Suggestion> viewAllSuggestions();

	public ArrayList<Suggestion> viewSuggestionById(String suggestionID);

	public void editSuggestionById(String text, Suggestion suggestion);

	public void deleteSuggestionById(String suggestionID);

}