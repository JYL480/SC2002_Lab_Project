import java.util.ArrayList;

public class Student extends User implements StudentCampInterface {

	private boolean isCampCommittee;

	public Student(String id, String password, String name, String email, boolean isCampCommittee) {
		super(id, password, name, email);
		this.isCampCommittee = isCampCommittee;
	}


    public boolean getIsCampCommittee() {
        return this.isCampCommittee;
    }
    
    public void setIsCampCommittee(boolean isCampCommittee) {
        this.isCampCommittee = isCampCommittee;
    }

    public ArrayList<Camp> viewListOfCamps() {
        ArrayList<Camp> listOfCamps = new ArrayList<>();
        listOfCamps = DB_Camp.getAllCamps();

        // ArrayList<Camp> visibleAndFacultyCamps = new ArrayList<>();

        
        
        // for(Camp li: listOfCamps) {
        //     if(li.getIsVisible() == false) {
                
        //     }
        // }

        // THIS PART IS NOT DONE for the faculty part
    
        // if(listOfCamps.)
        
        return listOfCamps;
    }
	public int viewCampRemainingAttendeeSlots(String campId){
        Camp camp = DB_Camp.readCamp(campId);
        return camp.getTotalSlots();
	}

    public int viewCampRemainingCCMSlots(String campId){
        Camp camp = DB_Camp.readCamp(campId);
        return camp.getCampCommitteeSlots();
	}




}