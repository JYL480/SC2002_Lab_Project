import java.util.ArrayList;
import java.time.LocalDateTime;

public class Camp {

	private String id;
	private String name;
	private boolean isVisible;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private LocalDateTime regClosingDate;
	private ArrayList<String> userGroupOpened;
	private String location;
	private int totalSlots;
	private int campCommitteeSlots;
	private String description;

	public Camp(String id, String name, boolean isVisible, LocalDateTime startDate, 
	LocalDateTime endDate, LocalDateTime regClosingDate,
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

	public LocalDateTime getStartDate() {
		return this.startDate;
	}

	public LocalDateTime getEndDate() {
		return this.endDate;
	}

	public LocalDateTime getRegClosingDate() {
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
	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	/**
	 * 
	 * @param endDate
	 */
	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	/**
	 * 
	 * @param regClosingDate
	 */
	public void setRegClosingDate(LocalDateTime regClosingDate) {
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

	// public static void main(String[] args) {
    //     Camp camp = new Camp("123", "MyCamp", true, null, null, null,
    //             new ArrayList<>(), "CampLocation", 100, 10, "Description");

    //     LocalDateTime customStartDate = LocalDateTime.of(2023, 11, 13, 10, 0);
    //     camp.setStartDate(customStartDate);

    //     LocalDateTime customEndDate = LocalDateTime.of(2023, 11, 15, 16, 30);
    //     camp.setEndDate(customEndDate);

    //     LocalDateTime customRegClosingDate = LocalDateTime.of(2023, 11, 10, 23, 59);
    //     camp.setRegClosingDate(customRegClosingDate);

    //     // Other setters can be used similarly
	// 	System.out.println(camp.getRegClosingDate());
	// 	System.out.println(camp.getStartDate());
    // }
}



