import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Camp {

	private String id;
	private String name;
	private boolean isVisible;
	private DateTime startDate;
	private DateTime endDate;
	private DateTime regClosingDate;
	private ArrayList<String> userGroupOpened;
	private String location;
	private int totalSlots;
	private int campCommitteeSlots;
	private String description;
	private Staff staffInCharge;
	private HashMap<Integer, CampCommitteeMember> listOfCampCommitteeMembers;
	private HashMap<Integer, Attendee> listOfRegisteredAttendees;

	public Camp(String id, String name, boolean isVisible, DateTime startDate, 
	DateTime endDate, DateTime regClosingDate,
	ArrayList<String> userGroupOpened, String location, 
	int totalSlots, int campCommitteeSlots, String description, 
	Staff staffInCharge, HashMap<Integer, Attendee> listOfRegisteredAttendees,
	HashMap<Integer, CampCommitteeMember> listOfCampCommitteeMembers) {
		this.id = id;
		this.name = name;
		this.isVisible = isVisible;
		this.startDate = startDate;
		this.endDate = endDate;
		this.regClosingDate = regClosingDate;
		this.userGroupOpened = new ArrayList<>();
		this.location = location;
		this.totalSlots = totalSlots;
		this.campCommitteeSlots = campCommitteeSlots;
		this.description = description;
		this.staffInCharge = staffInCharge;
		this.listOfRegisteredAttendees = listOfRegisteredAttendees; // this should be pointing to theri hash map
		this.listOfCampCommitteeMembers = listOfCampCommitteeMembers;
}

	public String getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public boolean getIsVisible() {
		return this.isVisible;
	}

	public DateTime getStartDate() {
		return this.startDate;
	}

	public DateTime getEndDate() {
		return this.endDate;
	}

	public DateTime getRegClosingDate() {
		return this.regClosingDate;
	}

	public ArrayList<String> getUserGroupOpened() {
		return this.userGroupOpened;
	}

	public String getLocation() {
		return this.location;
	}

	public int getTotalSlots() {
		return this.totalSlots;
	}

	public int getCampCommitteeSlots() {
		return this.campCommitteeSlots;
	}

	public String getDescription() {
		return this.description;
	}

	public Staff getStaffInCharge() {
		return this.staffInCharge;
	}

	public HashMap<Integer, CampCommitteeMember> getListOfCampCommitteeMembers() {
		return this.listOfCampCommitteeMembers;
	}

	public HashMap<Integer, Attendee> getListOfRegisteredAttendees() {
		return this.listOfRegisteredAttendees;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @param isVisible
	 */
	public void setIsVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	/**
	 * 
	 * @param startDate
	 */
	public void setStartDate(DateTime startDate) {
		this.startDate = startDate;
	}

	/**
	 * 
	 * @param endDate
	 */
	public void setEndDate(DateTime endDate) {
		this.endDate = endDate;
	}

	/**
	 * 
	 * @param regClosingDate
	 */
	public void setRegClosingDate(DateTime regClosingDate) {
		this.regClosingDate = regClosingDate;
	}

	/**
	 * 
	 * @param userGroupOpened
	 */
	public void setUserGroupOpened(ArrayList<String> userGroupOpened) {
		this.userGroupOpened = userGroupOpened;
	}

	/**
	 * 
	 * @param location
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * 
	 * @param totalSlots
	 */
	public void setTotalSlots(int totalSlots) {
		this.totalSlots = totalSlots;
	}

	/**
	 * 
	 * @param comapCommitteeSlots
	 */
	public void setCampCommitteeSlots(int comapCommitteeSlots) {
		this.campCommitteeSlots = comapCommitteeSlots;
	}

	/**
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 
	 * @param staffInCharge
	 */
	public void setStaffInCharge(Staff staffInCharge) {
		this.staffInCharge = staffInCharge;
	}

	/**
	 * 
	 * @param listOfCampCommitteeMembers
	 */
	public void setListOfCampCommitteeMembers(HashMap<Integer, CampCommitteeMember> listOfCampCommitteeMembers) {
		this.listOfCampCommitteeMembers = listOfCampCommitteeMembers;
	}

	/**
	 * 
	 * @param listOfRegisteredAttendees
	 */
	public void setListOfRegisteredAttendees(HashMap<Integer, Attendee> listOfRegisteredAttendees) {
		this.listOfRegisteredAttendees = listOfRegisteredAttendees;
	}

}