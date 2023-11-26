import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Staff extends User implements StaffAttendeeEnquiryInterface, StaffCampCommitteeInterface, StaffCampInterface, StaffReportInterface {

    public Staff(String id, String password, String name, String email, String facultyId) {
        super(id, password, name, email, facultyId);
    }

    public Staff(String id, String password, String name, String email, boolean isNewLogin, String facultyId) {
        super(id, password, name, email, isNewLogin, facultyId);
    }

    public ArrayList<Object[]> viewAllAttendeesEnquiriesByCampId(String campId) {
        ArrayList<String> attendeeIds = DB_AttendeeIdToCampId.getAttendeeIds(campId);
        ArrayList<Object[]> enquiryAttendeeArrays = new ArrayList<>();
        for (String aId : attendeeIds) {
            ArrayList<String> tmp = DB_AttendeeIdToEnquiryId.getEnquiryIds(aId);
            Attendee attendee = new Attendee(DB_Student.readStudent(aId));
            for (String eId : tmp) {
                Enquiry enquiry = DB_Enquiry.readEnquiry(eId);
                enquiryAttendeeArrays.add(new Object[]{enquiry, attendee});
            }
        }
        // Implement logic to view all attendee enquiries for a specific camp
        return enquiryAttendeeArrays;
    }
    

  
    public void replyToAttendeeEnquiry(Enquiry enquiry) {
        // Implement logic to reply to an attendee enquiry
        DB_Enquiry.updateEnquiry(enquiry);
    }

    
    public ArrayList<Suggestion> viewCommiteeMembersSuggestionsByCampId(String campId) {
        // Implement logic to view committee members' suggestions for a specific camp
        ArrayList<String> suggestionIds = new ArrayList<>();
        ArrayList<Suggestion> suggestions = new ArrayList<>();
        ArrayList<String> ccmIds = DB_CCMIdToCampId.getCCMIds(campId);
        for(String ccmId: ccmIds){
            suggestionIds.addAll(DB_CCMIdToSuggestionId.getSuggestionIds(ccmId));
        }
        for(String s: suggestionIds){
            suggestions.add(DB_Suggestion.readSuggestion(s));
        }
        
        return suggestions;
    }

    public void approveSuggestion(String suggestionId, boolean isApproved)
    {
        Suggestion suggestion = DB_Suggestion.readSuggestion(suggestionId);
        suggestion.setIsProcessed(true);
        suggestion.setIsApproved(isApproved);

        //If is approved, give one point to ccm, if not, no points; Also change the camp details as well
        if(isApproved)
        {
            String ccmId = DB_CCMIdToSuggestionId.getCCMId(suggestionId);
            CampCommitteeMember ccm = new CampCommitteeMember(DB_Student.readStudent(ccmId), DB_CCMIdToPoints.getPoints(ccmId));
            ccm.setPoints(ccm.getPoints()+1);
            DB_CCMIdToPoints.updatePoints(ccm.getId(), ccm.getPoints());
        }
        DB_Suggestion.updateSuggestion(suggestion);

    }

    
    public void createCamp(Camp camp) {
        // Implement logic to create a camp
        DB_Camp.createCamp(camp);
    }

    
    public boolean deleteCamp(Camp camp) {
        // Implement logic to delete a camp
        if(camp.getStaffId() == this.getId()) {
            String campId = camp.getId();
            DB_Camp.deleteCamp(campId);
            DB_AttendeeIdToCampId.deleteMappingsByCampId(campId);
            DB_CCMIdToCampId.deleteMappingsByCampId(campId);
            return true;
        }
        return false;
    }

    //Might need to add faculty info tbh
    public ArrayList<Object[]> viewAllCamps() {
        ArrayList<Camp> camps = DB_Camp.getAllCamps();
        ArrayList<Object[]> campStaffFacultiesArrays = new ArrayList<>();
        for (Camp c : camps) {
            String staffId = c.getStaffId();
            Staff staff = DB_Staff.readStaff(staffId);
            Faculty faculty = DB_Faculty.readFaculty(c.getFacultyId());
            campStaffFacultiesArrays.add(new Object[]{c, staff, faculty});  
        }
        return campStaffFacultiesArrays;
    }

   
    public ArrayList<Camp> viewSelfCreatedCamps() {
        // Implement logic to view camps created by the staff
        return DB_Camp.getCampsByStaffId(this.getId());
    }

  
    public boolean editCamp(Camp camp) {
        if (camp.getStaffId() == this.getId()) {
            DB_Camp.updateCamp(camp);
            return true;
        }
        return false;
    }

    
    public void generateReportOfStudentsAttendingSelfCreatedCamp(Staff staff) {

        ArrayList<Camp> camps = staff.viewSelfCreatedCamps();

        ArrayList<String> campIdCreatedByStaff = new ArrayList<>();
   
        for(Camp CID : camps)
        {
            campIdCreatedByStaff.add(CID.getId());
        }
        
        ArrayList<Student> attendees = new ArrayList<>();
        // ArrayList<CampCommitteeMember> ccms = new ArrayList<>();
   
        String csvFilePath = "AttendeeParticipantReportbyStaff.csv";
        ArrayList<String[]> data = new ArrayList<>();
        data.add(new String[]{"StudentID","Email" ,"Name","CampName","Role"});


        for(String cid: campIdCreatedByStaff){
            
            ArrayList<String> attendeeIds = DB_AttendeeIdToCampId.getAttendeeIds(cid);
            Camp c = DB_Camp.readCamp(cid);

            for(String aid: attendeeIds){
                attendees.add(DB_Student.readStudent(aid));
            }
            
            for(Student a: attendees){
                data.add(new String[]{a.getId(),a.getEmail(),a.getName(), c.getName(), "Attendee"});
            }
        } 
        arrayListToCsv(data, csvFilePath);
    }

    public void generateReportOfCCMAttendingSelfCreatedCamp(Staff staff) {
        // Implement logic to generate a report of students attending a self-created camp
           
        ArrayList<Camp> camps = staff.viewSelfCreatedCamps();

        ArrayList<String> campIdCreatedByStaff = new ArrayList<>();
   
        for(Camp CID : camps)
        {
            campIdCreatedByStaff.add(CID.getId());
        }
        
        ArrayList<Student> ccms = new ArrayList<>();
        // ArrayList<CampCommitteeMember> ccms = new ArrayList<>();
   
        String csvFilePath = "CCMParticipantReportbyStaff.csv";
        ArrayList<String[]> data = new ArrayList<>();
        data.add(new String[]{"CCMID","Email" ,"Name","CampName","Role"});


        for(String cid: campIdCreatedByStaff){
            
            ArrayList<String> ccmIds = DB_CCMIdToCampId.getCCMIds(cid);
            Camp c = DB_Camp.readCamp(cid);

            for(String ccmid: ccmIds){
                ccms.add(DB_Student.readStudent(ccmid));
            }
            
            for(Student a: ccms){
                data.add(new String[]{a.getId(),a.getEmail(),a.getName(), c.getName(), "Attendee"});
            }
        } 
        arrayListToCsv(data, csvFilePath);
    }

    public void generateReportWithFilterByCampName(Staff staff,String name) {
        String csvFilePath = "CampReportCreatedbyStaff_FilteredByName.csv";
        ArrayList<String[]> data = new ArrayList<>();
        data.add(new String[]{"CampName", "CampID", "Location", "EndDate", "StartDate", "TotalSlots", "RegClosingDate", "CCMSlots", "Description"});
        // int i =0;
        ArrayList<Camp> camps = staff.viewSelfCreatedCamps();
        ArrayList<String> campIdCreatedByStaff = new ArrayList<>();
   
        for(Camp CID : camps)
        {
            campIdCreatedByStaff.add(CID.getId());
        }
     
        for(String campId: campIdCreatedByStaff){
            Camp camp = DB_Camp.readCamp(campId);
            String nama = camp.getName();
            if (nama.startsWith(name)) {
                data.add(new String[]{camp.getName(), camp.getId(), camp.getLocation(), camp.getEndDate().toString(), camp.getStartDate().toString(), Integer.toString(camp.getTotalSlots()), camp.getRegClosingDate().toString(), Integer.toString(camp.getCampCommitteeSlots()), camp.getDescription()});
            }
      
        }
            arrayListToCsv(data, csvFilePath);
            System.out.println("ReportByName generated");
    }

    public void generateReportFilterByLocation(Staff staff,String location) {
        String csvFilePath = "CampReportCreatedbyStaff_FilteredByLocation.csv";
        ArrayList<String[]> data = new ArrayList<>();
        data.add(new String[]{"CampName", "CampID", "Location", "EndDate", "StartDate", "TotalSlots", "RegClosingDate", "CCMSlots", "Description"});
        
        ArrayList<Camp> camps = staff.viewSelfCreatedCamps();
        ArrayList<String> campIdCreatedByStaff = new ArrayList<>();
   
        for(Camp CID : camps)
        {
            campIdCreatedByStaff.add(CID.getId());
        }

        for(String campId: campIdCreatedByStaff){
            Camp camp = DB_Camp.readCamp(campId);
            String loc = camp.getLocation();
            if (loc.equals(location)) {
                data.add(new String[]{camp.getName(), camp.getId(), camp.getLocation(), camp.getEndDate().toString(), camp.getStartDate().toString(), Integer.toString(camp.getTotalSlots()), camp.getRegClosingDate().toString(), Integer.toString(camp.getCampCommitteeSlots()), camp.getDescription()});
            }
        }
            arrayListToCsv(data, csvFilePath);
            System.out.println("ReportByLocation generated");
    }

    public void generatePerformanceReportOfCampCommitteeMembers(Staff staff) {

        ArrayList<Camp> camps = staff.viewSelfCreatedCamps();

        ArrayList<String> campIdCreatedByStaff = new ArrayList<>();
   
        for(Camp CID : camps)
        {
            campIdCreatedByStaff.add(CID.getId());
        }
        
        ArrayList<Student> ccms = new ArrayList<>();
    

        String csvFilePath = "CampCommitteeMemberPerformance.csv";
        ArrayList<String[]> data = new ArrayList<>();
        data.add(new String[]{"ccmName", "ccmId","CampName","Rating"});

        for(String cid: campIdCreatedByStaff){
            
            ArrayList<String> ccmIds = DB_CCMIdToCampId.getCCMIds(cid);
            Camp c = DB_Camp.readCamp(cid);

            for(String ccmid: ccmIds){
                ccms.add(DB_Student.readStudent(ccmid));
            }
            
            
            for(Student a: ccms){
                data.add(new String[]{a.getName(),a.getId(), c.getName(), String.valueOf(DB_CCMIdToPoints.getPoints(a.getId()))});
            }
        } 
        
        arrayListToCsv(data, csvFilePath);
    }

    private static void arrayListToCsv(ArrayList<String[]> data, String csvFilePath) {
        try (FileWriter csvWriter = new FileWriter(csvFilePath)) {
            for (String[] rowData : data) {
                StringBuilder csvLine = new StringBuilder();
                for (String value : rowData) {
                    csvLine.append(value).append(",");
                }
                // Remove the trailing comma and add a new line
                csvWriter.append(csvLine.substring(0, csvLine.length() - 1)).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changePassword(String newPassword) {
        this.setPassword(newPassword);
        DB_Staff.updateStaff(this);
    }

    
    // public static void main(String[] args) {
    //     Staff s = new Staff("sadfasdfe", "s1", "s1", "s1", "s1");
    //     DB_Staff.createStaff(s);
    //     s.generateReportOfStudentsAttendingSelfCreatedCamp(s);

    // }
}       