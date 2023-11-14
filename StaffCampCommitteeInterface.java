import java.util.ArrayList;

public interface StaffCampCommitteeInterface {
    public ArrayList<Suggestion> viewCommiteeMembersSuggestionsByCamp(Camp camp);
    public String givePerformanceReview(Performance performance, CampCommitteeMember campcommitteemember);
    public String editPerformanceReview(Performance performance, CampCommitteeMember campcommitteemember);
    public String deletePerformanceReview(CampCommitteeMember campcommiteemember);
}