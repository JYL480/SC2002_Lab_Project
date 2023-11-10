import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class CampStudentReport extends Report {

	private boolean includeAttendees;
	private boolean includeCampCommitteeMembers;
	private HashMap<Integer, Student> listOfStudents;

	public CampStudentReport(String id, Camp camp, String fileType, boolean print, 
    boolean includeAttendees, boolean includesCampCommitteeMembers, 
    HashMap<Integer, Student> listOfStudents) {
        super(id, camp, fileType, print);
        this.includeAttendees = includeAttendees;
        this.includeCampCommitteeMembers = includesCampCommitteeMembers;
        this.listOfStudents = listOfStudents;
}


	public boolean getIncludeAttendees() {
		return this.includeAttendees;
	}

	public boolean getIncludeCampCommitteeMembers() {
		return this.includeCampCommitteeMembers;
	}

	public HashMap<Integer, Student> getListOfStudents() {
		return this.listOfStudents;
	}

	/**
	 * 
	 * @param includeAttendee
	 */
	public void setIncludeAttendees(boolean includeAttendee) {
		this.includeAttendees = includeAttendee;
	}

	/**
	 * 
	 * @param includeCampCommitteeMembers
	 */
	public void setIncludeCampCommitteeMembers(boolean includeCampCommitteeMembers) {
		this.includeCampCommitteeMembers = includeCampCommitteeMembers;
	}

	/**
	 * 
	 * @param listOfStudents
	 */
	public void setListOfStudents(HashMap<Integer, Student> listOfStudents) {
		this.listOfStudents = listOfStudents;
	}

}