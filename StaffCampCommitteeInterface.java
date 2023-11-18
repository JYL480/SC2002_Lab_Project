import java.util.ArrayList;

public interface StaffCampCommitteeInterface {
    public ArrayList<Suggestion> viewCommiteeMembersSuggestionsByCampId(String campId);
    public void approveSuggestion(String suggestionId, boolean isApproved);

    public void givePerformanceReview(Performance performance, String ccmId);
    public void editPerformanceReview(Performance performance);
    public void deletePerformanceReview(String performanceId, String ccmId);
}