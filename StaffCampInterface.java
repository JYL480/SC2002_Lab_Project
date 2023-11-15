import java.util.ArrayList;

public interface StaffCampInterface {
    public void createCamp(Camp camp, ArrayList<Faculty> allowedFaculty);
    public void deleteCamp(String campId);
    public ArrayList<Object[]> viewAllCamps();
    public ArrayList<Camp> viewSelfCreatedCamps();
    public void editCamp(Camp camp, ArrayList<Faculty> allowedFaculty);
}