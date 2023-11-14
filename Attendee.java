import java.util.ArrayList;

public class Attendee extends User implements AttendeeEnquiryInterface, AttendeeCampInterface{
    private Camp camp;
    // need add in attribute for the enquiryIdToEnquiyObjMap and campIdToCampObjMap

    public Attendee(String id, String password, String name, String faculty, String email){
        super(id, password, name, faculty, email);
        this.camp = null;
    }

// Implementation of AttendeeEnquiryInterface
    public void submitEnquiry(Enquiry e){
        // this.equiryIdToEnquiyObjMap.put(e.getId(), e);
    }
    public ArrayList<Enquiry> viewAllEnquires(){
        // return new ArrayList<Enquiry>(this.equiryIdToEnquiyObjMap.values());
    }
    public Enquiry viewEnquirybyId(String id){
        // return this.equiryIdToEnquiyObjMap.get(id);
    }
    public void editEnquiry(Enquiry e, Enquiry newE){
        // this.equiryIdToEnquiyObjMap.remove(e.getId());
        // this.equiryIdToEnquiyObjMap.put(e.getId(), newE);
    }
    public void deleteEnquiry(Enquiry e){
        // this.equiryIdToEnquiyObjMap.remove(e.getId());
    }
// Implementation of AttendeeCampInterface
    public void registerForCampAsAttendee(Camp camp){
        // this.campIdToCampObjMap.put(camp.getId(), camp);
    }
}