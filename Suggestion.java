public class Suggestion {

	private String id;
	private Camp newCamp;
	private boolean isProcessed;
	private boolean print;

	public Suggestion(String id, Camp newCamp, boolean isProcessed, boolean print) {
        this.id = id;
        this.newCamp = newCamp;
        this.isProcessed = isProcessed;
        this.print = print;
    }


	public String getId() {
		return this.id;
	}

	public Camp getNewCamp() {
		return this.newCamp;
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
	 * @param newCamp
	 */
	public void setNewCamp(Camp newCamp) {
		this.newCamp = newCamp;
	}

	/**
	 * 
	 * @param isProcessed
	 */
	public void setIsProcessed(boolean isProcessed) {
		this.isProcessed = isProcessed;
	}

}