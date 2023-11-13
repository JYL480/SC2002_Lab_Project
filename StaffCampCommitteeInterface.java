import java.util.ArrayList;

public interface StaffCampCommitteeInterface {
    public ArrayList<Suggestion> viewCommiteeMembersSuggestionsByCamp(Camp camp);
    public String givePerformanceReview(Performance performance, CampCommiteeMember campcommiteemember);
    public String editPerformanceReview(Performance performance, CampCommiteeMember campcommiteemember);
    public String deletePerformanceReview(CampCommiteeMember campcommiteemember);
}
