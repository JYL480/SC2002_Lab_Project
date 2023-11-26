import java.util.ArrayList;
import java.time.LocalDate;

public class Student extends User implements StudentCampInterface {

	private boolean isCampCommittee;

	public Student(String id, String password, String name, String email, boolean isCampCommittee, String facultyId) {
		super(id, password, name, email, facultyId);
		this.isCampCommittee = isCampCommittee;
	}

    public Student(String id, String password, String name, String email, boolean isCampCommittee, boolean isNewLogin, String facultyId) {
		super(id, password, name, email, isNewLogin ,facultyId);
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
        return DB_Camp.getCampsAvailableForStudent(this.getFacultyId());
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
       
        Camp camp = DB_Camp.readCamp(campId);
        /**
         * 1. check if there are slots
         * 2. check if there are ccm slots
         * 3. check if ccm has already registed for this camp
         * 4. checck if ccm ahs registerd for any camp generally 
         * 5. check if ccm reg date has passed
         */
        if(camp.getCampCommitteeSlots() == 0 || 
        camp.getTotalSlots() == 0 || 
        this.isCampCommittee || 
        camp.getRegClosingDate().isBefore(LocalDate.now())
        )
        {
            return false;
        }
        DB_CCMIdToCampId.createMapping(this.getId(), camp.getId());
        camp.setTotalSlots(camp.getTotalSlots()-1);
        camp.setCampCommitteeSlots(camp.getCampCommitteeSlots()-1);
        DB_Camp.updateCamp(camp);
        this.setIsCampCommittee(true);
        DB_Student.updateStudent(this);
        DB_CCMIdToPoints.createMapping(this.getId(), 0);
        return true; 
    }

    public void changePassword(String newPassword) {
        this.setPassword(newPassword);
        DB_Student.updateStudent(this);
    }
}