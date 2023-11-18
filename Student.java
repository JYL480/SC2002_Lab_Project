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

    public ArrayList<Camp> viewListOfAvailCamps()
    {
        //Get camps that are avaible to studen'ts faculty
        ArrayList<String> attendeeFacultyIds = DB_AttendeeIdToFacultyId.getFacultyIds(this.getId());
        ArrayList<String> campIds = new ArrayList<> ();
        ArrayList<Camp> camps = new ArrayList<> ();
        for(String id: attendeeFacultyIds)
        {      
            campIds.addAll(DB_CampIdToFacultyId.getCampIds(id));     
        }

        for(String id: campIds)
        {
            Camp tmpCamp = DB_Camp.readCamp(id);
             // See if the visibilty is toggled on
            if(tmpCamp.getIsVisible())
            {
                camps.add(tmpCamp);
            }
        }

        return camps; 
    }

        /*
    If the registration is succecsful, u will get back true, false otherwise. 
    */
    public boolean registerForCampAsAttendee(String campId)
    {
        //Check if the camp slot is full
        Camp camp = DB_Camp.readCamp(campId);
        if(camp.getTotalSlots() == 0 || 
        DB_AttendeeIdToWithdrawnCampId.isExists(this.getId(),campId) || 
        DB_AttendeeIdToCampId.isExists(this.getId(), camp.getId()))  
        {
            return false;
        }

        DB_AttendeeIdToCampId.createMapping(this.getId(), camp.getId());
        camp.setTotalSlots(camp.getTotalSlots() - 1);
        DB_Camp.updateCamp(camp);

        return true;
    }

    public boolean registerForCampAsCCM(String campId)
    {
        int maxCCMSlot = 10;
        Camp camp = DB_Camp.readCamp(campId);
        if(camp.getCampCommitteeSlots() == maxCCMSlot || 
        camp.getTotalSlots() == 0 || 
        DB_CCMIdToCampId.isExists(this.getId(),camp.getId())
        )
        {
            return false;
        }

        DB_CCMIdToCampId.createMapping(this.getId(), camp.getId());
        camp.setTotalSlots(camp.getTotalSlots()-1);
        camp.setCampCommitteeSlots(camp.getCampCommitteeSlots()-1);
        DB_Camp.updateCamp(camp);
        return true; 
    }




}