import java.util.ArrayList;

public interface StaffCampCommitteeInterface {
    public ArrayList<Suggestion> viewCommiteeMembersSuggestionsByCampId(String campId);
    public String givePerformanceReview(Performance performance, CampCommitteeMember campcommitteemember);
    public String editPerformanceReview(Performance performance);
    public String deletePerformanceReview(String performanceId, CampCommitteeMember CCMId);
}