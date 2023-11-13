import java.util.ArrayList;

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

	public Camp(String id, String name, boolean isVisible, DateTime startDate, 
	DateTime endDate, DateTime regClosingDate,
	ArrayList<String> userGroupOpened, String location, 
	int totalSlots, int campCommitteeSlots, String description){
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
	 * @param campCommitteeSlots
	 */
	public void setCampCommitteeSlots(int campCommitteeSlots) {
		this.campCommitteeSlots = campCommitteeSlots;
	}

	/**
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}



}