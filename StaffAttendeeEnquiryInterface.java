
import java.util.ArrayList;
// import java.util.*;

public interface StaffAttendeeEnquiryInterface {

    public ArrayList<Enquiry> viewAllAttendeesEnquiriesByCampId(String campId);
    public void replyToAttendeeEnquiry(String enquiryId, String replyStr);
}