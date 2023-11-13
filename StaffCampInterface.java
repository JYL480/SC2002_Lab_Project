import java.util.ArrayList;

public interface StaffCampInterface {
    public void createCamp();
    public void deleteCamp();
    public ArrayList<Camp> viewAllCamps();
    public ArrayList<Camp> viewSelfCreatedCamps();
    public void editCamps(Camp camp);
}
