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
                suggestion.getNewDescription(),
                this.getFacultyId(),
                this.getId(),
                suggestion.getNewIsOpenToAll()// add this column to suggestion
            );
            DB_Camp.updateCamp(newCamp);
        }

    }
    // will have to check what performance actually is
   
    public void givePerformanceReview(Performance performance, String ccmId) {
        // Implement logic to give performance review to a camp committee member
        // DB_CCMIdToPerformanceId.createMapping(ccmId, performance.getId());
        // DB_Performance.createPerformance(performance);
    }


    public void editPerformanceReview(Performance performance) {
        // Implement logic to edit performance review of a camp committee member
        // DB_Performance.updatePerformance(performance);
    }

    
    public void deletePerformanceReview(String performanceId, String ccmId) {
        // Implement logic to delete performance review of a camp committee member
        // DB_CCMIdToPerformanceId.deleteMapping(ccmId, performanceId);
        // DB_Performance.deletePerformance(performanceId);
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
            Attendee attendee = new Attendee(DB_Student.readStudent(attendeeId));
            attendees.add(attendee);
        }

        for (String ccmId : ccmIds) {
            CampCommitteeMember ccm = new CampCommitteeMember(DB_Student.readStudent(ccmId), DB_CCMIdToPoints.getPoints(ccmId));
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
        // String campId = camp.getId();


        // // Get the list of attendee IDs for the camp 
        // ArrayList<String> ccmIds = DB_CCMIdToCampId.getCCMIds(campId);

        // String csvFilePath = "CampCommitteeMemberPerformance.csv";
        // ArrayList<String[]> data = new ArrayList<>();
        // data.add(new String[]{"ccmName", "ccmId","Rating","AreasDoneWell","AreasToImprove"});



        // for(String ccmId: ccmIds){
        //     CampCommitteeMember ccm = DB_Student.readStudent(ccmId);
        //     ArrayList<String> performanceId = DB_CCMIdToPerformanceId.getPerformanceIds(ccmId);
        //     for(String pid: performanceId){
        //         Performance performance = DB_Performance.readPerformance(pid);
               
        //         data.add(new String[]{ccm.getName(), ccm.getId(),String.valueOf(performance.getRating()) ,performance.getAreasDoneWell(),performance.getAreasToImprove()});
        //     }
           
        // }
        // arrayListToCsv(data, csvFilePath);
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
