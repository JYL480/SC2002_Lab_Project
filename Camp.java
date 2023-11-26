// import java.util.ArrayList;
// import java.time.String;
import java.time.LocalDate;

public class Camp {

	private String id;
	private String name;
	private boolean isVisible;
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalDate regClosingDate;
	private String location;
	private int totalSlots;
	private int campCommitteeSlots;
	private String description;
	private String facultyId;
	public String getFacultyId() {
		return facultyId;
	}


	public void setFacultyId(String facultyId) {
		this.facultyId = facultyId;
	}


	private String staffId;
	public String getStaffId() {
		return staffId;
	}


	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}


	private boolean openToAll;

	public boolean isOpenToAll() {
		return openToAll;
	}


	public void setOpenToAll(boolean openToAll) {
		this.openToAll = openToAll;
	}


	public Camp(String id, String name, boolean isVisible, LocalDate startDate, 
	LocalDate endDate, LocalDate regClosingDate, String location, 
	int totalSlots, int campCommitteeSlots, String description, String facultyId, String staffId, boolean openToAll){
		this.id = id;
		this.name = name;
		this.isVisible = isVisible;
		this.startDate = startDate;
		this.endDate = endDate;
		this.regClosingDate = regClosingDate;
		this.location = location;
		this.totalSlots = totalSlots;
		this.campCommitteeSlots = campCommitteeSlots;
		this.description = description;
		this.facultyId = facultyId;
		this.staffId = staffId;
		this.openToAll = openToAll;
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

	public LocalDate getStartDate() {
		return this.startDate;
	}

	public LocalDate getEndDate() {
		return this.endDate;
	}

	public LocalDate getRegClosingDate() {
		return this.regClosingDate;
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

	
	public void setId(String id) {
		this.id = id;
	}

	
	public void setName(String name) {
		this.name = name;
	}


	public void setIsVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}


	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}


	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}


	public void setRegClosingDate(LocalDate regClosingDate) {
		this.regClosingDate = regClosingDate;
	}



	public void setLocation(String location) {
		this.location = location;
	}


	public void setTotalSlots(int totalSlots) {
		this.totalSlots = totalSlots;
	}


	public void setCampCommitteeSlots(int campCommitteeSlots) {
		this.campCommitteeSlots = campCommitteeSlots;
	}


	public void setDescription(String description) {
		this.description = description;
	}

	// public static void main(String[] args) {
    //     Camp camp = new Camp("123", "MyCamp", true, null, null, null,
    //             new ArrayList<>(), "CampLocation", 100, 10, "Description");

    //     String customStartDate = String.of(2023, 11, 13, 10, 0);
    //     camp.setStartDate(customStartDate);

    //     String customEndDate = String.of(2023, 11, 15, 16, 30);
    //     camp.setEndDate(customEndDate);

    //     String customRegClosingDate = String.of(2023, 11, 10, 23, 59);
    //     camp.setRegClosingDate(customRegClosingDate);

    //     // Other setters can be used similarly
	// 	System.out.println(camp.getRegClosingDate());
	// 	System.out.println(camp.getStartDate());
    // }
}



