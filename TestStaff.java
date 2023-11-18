// import java.util.ArrayList;
// public class TestStaff
// {
//     public static void main(String[] args)
//     {
//         Staff s1 = new Staff(RandomIdGenerator.generateRandomId(), "password", "Derek Zaw", "dzaw@mail.com");
//         DB_Staff.createStaff(s1);
//         Camp one = new Camp(RandomIdGenerator.generateRandomId(), "Fokasdsadsdkok Camp", true, "2023-07-01", "2023-07-15", "2023-06-15", "Camp Location", 100, 10, "Camp Description");
        
//         ArrayList <Faculty> f = DB_Faculty.getAllFaculty();

//         s1.createCamp(one, f);
//         Attendee a1 = new Attendee("KUKU1asdland", "asdsadsa", "asdsadsa", "asdsadsa", false);
//         DB_Attendee.createAttendee(a1);
//         a1.registerForCampAsAttendee(one);
//         Attendee a2 = new Attendee("KUKU12asd3land", "asdasdsadsa", "asdsadsa", "asdsadsa", false);
//         DB_Attendee.createAttendee(a2);
//         a2.registerForCampAsAttendee(one);


//         CampCommitteeMember ccm1 = new CampCommitteeMember("CCasasddM1", "asdsadsa", "asdsadsa", "asdsadsa", false, 0);
//         DB_CCM.createCampCommitteeMember(ccm1);
//         ccm1.registerForCampAsCampCommittee(one);
//         // CampCommitteeMember ccm2 = new CampCommitteeMember("CCasdMasd2", "asdsadasdsasa", "asdsadsa", "asdsadsa", false, 0);
//         // DB_CCM.createCampCommitteeMember(ccm2);
//         // ccm2.registerForCampAsCampCommittee(one);



//         // ccm1.generateReportOfStudentsAttendingCamp(one, 3);
//         Student st1 = new Student(null, null, null, null, false);
        
//         System.out.println(st1.viewCampRemainingAttendeeSlots(one.getId()));
        
//         System.out.println(st1.viewCampRemainingCCMSlots(one.getId()));


//         Enquiry e1 = new Enquiry("E100120","aasdsazzzsd","asdfasfdhghfghfhd",false,"asd","asd",false);
//         a1.submitEnquiry(e1);
//         ccm1.replyToAttendeeEnquiry(e1,"NO haha", a1);

//         // System.out.println(st1.viewListOfCamps().get(0).getId());
        

//         // Performance p1 = new Performance("YUQUANa", 1120, "asdasdsad", "akudsad", false);
//         // DB_Performance.createPerformance(p1);
//         // s1.givePerformanceReview(p1, ccm1);

//         // Performance p2 = new Performance("YUQUANYOUa", 100, "asdASd", "akudsad", false);
//         // DB_Performance.createPerformance(p2);
//         // s1.givePerformanceReview(p2, ccm2);

//         // s1.editPerformanceReview(p2)
//         // s1.generatePerformanceReportOfCampCommitteeMembers(one);
//         // s1.generateReportOfStudentsAttendingSelfCreatedCamp(one, 3);
//         // s1.createCamp(new Camp(RandomIdGenerator.generateRandomId(), "lame Kids Camp", false, "2023-07-01", "2023-07-15", "2023-06-15", "Camp Location", 100, 10, "Camp Description"));
//         // 
//         // DB_Attendee.createAttendee(a1);

//         // Suggestion sug1 = new Suggestion("jyjyj", false, "dsae helo");
//         // ccm1.submitSuggestion(sug1);
//         // Suggestion sug2 = new Suggestion("ewrwer", false, "dsae helo");
//         // ccm1.submitSuggestion(sug2);
//         // System.out.println(s1.viewCommiteeMembersSuggestionsByCampId(one.getId()));
//         // Performance p1 = new Performance("pp1", 4, "null", "null", false);
//         // s1.deletePerformanceReview(p1.getId(), ccm1);
//         // s1.editCamp(one, f);

//         // ArrayList<Object[]> a = s1.viewAllAttendeesEnquiriesByCampId("7928909862d1476a90e0a4e8cd749e2b");
//         // Enquiry e = (Enquiry) a.get(0);
//         // Attendee a = (Attendee) a[0][1] ;
//         // System.out.print(a.getName());

//         // ArrayList<Object[]> attendeeEnquiries = s1.viewAllAttendeesEnquiriesByCampId("7928909862d1476a90e0a4e8cd749e2b");
//         // Enquiry enquiry = (Enquiry) attendeeEnquiries.get(0);
//         // Attendee attendee = (Attendee) attendeeEnquiries.get(0)[1];
//         // System.out.print(attendee.getName());
//         // ArrayList<Object[]> attendeeEnquiries = s1.viewAllAttendeesEnquiriesByCampId("7928909862d1476a90e0a4e8cd749e2b");

// // Assuming the first element in the list is an Enquiry object
//         // ArrayList<Object[]> attendeeEnquiries = s1.viewAllAttendeesEnquiriesByCampId("7928909862d1476a90e0a4e8cd749e2b");
//         // System.out.println(attendeeEnquiries);
//         // // Assuming the first element in the list is an Enquiry object
//         // Enquiry enquiry = (Enquiry) attendeeEnquiries.get(0)[0];

//         // // Assuming the second element in the array is an Attendee object
//         // Attendee attendee = (Attendee) attendeeEnquiries.get(1)[1];

//         // System.out.print(attendee.getName());


//         // ArrayList<Object[]> campArrayList = s1.viewAllCamps();
//         // // System.out.println(campArrayList);

//         // Camp camp = (Camp) campArrayList.get(0)[0];
//         // System.out.println(camp.getLocation());

//         // Staff staff = (Staff) campArrayList.get(0)[1];
        
//         // System.out.println(staff.getName());
//         // // System.out.println("ASdks");

        
//     } 
// }