import java.util.ArrayList;


public interface StaffCampInterface {

	/**
	 * 
	 * @param Camp
	 */
	void createCamp(int Camp);

	/**
	 * 
	 * @param Camp
	 */
	void deleteCamp(int Camp);

	ArrayList<Camp> viewAllCamps();

	ArrayList<Camp> viewSelfCreatedCamps();

	/**
	 * 
	 * @param Camp
	 * @param parameter
	 */
	void editCamp(int Camp, int parameter);

}