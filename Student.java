import java.util.*;

public class Student extends User implements StudentCampInterface  {
    
    private boolean isCampCommittee;

    public Student(String userID, String password, String name, String faculty, String email, boolean isCampCommittee) {
        super(userID, password, name, faculty, email);
        this.isCampCommittee = isCampCommittee;
    }

    public boolean getIsCampCommittee() {
        return isCampCommittee;
    }
    public void setIsCampCommittee(boolean isCampCommittee) {
        this.isCampCommittee = isCampCommittee;
    }

    public ArrayList<Camp> viewListOfCamps() {
        return listOfCamps;
    }
    public int viewCampRemainingSlots(Camp camp) {
        return camp.remainingSlots;
    }
    
}
