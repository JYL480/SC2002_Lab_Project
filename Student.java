import java.util.ArrayList;
import java.time.LocalDate;

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
    private boolean dateRangesOverlap(Camp camp1, Camp camp2) {
        LocalDate camp1StartDate = camp1.getStartDate();
        LocalDate camp1EndDate = camp1.getEndDate();
        LocalDate camp2StartDate = camp2.getStartDate();
        LocalDate camp2EndDate = camp2.getEndDate();

        return !((camp1EndDate.isBefore(camp2StartDate) || camp1EndDate.isEqual(camp2StartDate)) ||
                 (camp1StartDate.isAfter(camp2EndDate) || camp1StartDate.isEqual(camp2EndDate)));
    }

    public boolean registerForCampAsAttendee(String campId)
    {
        //Check if the camp slot is full
        Camp camp = DB_Camp.readCamp(campId);
        /* Check if 
            1. the camp is full
            2. the camp registration is past now
            3. student has registerred for camps within the same date range
            4. if the student has withdran from this camp before
            5. if the student is already registered 
        */
        if(camp.getTotalSlots() == 0 || 
        DB_AttendeeIdToWithdrawnCampId.isExists(this.getId(),campId) || 
        DB_AttendeeIdToCampId.isExists(this.getId(), camp.getId())||
        camp.getRegClosingDate().isBefore(LocalDate.now()))  
        {
            return false;
        }

        ArrayList<String> registeredCampids = DB_AttendeeIdToCampId.getCampIds(this.getId());
        for(String id: registeredCampids)
        {
            Camp registeredCamp = DB_Camp.readCamp(id);
            if(dateRangesOverlap(camp,registeredCamp))  return false; 
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
        /**
         * 1. check if there are slots
         * 2. check if there are ccm slots
         * 3. check if ccm has already registed for this camp
         * 4. checck if ccm ahs registerd for any camp generally 
         * 5. check if ccm reg date has passed
         */
        if(camp.getCampCommitteeSlots() == maxCCMSlot || 
        camp.getTotalSlots() == 0 || 
        DB_CCMIdToCampId.isExists(this.getId(),camp.getId()) || 
        camp.getRegClosingDate().isBefore(LocalDate.now()) ||
        DB_CCMIdToCampId.getCampIds(this.getId()).size() > 0
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