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

		//get a point for a suggestion
		String ccmId = DB_CCMIdToSuggestionId.getCCMIds(suggestion.getId()).get(0);
		CampCommitteeMember ccm = DB_CCM.readCampCommitteeMember(ccmId);
		ccm.setPoints(ccm.getPoints() + 1);
		DB_CCM.updateCampCommitteeMember(ccm);
	}


	// This should return a specific ccm's suggestions made
	public ArrayList<Suggestion> viewAllSuggestions() {
		// Implement logic to view all suggestions
		ArrayList<String> suggestionIDs = DB_CCMIdToSuggestionId.getSuggestionIds(this.getId());
		ArrayList<Suggestion> suggestions = new ArrayList<>();
		for(String id: suggestionIDs)
		{
			suggestions.add(DB_Suggestion.readSuggestion(id));
		}
        return suggestions;
	}

	
	public Suggestion viewSuggestionById(String suggestionID) {
		return DB_Suggestion.readSuggestion(suggestionID);
	}

	//Return true if edit Suggestion is successful
	public boolean editSuggestion(Suggestion newSuggestion) {
		// Implement logic to edit a suggestion by ID
		Suggestion oldSuggestion = DB_Suggestion.readSuggestion(newSuggestion.getId());
		if(!oldSuggestion.getIsProcessed()){
			DB_Suggestion.updateSuggestion(newSuggestion);
			return true;
		}
		return false; 
	}

	
	public void deleteSuggestionById(String suggestionID) {
		// Implement logic to delete a suggestion by ID
		DB_CCMIdToSuggestionId.deleteMapping(this.getId(), suggestionID);
		DB_Suggestion.deleteSuggestion(suggestionID);
	}

	// implements CampCommitteeMemberAttendeeInterface
	//This should return a specific camp's attendees' enquiries - camp that this ccm manages
	public ArrayList<Enquiry> viewAllAttendeeEnquiries() {
		// Implement logic to view all attendee enquiries
		String campId = DB_CCMIdToCampId.getCampIds(this.getId()).get(0);
		ArrayList<String> attendeeIds = DB_AttendeeIdToCampId.getAttendeeIds(campId);
		ArrayList<String> enquiryIds = new ArrayList<>();
		for(String aId: attendeeIds)
		{
			enquiryIds.addAll(DB_AttendeeIdToEnquiryId.getEnquiryIds(aId));

		}
		ArrayList<Enquiry> enquiries = new ArrayList<>();
		for(String eId: enquiryIds)
		{
			enquiries.add(DB_Enquiry.readEnquiry(eId));
		}
		
		return enquiries;
	}

	// have issue in testing (NOT DONE)
	public void replyToAttendeeEnquiry(Enquiry e, String text) 
	{
		// Implement logic to reply to an attendee enquiry
		if(!e.getIsProcessed()){
			e.setReplyText(text);
			e.setRepliedByName(this.getName());
			e.setRepliedByStaff(false);
			e.setProcessed(true);
			DB_Enquiry.updateEnquiry(e);
		
			//Give the CCM one point
			this.setPoints(this.getPoints()+1);
			DB_CCM.updateCampCommitteeMember(this);

		}
	}

	// This should return specific camp - camp this ccm manages
	public Camp getCampDetails() 
	{
		//Each CCM Only manages one Camp but the code returns an arraylist - just take the first element
		String campId = DB_CCMIdToCampId.getCampIds(this.getId()).get(0);
		return DB_Camp.readCamp(campId);
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