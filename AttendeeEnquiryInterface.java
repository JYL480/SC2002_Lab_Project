import java.util.*;

public interface AttendeeEnquiryInterface {

    public void submitEnquiry(Enquiry e);
    public ArrayList<Enquiry> viewAllEnquires();
    public Enquiry viewEnquirybyId(String id);
    public void editEnquiry(Enquiry e, Enquiry newE);
    public void deleteEnquiry(Enquiry e);
}