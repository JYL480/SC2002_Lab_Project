import java.util.ArrayList;

public interface StudentCampInterface{
    public ArrayList<Camp> viewListOfAvailCamps();
    public boolean registerForCampAsAttendee(String campId);
    public boolean registerForCampAsCCM(String campId);
}