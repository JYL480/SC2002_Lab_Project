import java.util.ArrayList;

public class Student extends User implements StudentCampInterface {

	private boolean isCampCommittee;

	public Student(String userID, String password, String name, String faculty, String email, boolean isCampCommittee) {
		super(userID, password, name, faculty, email);
		this.isCampCommittee = isCampCommittee;
	}


	public boolean getIsCampCommittee() {
		return this.isCampCommittee;
	}

	/**
	 * 
	 * @param isCampCommittee
	 */
	public void setIsCampCommittee(boolean isCampCommittee) {
		this.isCampCommittee = isCampCommittee;
	}

	public ArrayList<Camp> viewListOfCamps() {
		// TODO - implement Student.viewListOfCamps
		throw new UnsupportedOperationException();

	}

	/**
	 * 
	 * @param Camp
	 */
	public int viewCampRemainingSlots(int Camp) {
		// TODO - implement Student.viewCampRemainingSlots
		throw new UnsupportedOperationException();
	
	}

}