import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Staff extends User implements StaffAttendeeEnquiryInterface, StaffCampCommitteeInterface, StaffCampInterface, StaffReportInterface {

    public Staff(String id, String password, String name, String email) {
        super(id, password, name, email);
    }

    public ArrayList<Object[]> viewAllAttendeesEnquiriesByCampId(String campId) {
        ArrayList<String> attendeeIds = DB_AttendeeIdToCampId.getAttendeeIds(campId);
        ArrayList<Object[]> enquiryAttendeeArrays = new ArrayList<>();
        for (String aId : attendeeIds) {
            ArrayList<String> tmp = DB_AttendeeIdToEnquiryId.getEnquiryIds(aId);
            Attendee attendee = DB_Attendee.readAttendee(aId);
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
            String ccmId = DB_CCMIdToSuggestionId.getCCMIds(suggestionId).get(0);
            CampCommitteeMember ccm = DB_CCM.readCampCommitteeMember(ccmId);
            ccm.setPoints(ccm.getPoints()+1);
            DB_CCM.updateCampCommitteeMember(ccm);
            Camp newCamp = new Camp(
                suggestion.getCampId(),
                suggestion.getNewCampname(),
                suggestion.isNewCampisVisible(),
                suggestion.getNewCampStartDate(),
                suggestion.getNewCampEndDate(),
                suggestion.getNewRegClosingDate(),
                suggestion.getNewLocation(),
                suggestion.getNewTotalSlots(),
                suggestion.getNewCampCommitteeSlots(),
                suggestion.getNewDescription()
            );
            DB_Camp.updateCamp(newCamp);
        }

    }
   
    public void givePerformanceReview(Performance performance, String ccmId) {
        // Implement logic to give performance review to a camp committee member
        DB_CCMIdToPerformanceId.createMapping(ccmId, performance.getId());
        DB_Performance.createPerformance(performance);
    }


    public void editPerformanceReview(Performance performance) {
        // Implement logic to edit performance review of a camp committee member
        DB_Performance.updatePerformance(performance);
    }

    
    public void deletePerformanceReview(String performanceId, String ccmId) {
        // Implement logic to delete performance review of a camp committee member
        DB_CCMIdToPerformanceId.deleteMapping(ccmId, performanceId);
        DB_Performance.deletePerformance(performanceId);
    }

    
    public void createCamp(Camp camp, ArrayList<Faculty> allowedFaculty) {
        // Implement logic to create a camp
        DB_Camp.createCamp(camp);
        DB_StaffIdToCampId.createMapping(this.getId(), camp.getId());
        for(Faculty f: allowedFaculty)
        {
            DB_CampIdToFacultyId.createMapping(camp.getId(), f.getId());
        }
        
    }

    
    public void deleteCamp(String campId) {
        // Implement logic to delete a camp
        DB_Camp.deleteCamp(campId);
        DB_StaffIdToCampId.deleteMappingsByCampId(campId);
        DB_CampIdToFacultyId.deleteMappingsByCampId(campId);
        DB_AttendeeIdToCampId.deleteMappingsByCampId(campId);
        DB_CCMIdToCampId.deleteMappingsByCampId(campId);
        DB_CampIdToFacultyId.deleteMappingsByCampId(campId);
    }

    //Might need to add faculty info tbh
    public ArrayList<Object[]> viewAllCamps() {
        ArrayList<Camp> camps = DB_Camp.getAllCamps();
        ArrayList<Object[]> campStaffFacultiesArrays = new ArrayList<>();
        for (Camp c : camps) {
            String staffId = DB_StaffIdToCampId.getStaffId(c.getId());
            Staff staff = DB_Staff.readStaff(staffId);
            ArrayList <String> facultyIds = DB_CampIdToFacultyId.getFacultyIds(c.getId());
            ArrayList <Faculty> faculties = new ArrayList<>();
            for(String id: facultyIds)
            {
                faculties.add(DB_Faculty.readFaculty(id));
            }

            campStaffFacultiesArrays.add(new Object[]{c, staff, faculties});  
        }
        return campStaffFacultiesArrays;
    }

   
    public ArrayList<Object[]> viewSelfCreatedCamps() {
        // Implement logic to view camps created by the staff
        ArrayList<String> campIds = DB_StaffIdToCampId.getCampIds(this.getId());
        ArrayList<Object[]> campFacultiesArrays = new ArrayList<>(); 
        for(String id: campIds)
        {
            Camp camp = DB_Camp.readCamp(id);
            ArrayList <String> facultyIds = DB_CampIdToFacultyId.getFacultyIds(id);
            ArrayList <Faculty> faculties = new ArrayList<>();
            for(String fId: facultyIds)
            {
                faculties.add(DB_Faculty.readFaculty(fId));
            }
            campFacultiesArrays.add(new Object[]{camp, faculties});
        }
        return  campFacultiesArrays;
    }

  
    public void editCamp(Camp camp, ArrayList<Faculty> allowedFaculty) {
        // Implement logic to edit a camp
        DB_Camp.updateCamp(camp);

        //Need to check if allowed faculties need editing -- just delete the mappings n write again
        DB_CampIdToFacultyId.deleteMappingsByCampId(camp.getId());
        for(Faculty f: allowedFaculty)
        {
            DB_CampIdToFacultyId.createMapping(camp.getId(), f.getId());
        }
    }

    
    public void generateReportOfStudentsAttendingSelfCreatedCamp(Camp camp, int filter) {
        // Implement logic to generate a report of students attending a self-created camp
        String campId = camp.getId();

        ArrayList<Attendee> attendees = new ArrayList<>();
        ArrayList<CampCommitteeMember> ccms = new ArrayList<>();

        // Get the list of attendee IDs for the camp
        ArrayList<String> attendeeIds = DB_AttendeeIdToCampId.getAttendeeIds(campId);
        ArrayList<String> ccmIds = DB_CCMIdToCampId.getCCMIds(campId);

        // Loop through the attendee IDs and retrieve the attendee objects
        for (String attendeeId : attendeeIds) {
            Attendee attendee = DB_Attendee.readAttendee(attendeeId);
            attendees.add(attendee);
        }

        for (String ccmId : ccmIds) {
            CampCommitteeMember ccm = DB_CCM.readCampCommitteeMember(ccmId);
            ccms.add(ccm);
        }
        // Filter options
        // 1 = attendee
        // 2 = ccm
        // 3 = both


        String csvFilePath = "CampParticipantReportbyStaff.csv";
        ArrayList<String[]> data = new ArrayList<>();
        data.add(new String[]{"StudentID","Email" ,"Name","CampName","Role"});

        switch (filter) {
            case 1:
                for(Attendee a: attendees){
                    data.add(new String[]{a.getId(),a.getEmail(),a.getName(), camp.getName(), "Attendee"});
                }
                break;
                
            case 2:
                for(CampCommitteeMember ccmss: ccms){
                    data.add(new String[]{ccmss.getId(),ccmss.getEmail(),ccmss.getName(), camp.getName(), "CampCommitteeMember"});
                }
                break;


            case 3:
                for(Attendee a: attendees){
                    data.add(new String[]{a.getId(),a.getEmail(),a.getName(), camp.getName(), "Attendee"});
                }
                for(CampCommitteeMember ccmssss: ccms){
                    data.add(new String[]{ccmssss.getId(),ccmssss.getEmail(),ccmssss.getName(), camp.getName(), "CampCommitteeMember"});
                }
                break;
                

            default:
                System.out.println("Wrong input");
        }
        // switch (filter) {
        //     case 1:
        //         Sorting.insertionSort(attendees);
        //         for (Attendee a : attendees) {
        //             data.add(new String[]{a.getId(), a.getEmail(), a.getName(), camp.getName(), "Attendee"});
        //         }
        //         break;

        //     case 2:
        //         Sorting.insertionSort(ccms);
        //         for (CampCommitteeMember ccm : ccms) {
        //             data.add(new String[]{ccm.getId(), ccm.getEmail(), ccm.getName(), camp.getName(), "CampCommitteeMember"});
        //         }
        //         break;

        //     case 3:
        //         Sorting.insertionSort(attendees);
        //         Sorting.insertionSort(ccms);

        //         for (Attendee a : attendees) {
        //             data.add(new String[]{a.getId(), a.getEmail(), a.getName(), camp.getName(), "Attendee"});
        //         }

        //         for (CampCommitteeMember ccm : ccms) {
        //             data.add(new String[]{ccm.getId(), ccm.getEmail(), ccm.getName(), camp.getName(), "CampCommitteeMember"});
        //         }
        //         break;

        //     default:
        //         System.out.println("Wrong input");
        // }
        arrayListToCsv(data, csvFilePath);

    }


    public void generatePerformanceReportOfCampCommitteeMembers(Camp camp) {
        String campId = camp.getId();


        // Get the list of attendee IDs for the camp 
        ArrayList<String> ccmIds = DB_CCMIdToCampId.getCCMIds(campId);

        String csvFilePath = "CampCommitteeMemberPerformance.csv";
        ArrayList<String[]> data = new ArrayList<>();
        data.add(new String[]{"ccmName", "ccmId","Rating","AreasDoneWell","AreasToImprove"});



        for(String ccmId: ccmIds){
            CampCommitteeMember ccm = DB_CCM.readCampCommitteeMember(ccmId);
            ArrayList<String> performanceId = DB_CCMIdToPerformanceId.getPerformanceIds(ccmId);
            for(String pid: performanceId){
                Performance performance = DB_Performance.readPerformance(pid);
               
                data.add(new String[]{ccm.getName(), ccm.getId(),String.valueOf(performance.getRating()) ,performance.getAreasDoneWell(),performance.getAreasToImprove()});
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

}
