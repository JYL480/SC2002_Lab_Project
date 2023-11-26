public class CCMIdToSuggestionIdMapping {
    private String ccmId;
    private String suggestionId;
    public CCMIdToSuggestionIdMapping(String ccmId, String suggestionId) {
        this.ccmId = ccmId;
        this.suggestionId = suggestionId;
    }
    public String getCcmId() {
        return ccmId;
    }
    public void setCcmId(String ccmId) {
        this.ccmId = ccmId;
    }
    public String getSuggestionId() {
        return suggestionId;
    }
    public void setSuggestionId(String suggestionId) {
        this.suggestionId = suggestionId;
    }
}
