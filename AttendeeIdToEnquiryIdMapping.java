public class AttendeeIdToEnquiryIdMapping {
    private String AttendeeId;
    public String getAttendeeId() {
        return AttendeeId;
    }

    public void setAttendeeId(String attendeeId) {
        AttendeeId = attendeeId;
    }

    private String EnquiryId;

    public String getEnquiryId() {
        return EnquiryId;
    }

    public void setEnquiryId(String enquiryId) {
        EnquiryId = enquiryId;
    }

    public AttendeeIdToEnquiryIdMapping(String attendeeId, String enquiryId) {
        this.AttendeeId = attendeeId;
        this.EnquiryId = enquiryId;
    }
}
