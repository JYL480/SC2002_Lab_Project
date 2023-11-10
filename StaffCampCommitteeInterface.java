import java.util.ArrayList;

public interface StaffCampCommitteeInterface {
    public ArrayList<Suggestion> viewCommiteeMembersSuggestionsByCamp(Camp camp);
    public String givePerformanceReview(Performance performance, CampCommitteeMember CampCommitteeMember);
    public String editPerformanceReview(Performance performance, CampCommitteeMember CampCommitteeMember);
    public String deletePerformanceReview(CampCommitteeMember CampCommitteeMember);
}