public class Suggestion {

	private String id;
	private boolean isProcessed;
	private String suggestionStr;

	public Suggestion() {
		this(null, false, null);
    }


	public Suggestion(String id, boolean isProcessed, String suggestionStr) {
        this.id = id;
        this.isProcessed = isProcessed;
        this.suggestionStr = suggestionStr;
    }

	public String getId() {
		return this.id;
	}

	public String getSuggestionStr() {
		return this.suggestionStr;
	}

	public boolean getIsProcessed() {
		return this.isProcessed;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setSuggestionStr(String suggestionStr) {
		this.suggestionStr = suggestionStr;
	}


	public void setIsProcessed(boolean isProcessed) {
		this.isProcessed = isProcessed;
	}

}