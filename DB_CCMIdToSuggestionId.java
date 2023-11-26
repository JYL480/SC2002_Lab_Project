import java.util.ArrayList;

import org.apache.poi.ss.usermodel.*;

public class DB_CCMIdToSuggestionId extends DB_Base<CCMIdToSuggestionIdMapping> {
    private static final String FILE_PATH = DatabaseFilePaths.CCM_ID_TO_SUGGESTION_ID;
    private static final DB_CCMIdToSuggestionId instance = new DB_CCMIdToSuggestionId();

    public DB_CCMIdToSuggestionId() {
        super(FILE_PATH);
    }

    @Override
    protected CCMIdToSuggestionIdMapping createEntity(Row row) {
        String ccmId = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
        String suggestionId = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
        return new CCMIdToSuggestionIdMapping(ccmId, suggestionId);
    }

    @Override
    protected void writeEntityToRow(Row row, CCMIdToSuggestionIdMapping ccmIdToSuggestionIdMapping) {
        row.createCell(0).setCellValue(ccmIdToSuggestionIdMapping.getCcmId());
        row.createCell(1).setCellValue(ccmIdToSuggestionIdMapping.getSuggestionId());
    }

    public static void createMapping(String ccmId, String suggestionId) {
        CCMIdToSuggestionIdMapping ccmIdToSuggestionIdMapping = new CCMIdToSuggestionIdMapping(ccmId, suggestionId);
        instance.create(ccmIdToSuggestionIdMapping);
    }

    public static ArrayList<String> getSuggestionIds(String ccmId) {
        ArrayList<String> suggestionIds = new ArrayList<>();
        ArrayList<CCMIdToSuggestionIdMapping> entities = instance.getAllById(ccmId, 0);
        for(CCMIdToSuggestionIdMapping mapping : entities) {
            suggestionIds.add(mapping.getSuggestionId());
        }
        return suggestionIds;
    }

    public static String getCCMId(String suggestionId) {
        return instance.read(suggestionId, 1).getCcmId();
    }

    // Additional methods (create/update/delete) can be added as needed

    public static void deleteMapping(String ccmId, String suggestionId) {
        instance.deleteIdMapping(ccmId, suggestionId, 0, 1);
    }

    public static boolean isExists(String ccmId, String suggestionId) {
        return (instance.getMapping(ccmId, suggestionId, 0, 1) != null);
    }

    // public static void main(String[] args) {
    //     testDB_CCMIdToSuggestionId();
    // }

    // private static void testDB_CCMIdToSuggestionId() {
    //     // Dummy data
    //     String ccmId1 = "ccm1";
    //     String suggestionId1 = "suggestion1";
    //     String ccmId2 = "ccm2";
    //     String suggestionId2 = "suggestion2";

    //     // Test createMapping
    //     DB_CCMIdToSuggestionId.createMapping(ccmId1, suggestionId1);
    //     DB_CCMIdToSuggestionId.createMapping(ccmId2, suggestionId1);  // Creating a second mapping for the same suggestionId

    //     // Test getSuggestionIds
    //     ArrayList<String> suggestionIds1 = DB_CCMIdToSuggestionId.getSuggestionIds(ccmId1);
    //     System.out.println("Suggestion IDs for CCM ID " + ccmId1 + ": ");
    //     for (String suggestionId : suggestionIds1) {
    //         System.out.println(suggestionId);
    //     }

    //     ArrayList<String> suggestionIds2 = DB_CCMIdToSuggestionId.getSuggestionIds(ccmId2);
    //     System.out.println("Suggestion IDs for CCM ID " + ccmId2 + ": ");
    //     for (String suggestionId : suggestionIds2) {
    //         System.out.println(suggestionId);
    //     }

    //     // Test getCCMIds
    //     ArrayList<String> ccmIds1 = DB_CCMIdToSuggestionId.getCCMIds(suggestionId1);
    //     System.out.println("CCM IDs for Suggestion ID " + suggestionId1 + ": ");
    //     for (String ccmId : ccmIds1) {
    //         System.out.println(ccmId);
    //     }

    //     // Test createMapping for additional mappings
    //     DB_CCMIdToSuggestionId.createMapping(ccmId1, suggestionId2);
    //     DB_CCMIdToSuggestionId.createMapping(ccmId2, suggestionId2);

    //     ArrayList<String> ccmIds2 = DB_CCMIdToSuggestionId.getCCMIds(suggestionId2);
    //     System.out.println("CCM IDs for Suggestion ID " + suggestionId2 + ": ");
    //     for (String ccmId : ccmIds2) {
    //         System.out.println(ccmId);
    //     }

    //     // Test isExists
    //     boolean exists1 = DB_CCMIdToSuggestionId.isExists(ccmId1, suggestionId1);
    //     System.out.println("Mapping exists: " + exists1);

    //     boolean exists2 = DB_CCMIdToSuggestionId.isExists(ccmId2, suggestionId1);
    //     System.out.println("Second mapping exists: " + exists2);

    //     // Test deleteMapping
    //     DB_CCMIdToSuggestionId.deleteMapping(ccmId1, suggestionId1);

    //     // Test isExists after deletion
    //     exists1 = DB_CCMIdToSuggestionId.isExists(ccmId1, suggestionId1);
    //     System.out.println("Mapping exists after deletion: " + exists1);
    // }
}
