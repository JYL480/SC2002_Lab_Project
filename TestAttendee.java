import java.util.ArrayList;

public class TestAttendee {
    public static void main(String[] args)
    {
        // Staff s1 = new Staff(RandomIdGenerator.generateRandomId(), "password", "Derek Zaw", "dzaw@mail.com");
        // DB_Staff.createStaff(s1);
        // Camp one = new Camp(RandomIdGenerator.generateRandomId(), "Fourth Camp", true, "2023-07-01", "2023-07-15", "2023-06-15", "Camp Location", 100, 10, "Camp Description");
        // ArrayList <Faculty> f = DB_Faculty.getAllFaculty();
        // s1.createCamp(one, f);
        // s1.createCamp(new Camp(RandomIdGenerator.generateRandomId(), "lame Kids Camp", false, "2023-07-01", "2023-07-15", "2023-06-15", "Camp Location", 100, 10, "Camp Description"));
        Attendee a1 = new Attendee("4311ec9493ec4178a3cb8a3b65d84643","paasdssword", "poopp", "dzaw@maiasdsdasadsal.com", false);
        DB_Attendee.createAttendee(a1);
        // Camp one = new Camp(RandomIdGenerator.generateRandomId(), "Fourth Camp", true, "2023-07-01", "2023-07-15", "2023-06-15", "Camp Location", 100, 10, "Camp Description");
        Enquiry e = new Enquiry(a1.getId(),"KUKU bird", "hel111lo", true, "h11ello", "hello", true);
        Enquiry newE = new Enquiry(a1.getId(),"hqwewqello", "hello", true, "hello", "hello", true);
        // a1.submitEnquiry(newE);

        // System.out.println(a1.getId());
        // System.out.println(a1.viewEnquirybyId("4311ec9493ec4178a3cb8a3b65d84643").getSubject());
        a1.viewAllEnquires();

        
        // a1.editEnquiry(e, newE);
        
        // Enquiry newE1 = new Enquiry(a1.getId(),"hqwewqello", "I want to deop ot!", true, "hellosw", "hello", true);

        // a1.editEnquiry(e, newE1);
    
    } 
}
