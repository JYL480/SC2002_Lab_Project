
import java.util.Scanner;

public class SubmitSuggestionsPage implements Page {

    @Override
    public Page show() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Submit Suggestions");
        System.out.println("-------------------");

        // Assuming you have a method to get the current CCM (Camp Committee Member)
        CampCommitteeMember currentCCM = (CampCommitteeMember) CommandLineApp.LoggedInUser;

        // Get suggestion details from the user
        System.out.print("Enter your suggestion: ");
        String suggestionText = scanner.nextLine();

        // Create a new suggestion object
        Suggestion suggestion = new Suggestion();
        suggestion.setComment(suggestionText);
        suggestion.setCampId(currentCCM.getCampDetails().getId());

        // Submit the suggestion
        currentCCM.submitSuggestion(suggestion);

        // Display success message
        System.out.println("Suggestion submitted successfully!");

        // Return to the CCM main page
        return new CCMMainPage();
    }
}

