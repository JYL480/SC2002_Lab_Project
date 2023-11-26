public class Suggestion {

	private String id;
	private boolean isProcessed;
	private boolean isApproved;
	private String campId;
	private String comment;


	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Suggestion() {
		this.id = "";  // Set a default value for id, you can change it to any default value you prefer
		this.isProcessed = false;
		this.isApproved = false;
		// Initialize other fields with default values if needed
		this.campId = "";
		this.comment = "";
	}

	public Suggestion(String id, boolean isProcessed, boolean isApproved, String campId,
					String comment) {
		this.id = id;
		this.isProcessed = isProcessed;
		this.isApproved = isApproved;
		this.campId = campId;
		this.comment = comment;
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
}