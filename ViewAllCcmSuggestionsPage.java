import java.util.ArrayList;

public class ViewAllCcmSuggestionsPage implements Page {

    @Override
    public Page show() {
        System.out.println("View All Suggestions");
        System.out.println("--------------------");

        // Assuming you have a method to get the current CCM (Camp Committee Member)
        CampCommitteeMember currentCCM = getCurrentCCM();

        // Get all suggestions made by the current CCM
        ArrayList<Suggestion> allSuggestions = currentCCM.viewAllSuggestions();

        // Display all suggestions
        displaySuggestions(allSuggestions);

        // Return to the CCM main page
        return new CCMMainPage();
    }

    // Replace this with an actual implementation
    private CampCommitteeMember getCurrentCCM() {
        // Implement logic to get the current Camp Committee Member
        return (CampCommitteeMember) CommandLineApp.LoggedInUser;
    }

    private void displaySuggestions(ArrayList<Suggestion> suggestions) {
        System.out.println("====== All Suggestions ======");
        for (int i = 0; i < suggestions.size(); i++) {
            Suggestion suggestion = suggestions.get(i);
            System.out.println("Suggestion " + (i + 1));
            System.out.println("Comment: " + suggestion.getComment());
            System.out.println("Processed: " + (suggestion.getIsProcessed() ? "Yes" : "No"));
            System.out.println("Approved: " + (suggestion.getIsApproved() ? "Yes" : "No"));
            System.out.println();
        }
        System.out.println("================");
    }
}