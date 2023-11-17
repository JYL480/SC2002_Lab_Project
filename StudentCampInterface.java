import java.util.ArrayList;

public interface StudentCampInterface{
    public static ArrayList<Camp> listOfCamps = new ArrayList<Camp>();

    public abstract ArrayList<Camp> viewListOfCamps();
    public abstract int viewCampRemainingAttendeeSlots(String campId);
    public abstract int viewCampRemainingCCMSlots(String campId);
}