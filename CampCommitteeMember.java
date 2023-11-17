import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

// import org.jcp.xml.dsig.internal.SignerOutputStream;

public class CampCommitteeMember extends Student implements CampCommitteeMemberStaffInterface, CampCommitteeMemberAttendeeInterface, CampCommitteeMemberCampInterface, CampCommitteeMemberReportInterface {

	private int points;

	public CampCommitteeMember(String id, String password, String name, String email, boolean isCampCommittee, int points) {
        super(id, password, name, email, isCampCommittee);
        this.points = points;
    }

	public void setPoints(int points) {
		this.points = points;
	}

	public int getPoints() {
		return this.points;
	}
//	implements CampCommitteeMemberStaffInterface
	public void submitSuggestion(Suggestion suggestion) {
			// Implement logic to submit a suggestion
			DB_CCMIdToSuggestionId.createMapping(getId(), suggestion.getId());
			DB_Suggestion.createSuggestion(suggestion);
		}


	public ArrayList<Suggestion> viewAllSuggestions() {
		// Implement logic to view all suggestions
		ArrayList<Suggestion> suggestions = DB_Suggestion.getAllSuggestions();
        return suggestions;
	}

	
	public ArrayList<Suggestion> viewSuggestionById(String suggestionID) {
		// Implement logic to view a suggestion by ID
		// Suggestion s =DB_Suggestion.readSuggestion(suggestionID);
		ArrayList<Suggestion> suggestions = new ArrayList<>();
		ArrayList<Suggestion> suggestionss = DB_Suggestion.getAllSuggestions();

		for(Suggestion ss: suggestionss){
			if(suggestionID.equals(ss.getId())){
				suggestions.add(ss);
			}
		}

		
		return suggestions;
	}

	
	public void editSuggestionById(String text, Suggestion suggestion) {
		// Implement logic to edit a suggestion by ID
		if(!suggestion.getIsProcessed()){
			Suggestion newS = new Suggestion(suggestion.getId(),false, text);	
			DB_Suggestion.updateSuggestion(newS);
		}

	}

	
	public void deleteSuggestionById(String suggestionID) {
		// Implement logic to delete a suggestion by ID
		DB_CCMIdToSuggestionId.deleteMapping(getId(), suggestionID);
		DB_Suggestion.deleteSuggestion(suggestionID);
	}

	// implements CampCommitteeMemberAttendeeInterface
	public ArrayList<Enquiry> viewAllAttendeeEnquiries() {
		// Implement logic to view all attendee enquiries
		ArrayList<Enquiry> enquiries = DB_Enquiry.getAllEnquiries();
		return enquiries;
	}

	// have issue in testing
	public void replyToAttendeeEnquiry(Enquiry e, String text, Attendee a) {
		// Implement logic to reply to an attendee enquiry
		if(!e.getIsProcessed()){
			e.setReplyText(text);
			e.setRepliedByName(this.getName());
			e.setRepliedByStaff(true);
			e.setProcessed(true);
			DB_Enquiry.updateEnquiry(e);
		}
		
	}

	//	implements CampCommitteeMemberCampInterface
	public void registerForCampAsCampCommittee(Camp camp) {
		// Implement logic to register for a camp as a camp committee member
		if(camp.getCampCommitteeSlots() == 0){
			System.out.println("The camp is full");
			return;
		}
		if(!DB_CCMIdToCampId.isExists(getId(), camp.getId())){
			DB_CCMIdToCampId.createMapping(getId(), camp.getId());
			System.out.println("hehehehehehhe");
			camp.setCampCommitteeSlots(camp.getCampCommitteeSlots() - 1);
			DB_Camp.updateCamp(camp);
		}		
	}

	
	public Camp getCampDetails(String campId) {
		// Implement logic to get camp details
		Camp camp = DB_Camp.readCamp(campId);
		return camp;
	
	}

	public void generateReportOfStudentsAttendingCamp(Camp camp, int filter) {
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


        String csvFilePath = "CampParticipantReportbyCCM.csv";
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


}