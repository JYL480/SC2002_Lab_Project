public class Suggestion {

	private String id;
	private boolean isProcessed;
	private boolean isApproved;
	private String campId;
	private String newCampname;
	private boolean newCampisVisible;
	private String newCampStartDate;
	private String newCampEndDate;
	private String newRegClosingDate;
	private String newLocation;
	private int newTotalSlots;
	private int newCampCommitteeSlots;
	private String newDescription;


	public Suggestion() {
		this.id = "";  // Set a default value for id, you can change it to any default value you prefer
		this.isProcessed = false;
		this.isApproved = false;
		// Initialize other fields with default values if needed
		this.campId = "";
		this.newCampname = "";
		this.newCampisVisible = false;
		this.newCampStartDate = "";
		this.newCampEndDate = "";
		this.newRegClosingDate = "";
		this.newLocation = "";
		this.newTotalSlots = 0;  // Set a default value for int fields
		this.newCampCommitteeSlots = 0;  // Set a default value for int fields
		this.newDescription = "";
	}

	public Suggestion(String id, boolean isProcessed, boolean isApproved, String campId,
					String newCampName, boolean newCampisVisible, String newCampStartDate,
					String newCampEndDate, String newRegClosingDate, String newLocation,
					int newTotalSlots, int newCampCommitteeSlots, String newDescription) {
		this.id = id;
		this.isProcessed = isProcessed;
		this.isApproved = isApproved;
		this.campId = campId;
		this.newCampname = newCampName;
		this.newCampisVisible = newCampisVisible;
		this.newCampStartDate = newCampStartDate;
		this.newCampEndDate = newCampEndDate;
		this.newRegClosingDate = newRegClosingDate;
		this.newLocation = newLocation;
		this.newTotalSlots = newTotalSlots;
		this.newCampCommitteeSlots = newCampCommitteeSlots;
		this.newDescription = newDescription;
	}


	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}



	public boolean getIsProcessed() {
		return this.isProcessed;
	}


	public void setIsProcessed(boolean isProcessed) {
		this.isProcessed = isProcessed;
	}

	public boolean getIsApproved()
	{
		return this.isApproved;
	}

	public void setIsApproved(boolean isApproved)
	{
		this.isApproved = isApproved;
	}

	public String getCampId() {
        return this.campId;
    }

    public void setCampId(String campId) {
        this.campId = campId;
    }

    public String getNewCampname() {
        return this.newCampname;
    }

    public void setNewCampname(String newCampname) {
        this.newCampname = newCampname;
    }

    public boolean isNewCampisVisible() {
        return this.newCampisVisible;
    }

    public void setNewCampisVisible(boolean newCampisVisible) {
        this.newCampisVisible = newCampisVisible;
    }

    public String getNewCampStartDate() {
        return this.newCampStartDate;
    }

    public void setNewCampStartDate(String newCampStartDate) {
        this.newCampStartDate = newCampStartDate;
    }

    public String getNewCampEndDate() {
        return this.newCampEndDate;
    }

    public void setNewCampendDate(String newCampEndDate) {
        this.newCampEndDate = newCampEndDate;
    }

    public String getNewRegClosingDate() {
        return this.newRegClosingDate;
    }

    public void setNewRegClosingDate(String newRegClosingDate) {
        this.newRegClosingDate = newRegClosingDate;
    }

    public String getNewLocation() {
        return this.newLocation;
    }

    public void setNewLocation(String newLocation) {
        this.newLocation = newLocation;
    }

    public int getNewTotalSlots() {
        return this.newTotalSlots;
    }

    public void setNewTotalSlots(int newTotalSlots) {
        this.newTotalSlots = newTotalSlots;
    }

    public int getNewCampCommitteeSlots() {
        return this.newCampCommitteeSlots;
    }

    public void setNewCampCommitteeSlots(int newCampCommitteeSlots) {
        this.newCampCommitteeSlots = newCampCommitteeSlots;
    }

    public String getNewDescription() {
        return this.newDescription;
    }

    public void setNewDescription(String newDescription) {
        this.newDescription = newDescription;
    }


}