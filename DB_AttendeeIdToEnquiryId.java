import java.util.ArrayList;
import org.apache.poi.ss.usermodel.*;

public class DB_AttendeeIdToEnquiryId extends DB_Base<AttendeeIdToEnquiryIdMapping>{
    private static final String FILE_PATH = DatabaseFilePaths.ATTENDEE_ID_TO_ENQUIRY_ID;
    private static final DB_AttendeeIdToEnquiryId instance = new DB_AttendeeIdToEnquiryId();

    public DB_AttendeeIdToEnquiryId() {
        super(FILE_PATH);
    }

    @Override
    protected AttendeeIdToEnquiryIdMapping createEntity(Row row) {
        String attendeeId = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
        String enquiryId = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
        return new AttendeeIdToEnquiryIdMapping(attendeeId, enquiryId);
        
    }

    @Override
    protected void writeEntityToRow(Row row, AttendeeIdToEnquiryIdMapping attendeeIdToEnquiryIdMapping) {
        row.createCell(0).setCellValue(attendeeIdToEnquiryIdMapping.getAttendeeId());
        row.createCell(1).setCellValue(attendeeIdToEnquiryIdMapping.getEnquiryId());

    }


    public static void createMapping(String attendeeId, String enquiryId) {
        AttendeeIdToEnquiryIdMapping attendeeIdToEnquiryIdMapping = new AttendeeIdToEnquiryIdMapping(attendeeId, enquiryId);
        instance.create(attendeeIdToEnquiryIdMapping);
    }

    public static ArrayList<String> getEnquiryIds(String attendeeId) {
        ArrayList<AttendeeIdToEnquiryIdMapping> entityList = instance.getAllById(attendeeId, 0);
        ArrayList<String> enquiryIds = new ArrayList<>();
        for (AttendeeIdToEnquiryIdMapping mapping : entityList) {;
            enquiryIds.add(mapping.getEnquiryId());
        }
        return enquiryIds;
    }

    public static ArrayList<String> getAttendeeIds(String enquiryId) {
        ArrayList<String> attendeeIds = new ArrayList<>();
        ArrayList<AttendeeIdToEnquiryIdMapping> entityList = instance.getAllById(enquiryId, 1);
        for (AttendeeIdToEnquiryIdMapping mapping : entityList) {;
            attendeeIds.add(mapping.getAttendeeId());
        }
        return attendeeIds;

    }

    // Additional methods (create/update/delete) can be added as needed

    public static void deleteMapping(String attendeeId, String enquiryId) {
        instance.deleteIdMapping(attendeeId, enquiryId, 0 , 1);
    }

    public static boolean isExists(String attendeeId, String enquiryId) {
        return (instance.getMapping(attendeeId, enquiryId, 0, 1) != null);
    }

    // public static void main(String[] args) {
    //     testDB_AttendeeIdToEnquiryId();
    // }

    // private static void testDB_AttendeeIdToEnquiryId() {
    //     // Dummy data
    //     String attendeeId1 = "attendee1";
    //     String enquiryId1 = "enquiry1";
    //     String attendeeId2 = "attendee2";
    //     String enquiryId2 = "enquiry2";

    //     // Test createMapping
    //     DB_AttendeeIdToEnquiryId.createMapping(attendeeId1, enquiryId1);
    //     DB_AttendeeIdToEnquiryId.createMapping(attendeeId2, enquiryId1);  // Creating a second mapping for the same enquiryId

    //     // Test getEnquiryIds
    //     ArrayList<String> enquiryIds1 = DB_AttendeeIdToEnquiryId.getEnquiryIds(attendeeId1);
    //     System.out.println("Enquiry IDs for Attendee ID " + attendeeId1 + ": ");
    //     for (String enquiryId : enquiryIds1) {
    //         System.out.println(enquiryId);
    //     }

    //     ArrayList<String> enquiryIds2 = DB_AttendeeIdToEnquiryId.getEnquiryIds(attendeeId2);
    //     System.out.println("Enquiry IDs for Attendee ID " + attendeeId2 + ": ");
    //     for (String enquiryId : enquiryIds2) {
    //         System.out.println(enquiryId);
    //     }

    //     // Test getAttendeeIds
    //     ArrayList<String> attendeeIds1 = DB_AttendeeIdToEnquiryId.getAttendeeIds(enquiryId1);
    //     System.out.println("Attendee IDs for Enquiry ID " + enquiryId1 + ": ");
    //     for (String attendeeId : attendeeIds1) {
    //         System.out.println(attendeeId);
    //     }

    //     // Test createMapping for additional mappings
    //     DB_AttendeeIdToEnquiryId.createMapping(attendeeId1, enquiryId2);
    //     DB_AttendeeIdToEnquiryId.createMapping(attendeeId2, enquiryId2);

    //     ArrayList<String> attendeeIds2 = DB_AttendeeIdToEnquiryId.getAttendeeIds(enquiryId2);
    //     System.out.println("Attendee IDs for Enquiry ID " + enquiryId2 + ": ");
    //     for (String attendeeId : attendeeIds2) {
    //         System.out.println(attendeeId);
    //     }

    //     // Test isExists
    //     boolean exists1 = DB_AttendeeIdToEnquiryId.isExists(attendeeId1, enquiryId1);
    //     System.out.println("Mapping exists: " + exists1);

    //     boolean exists2 = DB_AttendeeIdToEnquiryId.isExists(attendeeId2, enquiryId1);
    //     System.out.println("Second mapping exists: " + exists2);

    //     // Test deleteMapping
    //     DB_AttendeeIdToEnquiryId.deleteMapping(attendeeId1, enquiryId1);

    //     // Test isExists after deletion
    //     exists1 = DB_AttendeeIdToEnquiryId.isExists(attendeeId1, enquiryId1);
    //     System.out.println("Mapping exists after deletion: " + exists1);
    // }

//     public static void main(String[] args) {
//         try (FileInputStream file = new FileInputStream(FILE_PATH);
//              Workbook workbook = new XSSFWorkbook(file)) {
    
//             Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet
    
//             int lastRowNum = sheet.getLastRowNum();
    
//             for (int i = 1; i <= lastRowNum; i++) {
//                 Row row = sheet.getRow(i);
    
//                 if (row != null) {
//                     sheet.removeRow(row);
    
//                     // If the row is not the last row, shift the remaining rows up to fill the gap
//                     if (i < lastRowNum) {
//                         sheet.shiftRows(i + 1, lastRowNum, -1);
//                     }
    
//                     // Adjust the row index after deletion
//                     i--;
//                 }
//             }
    
//             // Save the changes to the file
//             try (FileOutputStream fileOut = new FileOutputStream(FILE_PATH)) {
//                 workbook.write(fileOut);
//             }
    
//         } catch (IOException e) {
//             e.printStackTrace(); // Handle the exception according to your needs
//         }
// }
}
