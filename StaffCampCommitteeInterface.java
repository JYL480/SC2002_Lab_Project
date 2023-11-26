import java.util.ArrayList;

public interface StaffCampCommitteeInterface {
    public ArrayList<Suggestion> viewCommiteeMembersSuggestionsByCampId(String campId);
    public void approveSuggestion(String suggestionId, boolean isApproved);
}