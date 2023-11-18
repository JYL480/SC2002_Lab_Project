import java.util.ArrayList;

public class Attendee extends Student implements AttendeeEnquiryInterface, AttendeeCampInterface{
    // need add in attribute for the enquiryIdToEnquiyObjMap and campIdToCampObjMap

    public Attendee(String id, String password, String name, String email, boolean isCampCommittee){
        super(id, password, name, email, isCampCommittee);
    }

// Implementation of AttendeeEnquiryInterface
    public void submitEnquiry(Enquiry e){
        DB_AttendeeIdToEnquiryId.createMapping(getId(), e.getId());
        DB_Enquiry.createEnquiry(e);
    }

    public ArrayList<Enquiry> viewAllEnquires()
    {
        ArrayList<String> enquiryIds = DB_AttendeeIdToEnquiryId.getEnquiryIds(this.getId());
        ArrayList<Enquiry> enquiries = new ArrayList<>();
        for(String eId: enquiryIds)
        {
            enquiries.add(DB_Enquiry.readEnquiry(eId));
        }
        return enquiries;
    }

    public Enquiry viewEnquirybyId(String id){
        return DB_Enquiry.readEnquiry(id);
    }

    public void editEnquiry(Enquiry newE){
        DB_Enquiry.updateEnquiry(newE);
    }

    public void deleteEnquiry(Enquiry e)
    {
        DB_AttendeeIdToEnquiryId.deleteMapping(this.getId(), e.getId());
        DB_Enquiry.deleteEnquiry(e.getId());
    }
    
    public void withdrawFromCampAsAttendee(String campId)
    {
        DB_AttendeeIdToCampId.deleteMapping(this.getId(), campId);
        DB_AttendeeIdToWithdrawnCampId.createMapping(this.getId(), campId);
        Camp updatedCamp = DB_Camp.readCamp(campId);
        updatedCamp.setTotalSlots(updatedCamp.getTotalSlots() + 1); 
        DB_Camp.updateCamp(updatedCamp);
    }

    public ArrayList<Camp> viewRegisteredCampsAsAttendee()
    {
        ArrayList<String> campIds = DB_AttendeeIdToCampId.getCampIds(this.getId());
        ArrayList<Camp> camps = new ArrayList<>();
        for(String cId: campIds)
        {
            camps.add(DB_Camp.readCamp(cId));
        }

        return camps;
    }

}