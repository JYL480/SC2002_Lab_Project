public class AttendeeIdToCampIdMapping {
    private String AttendeeId;
    public String getAttendeeId() {
        return AttendeeId;
    }

    public void setAttendeeId(String attendeeId) {
        AttendeeId = attendeeId;
    }

    private String CampId;

    public String getCampId() {
        return CampId;
    }

    public void setCampId(String campId) {
        CampId = campId;
    }
    private boolean isWithdrawn;
    public boolean isWithdrawn() {
        return isWithdrawn;
    }

    public void setWithdrawn(boolean isWithdrawn) {
        this.isWithdrawn = isWithdrawn;
    }

    public AttendeeIdToCampIdMapping(String attendeeId, String campId) {
        this.AttendeeId = attendeeId;
        this.CampId = campId;
        this.isWithdrawn = false;
    }

    public AttendeeIdToCampIdMapping(String attendeeId, String campId, boolean isWithdrawn) {
        this.AttendeeId = attendeeId;
        this.CampId = campId;
        this.isWithdrawn = isWithdrawn;
    }
}
