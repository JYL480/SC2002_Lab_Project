import java.util.ArrayList;
import java.util.Scanner;

public class ViewCampRegistrationsPage implements Page {
    public Page show() {
        Scanner scanner = new Scanner(System.in);
        Staff staff = (Staff) CommandLineApp.LoggedInUser;
        
        ArrayList<Object[]> camps = staff.viewAllCamps();
        System.out.println("List of all Camps:");
        int index =1;
        for(Object[] camp : camps){
            Camp c = (Camp)camp[0];
            System.out.println(index + ". " + c.getName());
            index++;
        }
        
        int selectedCampIndex;
        do {
            System.out.println("Enter the number of the camp to view details (0 to go back): ");
            try {
                selectedCampIndex = Integer.parseInt(scanner.nextLine());
                if (selectedCampIndex == 0) {
                    return new StaffMainPage();
                } else if (selectedCampIndex >= 1 && selectedCampIndex <= camps.size()) {
                    break;
                } else {
                    System.out.println("Invalid choice! Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Try again.");
            }
        } while (true);

        Camp selectedCamp = (Camp)camps.get(selectedCampIndex - 1)[0];
        
        ArrayList<Student> students = selectedCamp.getAllStudents();

        index=1;
        for (Student student : students) {
            String role;
            if(student.getIsCampCommittee()){
                role = "Camp Committee";
            }
            else{
                role = "Attendee";
            }
            System.out.println(index+". "+student.getName()+", "+ role);
        }
        System.out.println("Full List of all Students in "+selectedCamp.getName());
        
        return new StaffMainPage();


    }
}
