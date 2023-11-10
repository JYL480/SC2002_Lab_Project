
import java.util.ArrayList;
public class CampCommitteeMembersPerformanceReport extends Report {

	private ArrayList<Performance> listOfPerformace;

	public CampCommitteeMembersPerformanceReport(String id, Camp camp, String fileType, boolean print) {
        super(id, camp, fileType, print);
        this.listOfPerformace = new ArrayList<Performance>(); // Initialize the list of performances.
    }

	public ArrayList<Performance> getListOfPerformace() {
		return this.listOfPerformace;
	}

	/**
	 * 
	 * @param listOfPerformace
	 */
	public void setListOfPerformace(ArrayList<Performance> listOfPerformace) {
		this.listOfPerformace = listOfPerformace;
	}

}