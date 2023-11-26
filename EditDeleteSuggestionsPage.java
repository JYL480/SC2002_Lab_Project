import java.util.ArrayList;
import java.util.Scanner;

public class EditDeleteSuggestionsPage implements Page {

    @Override
    public Page show() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Edit/Delete Suggestions");
        System.out.println("------------------------");

        // Assuming you have a method to get the current CCM (Camp Committee Member)
        CampCommitteeMember currentCCM = getCurrentCCM();

        // Get all unprocessed suggestions made by the current CCM
        ArrayList<Suggestion> unprocessedSuggestions = currentCCM.viewAllSuggestions();
        unprocessedSuggestions.removeIf(Suggestion::getIsProcessed); // Remove processed suggestions

        // Display unprocessed suggestions
        displaySuggestions(unprocessedSuggestions);

        if (!unprocessedSuggestions.isEmpty()) {
            // Get user input for editing or deleting a suggestion
            System.out.print("Enter the number of the suggestion to edit/delete (0 to go back): ");
            int choice = scanner.nextInt();

            if (choice > 0 && choice <= unprocessedSuggestions.size()) {
                Suggestion selectedSuggestion = unprocessedSuggestions.get(choice - 1);

                // Display the selected suggestion
                System.out.println("Selected Suggestion:");
                System.out.println("Comment: " + selectedSuggestion.getComment());
                System.out.println();

                // Get user input for edit or delete
                System.out.println("1. Edit Suggestion");
                System.out.println("2. Delete Suggestion");
                System.out.println("0. Go Back");

                System.out.print("Enter your choice: ");
                int editDeleteChoice = scanner.nextInt();

                switch (editDeleteChoice) {
                    case 1:
                        // Edit the suggestion
                        editSuggestion(selectedSuggestion);
                        break;
                    case 2:
                        // Delete the suggestion
                        currentCCM.deleteSuggestionById(selectedSuggestion.getId());
                        System.out.println("Suggestion deleted successfully!");
                        break;
                    case 0:
                        // Go back
                        break;
                    default:
                        System.out.println("Invalid choice. Going back to the main page.");
                        break;
                }
            }
        } else {
            System.out.println("No unprocessed suggestions to edit or delete.");
        }

        // Return to the CCM main page
        return new CCMMainPage();
    }

    // Replace this with an actual implementation
    private CampCommitteeMember getCurrentCCM() {
        // Implement logic to get the current Camp Committee Member
        return (CampCommitteeMember) CommandLineApp.LoggedInUser;
    }

    private void displaySuggestions(ArrayList<Suggestion> suggestions) {
        System.out.println("====== Unprocessed Suggestions ======");
        for (int i = 0; i < suggestions.size(); i++) {
            Suggestion suggestion = suggestions.get(i);
            System.out.println("Suggestion " + (i + 1));
            System.out.println("Comment: " + suggestion.getComment());
            System.out.println();
        }
        System.out.println("================");
    }

    private void editSuggestion(Suggestion suggestion) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Editing Suggestion:");
        System.out.println("Enter the new comment:");

        // Get the new comment from the user
        String newComment = scanner.nextLine();

        // Update the suggestion with the new comment
        suggestion.setComment(newComment);

        // Update the suggestion in the database
        CampCommitteeMember currentCCM = getCurrentCCM();
        currentCCM.editSuggestion(suggestion);

        System.out.println("Suggestion edited successfully!");
    }
}

