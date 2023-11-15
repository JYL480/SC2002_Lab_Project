import java.util.ArrayList;

public class Staff extends User implements StaffAttendeeEnquiryInterface, StaffCampCommitteeInterface, StaffCampInterface, StaffReportInterface {

    public Staff(String id, String password, String name, String email) {
        super(id, password, name, email);
    }

    @Override
    public ArrayList<Pair<Enquiry, Attendee>>viewAllAttendeesEnquiriesByCampId(String campId) {

        ArrayList<String> attendeeIds = DB_AttendeeIdToCampId.getAttendeeIds(campId);\
         ArrayList<Pair<Enquiry, Attendee>> enquiryAttendeePairs = new ArrayList<>();
        for(String aId: attendeeIds)
        {
            ArrayList<String> tmp = DB_AttendeeIdToEnquiryId.getEnquiryIds(aId);
            Attendee attendee = DB_Attendee.readAttendee(aId);
            for(String eId: tmp)
            {
                Enquiry enquiry = DB_Enquiry.readEnquiry(eId);
                enquiryIds.add(new Pair<>(enquiry, attendee));
            }
        }
        // Implement logic to view all attendee enquiries for a specific camp
        return enquiryAttendeePairs;
    }

    @Override
    public void replyToAttendeeEnquiry(String enquiryId, String replyStr) {
        // Implement logic to reply to an attendee enquiry
        Enquiry enquiry = DB_Enquiry.readEnquiry(enquiryId);
        enquiry.setReplyText(replyStr);
        enquiry.setRepliedByName(this.name);
        enquiry.setRepliedByStaff(true);
        DB_Enquiry.updateEnquiry(enquiry);
    }

    @Override
    public ArrayList<Suggestion> viewCommiteeMembersSuggestionsByCampId(String campId) {
        // Implement logic to view committee members' suggestions for a specific camp
        return new ArrayList<>();
    }

    @Override
    public String givePerformanceReview(Performance performance, CampCommitteeMember campCommitteeMember) {
        // Implement logic to give performance review to a camp committee member
        return "Performance review given";
    }

    @Override
    public String editPerformanceReview(Performance performance) {
        // Implement logic to edit performance review of a camp committee member
        return "Performance review edited";
    }

    @Override
    public String deletePerformanceReview(String performanceId, CampCommitteeMember CCMId) {
        // Implement logic to delete performance review of a camp committee member
        return "Performance review deleted";
    }

    @Override
    public void createCamp(Camp camp, ArrayList<Faculty> allowedFaculty) {
        // Implement logic to create a camp
        DB_Camp.createCamp(camp);
        DB_StaffIdToCampId.createMapping(this.id, camp.getId());
        for(Faculty f: allowedFaculty)
        {
            DB_CampIdToFacultyId.createMapping(this.id, f.getId());
        }
        
    }

    @Override
    public void deleteCamp(String campId) {
        // Implement logic to delete a camp
        DB_Camp.deleteCamp(campId);
        DB_StaffIdToCampId.deleteMapping(this.id, campId);
        DB_CampIdToFacultyId.deleteMappingsByCampId(campId);
        DB_AttendeeIdToCampId.deleteMappingsByCampId(campId);
    }

    @Override
    public ArrayList<Camp> viewAllCamps() {
        // Implement logic to view all camps
        return DB_Camp.getAllCamps();
    }

    @Override
    public ArrayList<Camp> viewSelfCreatedCamps() {
        // Implement logic to view camps created by the staff
        ArrayList<String> campIds = DB_StaffIdToCampId.getCampIds(this.id);
        ArrayList<Camp> camps; 
        for(String id: campsIds)
        {
            Camp camp = DB_Camp.readCamp(id);
            camps.add(camp);
        }
        return camps;
    }

    @Override
    public void editCamp(Camp camp) {
        // Implement logic to edit a camp
        DB_Camp.updateCamp(camp);
    }

    @Override
    public void generateReportOfStudentsAttendingSelfCreatedCamp(Camp camp) {
        // Implement logic to generate a report of students attending a self-created camp
    }

    @Override
    public void generatePerformanceReportOfCampCommitteeMembers(Camp camp) {
        // Implement logic to generate a performance report of camp committee members for a specific camp
    }

    public static void main(String[ args])
    {
        Staff s1 = new Staff(RandomIdGenerator.generateRandomId(), "password", "Derek Zaw", "dzaw@mail.com");
        Camp c1 = 
        s1.createCamp(new Camp(RandomIdGenerator.generateRandomId(), "Cool Kids Camp", true, "2023-07-01", "2023-07-15", "2023-06-15", "Camp Location", 100, 10, "Camp Description"))

    }
}
