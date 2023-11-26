import org.apache.poi.ss.usermodel.*;

public class DB_CCMIdToPoints extends DB_Base<CCMIdToPointsMapping>{
    private static final String FILE_PATH = DatabaseFilePaths.CCMIdToPoints;
    private static final DB_CCMIdToPoints instance = new DB_CCMIdToPoints();

    public DB_CCMIdToPoints() {
        super(FILE_PATH);
    }

    @Override
    protected CCMIdToPointsMapping createEntity(Row row) {
        String ccmId = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
        int points = (int) row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue();
        return new CCMIdToPointsMapping(ccmId, points);
    }

    @Override
    protected void writeEntityToRow(Row row, CCMIdToPointsMapping ccmIdToPointsMapping) {
        row.createCell(0).setCellValue(ccmIdToPointsMapping.getCcmId());
        row.createCell(1).setCellValue(ccmIdToPointsMapping.getPoints());
    }

    public static void createMapping(String ccmId, int points) {
        CCMIdToPointsMapping mapping = new CCMIdToPointsMapping(ccmId, points);
        instance.create(mapping);
    }

    public static int getPoints(String ccmId) {
        CCMIdToPointsMapping ccmIdToPointsMapping = instance.read(ccmId, 0);
        return ccmIdToPointsMapping.getPoints();
    }

    public static void updatePoints(String ccmId, int updatedPoints) {
        CCMIdToPointsMapping ccmIdToPointsMapping = instance.read(ccmId, 0);
        ccmIdToPointsMapping.setPoints(updatedPoints);
        instance.update(ccmId, 0, ccmIdToPointsMapping);
    }

    public static void deleteMapping(String ccmId) {
        instance.delete(ccmId, 0);
    }

    // public static void main(String[] args) {
    //     // Test createCampCommitteeMember
    //     CampCommitteeMember newCCM = new CampCommitteeMember("CCM1", 0);
    //     CampCommitteeMember newCCM2 = new CampCommitteeMember("CCM2", 0);
    //     DB_CCM.createCampCommitteeMember(newCCM);
    //     DB_CCM.createCampCommitteeMember(newCCM2);

    //     // Test readCampCommitteeMember
    //     CampCommitteeMember retrievedCCM = DB_CCM.readCampCommitteeMember("CCM1");
    //     if (retrievedCCM != null) {
    //         System.out.println("Retrieved CampCommitteeMember: " + retrievedCCM.getId());
    //     } else {
    //         System.out.println("CampCommitteeMember not found.");
    //     }

    //     // Test updateCampCommitteeMember
    //     if (retrievedCCM != null) {
    //         retrievedCCM.setPoints(100);
    //         DB_CCM.updateCampCommitteeMember(retrievedCCM);
    //         System.out.println("CampCommitteeMember updated.");
    //     }

    //
    
}