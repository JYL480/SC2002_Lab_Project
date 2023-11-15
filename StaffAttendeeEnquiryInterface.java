
import java.util.ArrayList;
// import java.util.*;

public interface StaffAttendeeEnquiryInterface {

    public ArrayList<Object[]> viewAllAttendeesEnquiriesByCampId(String campId);
    public void replyToAttendeeEnquiry(Enquiry enquiry);
}