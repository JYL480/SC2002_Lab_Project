import java.util.ArrayList;
public class TestStaff
{
    public static void main(String[] args)
    {
        Staff s1 = new Staff(RandomIdGenerator.generateRandomId(), "password", "Derek Zaw", "dzaw@mail.com");
        DB_Staff.createStaff(s1);
        Camp one = new Camp(RandomIdGenerator.generateRandomId(), "Fokasdsadsdkok Camp", true, "2023-07-01", "2023-07-15", "2023-06-15", "Camp Location", 100, 10, "Camp Description");
        
        ArrayList <Faculty> f = DB_Faculty.getAllFaculty();
        s1.createCamp(one, f);
        // CampCommitteeMember ccm1 = new CampCommitteeMember("CCM1", "asdsadsa", "asdsadsa", "asdsadsa", false, 0);
        // DB_CCM.createCampCommitteeMember(ccm1);
        // ccm1.registerForCampAsCampCommittee(one);
        // s1.createCamp(new Camp(RandomIdGenerator.generateRandomId(), "lame Kids Camp", false, "2023-07-01", "2023-07-15", "2023-06-15", "Camp Location", 100, 10, "Camp Description"));
        // Attendee a1 = new Attendee("KUKU1", "asdsadsa", "asdsadsa", "asdsadsa", false);
        // DB_Attendee.createAttendee(a1);
        // Enquiry e1 = new Enquiry("E1","aasdsazzzsd","asdfasfdhghfghfhd",true,"asd","asd",false);
        // a1.submitEnquiry(e1);
        // Suggestion sug1 = new Suggestion("jyjyj", false, "dsae helo");
        // ccm1.submitSuggestion(sug1);
        // Suggestion sug2 = new Suggestion("ewrwer", false, "dsae helo");
        // ccm1.submitSuggestion(sug2);
        // System.out.println(s1.viewCommiteeMembersSuggestionsByCampId(one.getId()));
        // Performance p1 = new Performance("pp1", 4, "null", "null", false);
        // s1.deletePerformanceReview(p1.getId(), ccm1);
        // s1.editCamp(one, f);

        // ArrayList<Object[]> a = s1.viewAllAttendeesEnquiriesByCampId("7928909862d1476a90e0a4e8cd749e2b");
        // Enquiry e = (Enquiry) a.get(0);
        // Attendee a = (Attendee) a[0][1] ;
        // System.out.print(a.getName());

        // ArrayList<Object[]> attendeeEnquiries = s1.viewAllAttendeesEnquiriesByCampId("7928909862d1476a90e0a4e8cd749e2b");
        // Enquiry enquiry = (Enquiry) attendeeEnquiries.get(0);
        // Attendee attendee = (Attendee) attendeeEnquiries.get(0)[1];
        // System.out.print(attendee.getName());
        // ArrayList<Object[]> attendeeEnquiries = s1.viewAllAttendeesEnquiriesByCampId("7928909862d1476a90e0a4e8cd749e2b");

// Assuming the first element in the list is an Enquiry object
        // ArrayList<Object[]> attendeeEnquiries = s1.viewAllAttendeesEnquiriesByCampId("7928909862d1476a90e0a4e8cd749e2b");
        // System.out.println(attendeeEnquiries);
        // // Assuming the first element in the list is an Enquiry object
        // Enquiry enquiry = (Enquiry) attendeeEnquiries.get(0)[0];

        // // Assuming the second element in the array is an Attendee object
        // Attendee attendee = (Attendee) attendeeEnquiries.get(1)[1];

        // System.out.print(attendee.getName());


        ArrayList<Object[]> campArrayList = s1.viewAllCamps();
        // System.out.println(campArrayList);

        Camp camp = (Camp) campArrayList.get(0)[0];
        System.out.println(camp.getLocation());

        Staff staff = (Staff) campArrayList.get(0)[1];
        
        System.out.println(staff.getName());
        // System.out.println("ASdks");

        
    } 
}