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

	/**
	 * 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 
	 * @param rating
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}

	/**
	 * 
	 * @param areasDoneWell
	 */
	public void setAreasDoneWell(String areasDoneWell) {
		this.areasDoneWell = areasDoneWell;
	}

	/**
	 * 
	 * @param areasToImprove
	 */
	public void setAreasToImprove(String areasToImprove) {
		this.areasToImprove = areasToImprove;
	}

	/**
	 * 
	 * @param isProcessed
	 */
	public void setIsProcessed(boolean isProcessed) {
		this.isProcessed = isProcessed;
	}

}