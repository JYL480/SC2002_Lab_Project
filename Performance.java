public class Performance {

	private String id;
	private int rating;
	private String areasDoneWell;
	private String areasToImprove;
	private boolean isProcessed;

    public Performance(String id, int rating, String areasDoneWell, String areasToImprove, boolean isProcessed) {
        this.id = id;
        this.rating = rating;
        this.areasDoneWell = areasDoneWell;
        this.areasToImprove = areasToImprove;
        this.isProcessed = isProcessed;
    }

	public String getId() {
		return this.id;
	}

	public int getRating() {
		return this.rating;
	}

	public String getAreasDoneWell() {
		return this.areasDoneWell;
	}

	public String getAreasToImprove() {
		return this.areasToImprove;
	}

	public boolean getIsProcessed() {
		return this.isProcessed;
	}


	public void setId(String id) {
		this.id = id;
	}


	public void setRating(int rating) {
		this.rating = rating;
	}


	public void setAreasDoneWell(String areasDoneWell) {
		this.areasDoneWell = areasDoneWell;
	}

	public void setAreasToImprove(String areasToImprove) {
		this.areasToImprove = areasToImprove;
	}


	public void setIsProcessed(boolean isProcessed) {
		this.isProcessed = isProcessed;
	}

}