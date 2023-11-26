public class CCMIdToPointsMapping {
    private String ccmId;
    private int points;
    public CCMIdToPointsMapping(String ccmId, int points) {
        this.ccmId = ccmId;
        this.points = points;
    }
    public String getCcmId() {
        return ccmId;
    }
    public void setCcmId(String ccmId) {
        this.ccmId = ccmId;
    }
    public int getPoints() {
        return points;
    }
    public void setPoints(int points) {
        this.points = points;
    }
}
