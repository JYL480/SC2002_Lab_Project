
import java.util.ArrayList;
// import java.util.*;

public interface StaffAttendeeEnquiryInterface {

    public ArrayList<Enquiry> viewAllAttendeesEnquiriesByCamp(Camp camp);
    public void replyToAttendeeEnquiry(Enquiry e, String text);
}