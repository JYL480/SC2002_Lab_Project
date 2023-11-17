import java.util.ArrayList;

public class Attendee extends Student implements AttendeeEnquiryInterface, AttendeeCampInterface{
    // need add in attribute for the enquiryIdToEnquiyObjMap and campIdToCampObjMap

    public Attendee(String id, String password, String name, String email, boolean isCampCommittee){
        super(id, password, name, email, isCampCommittee);
    }

// Implementation of AttendeeEnquiryInterface
    public void submitEnquiry(Enquiry e){
        // this.equiryIdToEnquiyObjMap.put(e.getId(), e);
        DB_AttendeeIdToEnquiryId.createMapping(getId(), e.getId());
        DB_Enquiry.createEnquiry(e);
    }
    public ArrayList<Enquiry> viewAllEnquires(){
        ArrayList<Enquiry> enquiries = DB_Enquiry.getAllEnquiries();
        for(Enquiry e: enquiries){
            System.out.println("ID: "+ e.id + " Subject: " + e.subject + " Description: " + e.description);
        }
        return enquiries;
    }

    public Enquiry viewEnquirybyId(String id){
        return DB_Enquiry.readEnquiry(id);
        
    }
    public void editEnquiry(Enquiry e, Enquiry newE){
        DB_AttendeeIdToEnquiryId.createMapping(getId(), e.getId());
        DB_Enquiry.updateEnquiry(newE);
    }
    public void deleteEnquiry(Enquiry e){
        DB_AttendeeIdToEnquiryId.createMapping(getId(), e.getId());
        DB_Enquiry.deleteEnquiry(e.getId());
    }
// Implementation of AttendeeCampInterface
    public void registerForCampAsAttendee(Camp camp){
        DB_AttendeeIdToCampId.createMapping(this.getId(), camp.getId());
        
    }
}