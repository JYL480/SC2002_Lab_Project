import java.util.ArrayList;
public class TestStaff
{
    public static void main(String[] args)
    {
        Staff s1 = new Staff(RandomIdGenerator.generateRandomId(), "password", "Derek Zaw", "dzaw@mail.com");
        DB_Staff.createStaff(s1);
        Camp one = new Camp(RandomIdGenerator.generateRandomId(), "Fourth Camp", true, "2023-07-01", "2023-07-15", "2023-06-15", "Camp Location", 100, 10, "Camp Description");
        ArrayList <Faculty> f = DB_Faculty.getAllFaculty();
        s1.createCamp(one, f);
        // s1.createCamp(new Camp(RandomIdGenerator.generateRandomId(), "lame Kids Camp", false, "2023-07-01", "2023-07-15", "2023-06-15", "Camp Location", 100, 10, "Camp Description"));
    } 
}