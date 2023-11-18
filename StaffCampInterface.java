import java.util.ArrayList;

public interface StaffCampInterface {
    public void createCamp(Camp camp, ArrayList<Faculty> allowedFaculty);
    public void deleteCamp(String campId);
    public void editCamp(Camp camp, ArrayList<Faculty> allowedFaculty);
    public ArrayList<Object[]> viewAllCamps();
    public ArrayList<Object[]> viewSelfCreatedCamps();
}