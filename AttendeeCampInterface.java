import java.util.ArrayList;

public interface AttendeeCampInterface {
    public void withdrawFromCampAsAttendee(String campId);
    public ArrayList<Camp> viewRegisteredCampsAsAttendee();
}