import java.util.ArrayList;
import java.time.LocalDate;
import org.apache.poi.ss.usermodel.*;

public class DB_Suggestion extends DB_Base<Suggestion> {
    private static final String FILE_PATH = DatabaseFilePaths.SUGGESTION;
    private static final DB_Suggestion instance = new DB_Suggestion();

    public DB_Suggestion(){
        super(FILE_PATH);
    }

    @Override
    protected Suggestion createEntity(Row row) {
        String id = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
        boolean isProcessed = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getBooleanCellValue();
        boolean isApproved = row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getBooleanCellValue();
        String campId = row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
        String newCampName = row.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
        boolean newCampIsVisible = row.getCell(5, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getBooleanCellValue();
        String newCampStartDate = row.getCell(6, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
        String newCampEndDate = row.getCell(7, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
        String newRegClosingDate = row.getCell(8, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
        String newLocation = row.getCell(9, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
        int newTotalSlots = (int) row.getCell(10, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue();
        int newCampCommitteeSlots = (int) row.getCell(11, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue();
        String newDescription = row.getCell(12, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
        boolean newIsOpenToAll = row.getCell(13, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getBooleanCellValue();

        return new Suggestion(id, isProcessed, isApproved, campId, newCampName, newCampIsVisible,
                LocalDate.parse(newCampStartDate), LocalDate.parse(newCampEndDate), LocalDate.parse(newRegClosingDate), newLocation, newTotalSlots,
                newCampCommitteeSlots, newDescription, newIsOpenToAll);
    }

    protected void writeEntityToRow(Row newRow, Suggestion suggestion) {
        Cell idCell = newRow.createCell(0);
        idCell.setCellValue(suggestion.getId());

        Cell isProcessedCell = newRow.createCell(1);
        isProcessedCell.setCellValue(suggestion.getIsProcessed());

        Cell isApprovedCell = newRow.createCell(2); // Assuming isApproved is the next column
        isApprovedCell.setCellValue(suggestion.getIsApproved());

        Cell campIdCell = newRow.createCell(3);
        campIdCell.setCellValue(suggestion.getCampId());

        Cell newCampNameCell = newRow.createCell(4);
        newCampNameCell.setCellValue(suggestion.getNewCampname());

        Cell newCampIsVisibleCell = newRow.createCell(5);
        newCampIsVisibleCell.setCellValue(suggestion.isNewCampisVisible());

        Cell newCampStartDateCell = newRow.createCell(6);
        newCampStartDateCell.setCellValue(suggestion.getNewCampStartDate().toString());

        Cell newCampEndDateCell = newRow.createCell(7);
        newCampEndDateCell.setCellValue(suggestion.getNewCampEndDate().toString());

        Cell newRegClosingDateCell = newRow.createCell(8);
        newRegClosingDateCell.setCellValue(suggestion.getNewRegClosingDate().toString());

        Cell newLocationCell = newRow.createCell(9);
        newLocationCell.setCellValue(suggestion.getNewLocation());

        Cell newTotalSlotsCell = newRow.createCell(10);
        newTotalSlotsCell.setCellValue(suggestion.getNewTotalSlots());

        Cell newCampCommitteeSlotsCell = newRow.createCell(11);
        newCampCommitteeSlotsCell.setCellValue(suggestion.getNewCampCommitteeSlots());

        Cell newDescriptionCell = newRow.createCell(12);
        newDescriptionCell.setCellValue(suggestion.getNewDescription());

        Cell newIsOpenToAllCell = newRow.createCell(13);
        newIsOpenToAllCell.setCellValue(suggestion.getNewIsOpenToAll());

    }

    public static void createSuggestion(Suggestion suggestion) {
       instance.create(suggestion);
    }

    public static Suggestion readSuggestion(String suggestionId) {
        return instance.read(suggestionId, 0);
    }

    public static void updateSuggestion(Suggestion suggestion) {
        instance.update(suggestion.getId(), 0, suggestion);
    }

    public static void deleteSuggestion(String suggestionId) {
        instance.delete(suggestionId, 0);
    }

    public static ArrayList<Suggestion> getAllSuggestions() {
        return instance.getAll();
    }

    // public static void main(String[] args) {
    //     // Test createSuggestion
    //     Suggestion suggestionOne = new Suggestion(RandomIdGenerator.generateRandomId(), false, true, "campId1",
    //             "New Camp One", true, "2023-08-01", "2023-08-15", "2023-07-15", "New Location One", 50, 5, "New Description One");
    //     Suggestion suggestionTwo = new Suggestion(RandomIdGenerator.generateRandomId(), true, false, "campId2",
    //             "New Camp Two", false, "2023-08-01", "2023-08-15", "2023-07-15", "New Location Two", 75, 8, "New Description Two");
    //     Suggestion suggestionThree = new Suggestion(RandomIdGenerator.generateRandomId(), false, true, "campId3",
    //             "New Camp Three", true, "2023-08-01", "2023-08-15", "2023-07-15", "New Location Three", 60, 6, "New Description Three");

    //     DB_Suggestion.createSuggestion(suggestionOne);
    //     DB_Suggestion.createSuggestion(suggestionTwo);
    //     DB_Suggestion.createSuggestion(suggestionThree);

    //     // Test readSuggestion
    //     Suggestion retrievedSuggestion = DB_Suggestion.readSuggestion(suggestionOne.getId());
    //     System.out.println("Retrieved Suggestion: " + retrievedSuggestion.getNewCampname());

    //     // Test updateSuggestion
    //     Suggestion updatedSuggestion = new Suggestion(suggestionOne.getId(), true, false, "updatedCampId",
    //             "Updated Camp", false, "2023-09-01", "2023-09-15", "2023-08-15", "Updated Location", 80, 7, "udpated descrition");

    //     DB_Suggestion.updateSuggestion(updatedSuggestion);

    //     // Test getAllSuggestions
    //     System.out.println("All Suggestions:");
    //     ArrayList<Suggestion> allSuggestions = DB_Suggestion.getAllSuggestions();
    //     for (Suggestion suggestion : allSuggestions) {
    //         System.out.println(suggestion.getNewCampname());
    //     }

    //     // Test deleteSuggestion
    //     DB_Suggestion.deleteSuggestion(suggestionTwo.getId());

    //     // Test getAllSuggestions after deletion
    //     System.out.println("\nAll Suggestions after Deletion:");
    //     ArrayList<Suggestion> suggestionsAfterDeletion = DB_Suggestion.getAllSuggestions();
    //     for (Suggestion suggestion : suggestionsAfterDeletion) {
    //         System.out.println(suggestion.getNewCampname());
    //     }
    // }
}
