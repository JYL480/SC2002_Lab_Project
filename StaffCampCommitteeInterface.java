import java.util.ArrayList;

public interface StaffCampCommitteeInterface {

	/**
	 * 
	 * @param Camp
	 */
	ArrayList<Suggestion> viewCommitteeMembersSuggetionsByCamp(int Camp);

	/**
	 * 
	 * @param CampCommitteeMember
	 * @param Performance
	 */
	void givePerformanceReview(int CampCommitteeMember, int Performance);

	/**
	 * 
	 * @param CampCommitteeMember
	 * @param Performance
	 */
	void editPerformanceReview(int CampCommitteeMember, int Performance);

	/**
	 * 
	 * @param CampCommitteeMember
	 */
	void deletePerformanceReview(int CampCommitteeMember);

}