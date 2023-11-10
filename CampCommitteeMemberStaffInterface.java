
import java.util.ArrayList;
public interface CampCommitteeMemberStaffInterface {

	/**
	 * 
	 * @param Suggestion
	 */
	void submitSuggestion(int Suggestion);

	ArrayList<Suggestion> viewAllSuggestions();

	/**
	 * 
	 * @param String
	 */
	Suggestion viewSuggestionById(int String);

	/**
	 * 
	 * @param text
	 * @param Suggestion
	 */
	void editSuggestionById(int text, int Suggestion);

	/**
	 * 
	 * @param String
	 */
	void deleteSuggestionById(int String);

}