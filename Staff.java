import java.util.ArrayList;
import java.util.HashMap;

public class Staff extends User implements StaffCampInterface, StaffReportInterface, StaffAttendeeEnquiryInterface, StaffCampCommitteeInterface{
    private ArrayList<Camp> campsCreated;
    private HashMap<String, CampCommiteeMembersPerformanceReport> CMPReporIdToReportObjtMap;
    private HashMap<String, CampStudentReport> studetReportIdToReportObjMap;

    public Staff(String userID, String password, String name, String faculty, String email) {
        super(userID, password, name, faculty, email);
        this.campsCreated = new ArrayList<>();
        this.CMPReporIdToReportObjtMap = new HashMap<>();
        this.studetReportIdToReportObjMap = new HashMap<>();
    }

    public ArrayList<Camp> getCampsCreated() {
        return campsCreated;
    }
    public void setCampsCreated(ArrayList<Camp> campsCreated) {
        this.campsCreated = campsCreated;
    }
    public void createCamp(Camp camp) {
        this.campsCreated.add(camp);
    }
    public void deleteCamp(Camp camp) {
        this.campsCreated.remove(camp);
    }
    public ArrayList<Camp> viewAllCamps() {
        return this.campsCreated;
    }
    public ArrayList<Camp> viewSelfCreatedCamps() {
        ArrayList<Camp> selfCreatedCamps = new ArrayList<>();
        for (Camp camp : this.campsCreated) {
            if (camp.getUser().equals(this.getUser())) {
                selfCreatedCamps.add(camp);
            }
        }
        return selfCreatedCamps;
    }
    public void editCamps(Camp camp) {
        Camp newCamp = new Camp();
        camp = newCamp;
    }
}
