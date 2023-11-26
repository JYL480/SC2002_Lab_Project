import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

// import org.jcp.xml.dsig.internal.SignerOutputStream;

public class CampCommitteeMember extends Student implements CampCommitteeMemberStaffInterface, CampCommitteeMemberAttendeeInterface, CampCommitteeMemberCampInterface, CampCommitteeMemberReportInterface {

	private int points;

	public CampCommitteeMember(String id, String password, String name, String email, boolean isCampCommittee, boolean isNewLogin, String facultyId, int points) {
        super(id, password, name, email, isCampCommittee, isNewLogin, facultyId);
        this.points = points;
    }

	public CampCommitteeMember(String id, String password, String name, String email, boolean isCampCommittee, boolean isNewLogin, String facultyId) {
        super(id, password, name, email, isCampCommittee, isNewLogin, facultyId);
        this.points = 0;
    }

	public CampCommitteeMember(String id, String password, String name, String email, boolean isCampCommittee, String facultyId, int points) {
        super(id, password, name, email, isCampCommittee, facultyId);
        this.points = points;
    }

	public CampCommitteeMember(String id, String password, String name, String email, boolean isCampCommittee, String facultyId) {
        super(id, password, name, email, isCampCommittee, facultyId);
        this.points = 0;
    }

	public CampCommitteeMember(Student student, int points) {
		super(student.getId(), student.getPassword(), student.getName(), student.getEmail(), student.getIsCampCommittee(), student.isNewLogin(), student.getFacultyId());
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
		this.setPoints(this.getPoints() + 1);
		DB_CCMIdToPoints.updatePoints(this.getId(), this.points);
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
		String campId = DB_CCMIdToCampId.getCampId(this.getId());
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
			DB_CCMIdToPoints.updatePoints(this.getId(), this.points);

		}
	}

	// This should return specific camp - camp this ccm manages
	public Camp getCampDetails() 
	{
		//Each CCM Only manages one Camp but the code returns an arraylist - just take the first element
		String campId = DB_CCMIdToCampId.getCampId(this.getId());
		return DB_Camp.readCamp(campId);
	}

	public void generateReportOfStudentsAttendingWithCCMCamp(Camp camp) {
        // Implement logic to generate a report of students attending a self-created camp
        String campId = camp.getId();

        ArrayList<Student> students = new ArrayList<>();
        // ArrayList<CampCommitteeMember> ccms = new ArrayList<>();

        // Get the list of attendee IDs for the camp
        ArrayList<String> studentIds = DB_AttendeeIdToCampId.getAttendeeIds(campId);
        // ArrayList<String> ccmIds = DB_CCMIdToCampId.getCCMIds(campId);

        // Loop through the attendee IDs and retrieve the attendee objects
        for (String attendeeId : studentIds) {
            Student student = DB_Student.readStudent(attendeeId);
            students.add(student);
        }
        String csvFilePath = "CampParticipantReportbyCCM.csv";
        ArrayList<String[]> data = new ArrayList<>();
        data.add(new String[]{"StudentID","Email" ,"Name","CampName","Role"});
		for(Student a: students){
			data.add(new String[]{a.getId(),a.getEmail(),a.getName(), camp.getName(), "Attendee"});
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