import java.util.ArrayList;

public class CampCommiteeMembersPerformanceReport extends Report {

	private ArrayList<Performance> listOfPerformance;

	public CampCommiteeMembersPerformanceReport(String id, Camp camp, String fileType, boolean print, ArrayList<Performance> listOfPerformance) {
        super(id, camp, fileType, print);
        this.listOfPerformance = listOfPerformance;
	}
	
	public ArrayList<Performance> getListOfPerformance() {
		return this.listOfPerformance;
	}

	/**
	 * 
	 * @param listOfPerformance
	 */
	public void setListOfPerformance(ArrayList<Performance> listOfPerformance) {
		this.listOfPerformance = listOfPerformance;
	}

}