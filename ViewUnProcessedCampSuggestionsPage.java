import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class ViewUnProcessedCampSuggestionsPage implements Page {

    @Override
    public Page show() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("View Camp Suggestions");
        System.out.println("----------------------");

        // Assuming you have a method to get the current staff member
        Staff currentStaff = getCurrentStaff();

        // Display self-created camps by the staff
        List<Camp> selfCreatedCamps = currentStaff.viewSelfCreatedCamps();
        displaySelfCreatedCamps(selfCreatedCamps);

        // Ask the staff to choose a camp
        System.out.print("Enter the index of the camp to view suggestions (or 0 to go back): ");
        int chosenCampIndex = scanner.nextInt();

        // Handle the case where the staff wants to go back
        if (chosenCampIndex == 0) {
            return new StaffMainPage();
        }

        // Get the chosen camp
        Camp chosenCamp = selfCreatedCamps.get(chosenCampIndex - 1);

        // Display suggestions for the chosen camp
        displaySuggestionsForCamp(chosenCamp);

        // Choose a suggestion to approve or reject
        chooseSuggestionToApproveOrReject(chosenCamp, currentStaff);

        // Return the staff main page
        return new StaffMainPage();
    }

    // Helper method to display self-created camps
    private void displaySelfCreatedCamps(List<Camp> selfCreatedCamps) {
        System.out.println("Your Self-Created Camps:");
        for (int i = 0; i < selfCreatedCamps.size(); i++) {
            System.out.println((i + 1) + ". " + selfCreatedCamps.get(i).getName());
        }
    }

    // Helper method to display suggestions for a camp
    private void displaySuggestionsForCamp(Camp camp) {
        System.out.println("====== Suggestions for Camp " + camp.getName() + " ======");
        List<Suggestion> suggestions = DB_Suggestion.getAllSuggestionsByCampId(camp.getId());
        int i = 0;
        Iterator<Suggestion> iterator = suggestions.iterator();
        while (iterator.hasNext()) {
            Suggestion suggestion = iterator.next();
            if (suggestion.getIsProcessed()) {
                iterator.remove();  // Use iterator's remove method
            }
        }

        for (i = 0; i < suggestions.size(); i++) {
            Suggestion suggestion = suggestions.get(i);
            System.out.println("Suggestion " + (i + 1));
            System.out.println("Comment: " + suggestion.getComment());
            System.out.println();
        }
        System.out.println("================");
    }

    // Helper method to choose a suggestion to approve or reject
    private void chooseSuggestionToApproveOrReject(Camp camp, Staff currentStaff) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the index of the suggestion to approve or reject (or 0 to go back): ");
        int chosenSuggestionIndex = scanner.nextInt();

        // Handle the case where the staff wants to go back
        if (chosenSuggestionIndex == 0) {
            return;
        }

        // Get the chosen suggestion
        List<Suggestion> suggestions = DB_Suggestion.getAllSuggestionsByCampId(camp.getId());
        Suggestion chosenSuggestion = suggestions.get(chosenSuggestionIndex - 1);

        // Display the details of the chosen suggestion
        displayChosenSuggestionDetails(chosenSuggestion);

        // Confirm the action
        System.out.print("Press 1 to approve, 0 to reject (anything else to go back): ");
        int confirmation = scanner.nextInt();

        // Process the approval or rejection based on user input
        if (confirmation == 1) {
            currentStaff.approveSuggestion(chosenSuggestion.getId(), true);
            System.out.println("Suggestion approved successfully!");
        } else if (confirmation == 0){
            currentStaff.approveSuggestion(chosenSuggestion.getId(), false);
            System.out.println("Suggestion rejected successfully!");
        }
        else return;
    }

    // Helper method to display details of the chosen suggestion
    private void displayChosenSuggestionDetails(Suggestion suggestion) {
        System.out.println("Chosen Suggestion Details:");
        System.out.println("Comment: " + suggestion.getComment());
        // Display other suggestion details...
        System.out.println("================");
    }

    // Replace these with actual implementations
    private Staff getCurrentStaff() {
        // Implement logic to get the current staff member
        return (Staff) CommandLineApp.LoggedInUser;
    }
}

    

