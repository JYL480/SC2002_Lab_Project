import java.util.ArrayList;

public interface StaffCampInterface {
    public void createCamp(Camp camp);
    public boolean deleteCamp(Camp camp);
    public boolean editCamp(Camp camp);
    public ArrayList<Object[]> viewAllCamps();
    public ArrayList<Camp> viewSelfCreatedCamps();
}