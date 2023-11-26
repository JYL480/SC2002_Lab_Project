public class CCMIdToCampIdMapping {
    private String ccmId;
    private String campId;
    private boolean isWithdrawn;
    public CCMIdToCampIdMapping(String ccmId, String campId, boolean isWithdrawn) {
        this.ccmId = ccmId;
        this.campId = campId;
        this.isWithdrawn = isWithdrawn;
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
    public boolean isWithdrawn() {
        return isWithdrawn;
    }
    public void setWithdrawn(boolean isWithdrawn) {
        this.isWithdrawn = isWithdrawn;
    }
}
