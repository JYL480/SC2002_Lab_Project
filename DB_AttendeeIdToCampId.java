import java.util.ArrayList;
import org.apache.poi.ss.usermodel.*;

public class DB_AttendeeIdToCampId extends DB_Base<AttendeeIdToCampIdMapping>{
    private static final String FILE_PATH = DatabaseFilePaths.ATTENDEE_ID_TO_CAMP_ID;
    private static final DB_AttendeeIdToCampId instance = new DB_AttendeeIdToCampId();

    public DB_AttendeeIdToCampId() {
        super(FILE_PATH);
    }

    @Override
    protected AttendeeIdToCampIdMapping createEntity(Row row) {
        String attendeeId = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
        String campId = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
        boolean isWithdrawn = row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getBooleanCellValue();
        return new AttendeeIdToCampIdMapping(attendeeId, campId, isWithdrawn);
        
    }

    @Override
    protected void writeEntityToRow(Row row, AttendeeIdToCampIdMapping attendeeIdToCampIdMapping) {
        row.createCell(0).setCellValue(attendeeIdToCampIdMapping.getAttendeeId());
        row.createCell(1).setCellValue(attendeeIdToCampIdMapping.getCampId());
        row.createCell(2).setCellValue(attendeeIdToCampIdMapping.isWithdrawn());
    }

    public static void createMapping(String attendeeId, String campId) {
        AttendeeIdToCampIdMapping attendeeIdToCampIdMapping = new AttendeeIdToCampIdMapping(attendeeId, campId);
        instance.create(attendeeIdToCampIdMapping);
    }

    public static ArrayList<String> getCampIds(String attendeeId) {
        ArrayList<AttendeeIdToCampIdMapping> entityList = instance.getAllById(attendeeId, 0);
        ArrayList<String> campIds = new ArrayList<>();
        for (AttendeeIdToCampIdMapping mapping : entityList) {;
            if(!mapping.isWithdrawn()) campIds.add(mapping.getCampId());
        }
        return campIds;
        
    }

    public static ArrayList<String> getAttendeeIds(String campId) {
        ArrayList<AttendeeIdToCampIdMapping> entityList = instance.getAllById(campId, 1);
        ArrayList<String> attendeeIds = new ArrayList<>();
        for (AttendeeIdToCampIdMapping mapping : entityList) {;
            if(!mapping.isWithdrawn()) attendeeIds.add(mapping.getAttendeeId());
        }
        return attendeeIds;
    }

    public static boolean isWithdrawn(String attendeeId, String campId) {
        AttendeeIdToCampIdMapping mapping = instance.getMapping(attendeeId, campId, 0, 1);
        return mapping.isWithdrawn();
    }

    public static void updateWithdrawn(String attendeeId, String campId) {
        AttendeeIdToCampIdMapping attendeeIdToCampIdMapping = instance.getMapping(attendeeId, campId, 0, 1);
        attendeeIdToCampIdMapping.setWithdrawn(true);
        instance.update(attendeeId, 0, attendeeIdToCampIdMapping);
    }

    public static void deleteMapping(String attendeeId, String campId) {
        instance.deleteIdMapping(attendeeId, campId, 0, 1);
    }

    public static boolean isExists(String attendeeId, String campId) {
        return (instance.getMapping(attendeeId, campId, 0, 1) != null);
    }

    public static void deleteMappingsByCampId(String campId) {
       instance.deleteMappingsById(campId, 1);
    }

    // Additional methods (create/update) can be added as needed
    // public static void main(String[] args) {
    //             try (FileInputStream file = new FileInputStream(FILE_PATH);
    //                  Workbook workbook = new XSSFWorkbook(file)) {
            
    //                 Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet
            
    //                 int lastRowNum = sheet.getLastRowNum();
            
    //                 for (int i = 1; i <= lastRowNum; i++) {
    //                     Row row = sheet.getRow(i);
            
    //                     if (row != null) {
    //                         sheet.removeRow(row);
            
    //                         // If the row is not the last row, shift the remaining rows up to fill the gap
    //                         if (i < lastRowNum) {
    //                             sheet.shiftRows(i + 1, lastRowNum, -1);
    //                         }
            
    //                         // Adjust the row index after deletion
    //                         i--;
    //                     }
    //                 }
            
    //                 // Save the changes to the file
    //                 try (FileOutputStream fileOut = new FileOutputStream(FILE_PATH)) {
    //                     workbook.write(fileOut);
    //                 }
            
    //             } catch (IOException e) {
    //                 e.printStackTrace(); // Handle the exception according to your needs
    //             }
    //     }
}
