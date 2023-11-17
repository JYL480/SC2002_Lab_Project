// import java.util.ArrayList;

public class TestAttendee {
    public static void main(String[] args)
    {
        // Staff s1 = new Staff(RandomIdGenerator.generateRandomId(), "password", "Derek Zaw", "dzaw@mail.com");
        // DB_Staff.createStaff(s1);
        // Camp one = new Camp(RandomIdGenerator.generateRandomId(), "Fourth Camp", true, "2023-07-01", "2023-07-15", "2023-06-15", "Camp Location", 100, 10, "Camp Description");
        // ArrayList <Faculty> f = DB_Faculty.getAllFaculty();
        // s1.createCamp(one, f);
        // s1.createCamp(new Camp(RandomIdGenerator.generateRandomId(), "lame Kids Camp", false, "2023-07-01", "2023-07-15", "2023-06-15", "Camp Location", 100, 10, "Camp Description"));
        Attendee a1 = new Attendee("bb18c70a31fb40e196dd79d9df8e5307","pass", "poopp", "dzaw@maiasdsdasadsal.com", true);
        // DB_Attendee.createAttendee(a1);
        // Camp one = new Camp(RandomIdGenerator.generateRandomId(), "Fourth Camp", true, "2023-07-01", "2023-07-15", "2023-06-15", "Camp Location", 100, 10, "Camp Description");
        Enquiry e = new Enquiry(a1.getId(),"Bird bird", "Is A Bird", true, "h11ello", "hello", true);
        // a1.submitEnquiry(e);

        // System.out.println(a1.getId());
        // System.out.println(a1.viewEnquirybyId("4311ec9493ec4178a3cb8a3b65d84643").getSubject());
        // a1.viewAllEnquires();

        
        // a1.editEnquiry(e, newE);
        // Enquiry newE1 = new Enquiry(a1.getId(),"hqwewqello", "I want to deop ot!", true, "hellosw", "hello", true);
        a1.deleteEnquiry(e);
        // a1.editEnquiry(newE, e);
        a1.viewEnquirybyId(a1.getId());

    
    } 
}
