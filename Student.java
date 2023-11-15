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
        return listOfCamps;
    }
	public int viewCampRemainingSlots(int campId){
        return -1;
		// Camp c = DB_Camp.readCamp(campId);
		// return c.totalSlots;
	}


}