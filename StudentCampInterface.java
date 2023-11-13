import java.util.ArrayList;

public interface StudentCampInterface {

	ArrayList<Camp> viewListOfCamps();

	/**
	 * 
	 * @param Camp
	 */
	int viewCampRemainingSlots(int Camp);

}