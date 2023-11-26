public class CCMIdToCampIdMapping {
    private String ccmId;
    private String campId;
    public CCMIdToCampIdMapping(String ccmId, String campId) {
        this.ccmId = ccmId;
        this.campId = campId;
    }
    public String getCcmId() {
        return ccmId;
    }
    public void setCcmId(String ccmId) {
        this.ccmId = ccmId;
    }
    public String getCampId() {
        return campId;
    }
    public void setCampId(String campId) {
        this.campId = campId;
    }
}
