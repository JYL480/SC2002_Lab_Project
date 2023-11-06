import java.util.ArrayList;
import java.util.HashMap;

public class Attendee extends User implements AttendeeEnquiryInterface, AttendeeCampInterface{
    private HashMap<String, Enquiry> equiryIdToEnquiyObjMap;
    private HashMap<String, Camp> campIdToCampObjMap;

    public Attendee(String userID, String password, String name, String faculty, String email){
        super(userID, password, name, faculty, email);
        this.equiryIdToEnquiyObjMap = new HashMap<>();
        this.campIdToCampObjMap = new HashMap<>();
    }

    public HashMap<String, Camp> getCampIdToCampObjMap() {
        return campIdToCampObjMap;
    }
    public HashMap<String, Enquiry> getEquiryIdToEnquiyObjMap() {
        return equiryIdToEnquiyObjMap;
    }
    public void setCampIdToCampObjMap(HashMap<String, Camp> campIdToCampObjMap) {
        this.campIdToCampObjMap = campIdToCampObjMap;
    }
    public void setEquiryIdToEnquiyObjMap(HashMap<String, Enquiry> equiryIdToEnquiyObjMap) {
        this.equiryIdToEnquiyObjMap = equiryIdToEnquiyObjMap;
    }
// Implementation of AttendeeEnquiryInterface
    public void submitEnquiry(Enquiry e){
        this.equiryIdToEnquiyObjMap.put(e.getId(), e);
    }
    public ArrayList<Enquiry> viewAllEnquires(){
        return new ArrayList<Enquiry>(this.equiryIdToEnquiyObjMap.values());
    }
    public Enquiry viewEnquirybyId(String id){
        return this.equiryIdToEnquiyObjMap.get(id);
    }
    public void editEnquiry(Enquiry e, Enquiry newE){
        this.equiryIdToEnquiyObjMap.remove(e.getId());
        this.equiryIdToEnquiyObjMap.put(e.getId(), newE);
    }
    public void deleteEnquiry(Enquiry e){
        this.equiryIdToEnquiyObjMap.remove(e.getId());
    }
// Implementation of AttendeeCampInterface
    public void registerForCampAsAttendee(Camp camp){
        this.campIdToCampObjMap.put(camp.getId(), camp);
    }
}
