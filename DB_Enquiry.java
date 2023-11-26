import java.util.ArrayList;

import org.apache.poi.ss.usermodel.*;

public class DB_Enquiry extends DB_Base<Enquiry>{
    private static final String FILE_PATH = DatabaseFilePaths.ENQUIRY;
    private static final DB_Enquiry instance = new DB_Enquiry();

    public DB_Enquiry() {
        super(FILE_PATH);
    }

    @Override
    protected Enquiry createEntity(Row row) {
        String id = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
        String subject = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
        String description = row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
        boolean isProcessed = row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getBooleanCellValue();
        String replyText = row.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
        String repliedByName = row.getCell(5, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
        boolean repliedByStaff = row.getCell(6, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getBooleanCellValue();
        String campId = row.getCell(7, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

        return new Enquiry(id, subject, description, isProcessed, replyText, repliedByName, repliedByStaff, campId);
    }

    @Override
    protected void writeEntityToRow(Row row, Enquiry enquiry) {
        row.createCell(0).setCellValue(enquiry.getId());
        row.createCell(1).setCellValue(enquiry.getSubject());
        row.createCell(2).setCellValue(enquiry.getDescription());
        row.createCell(3).setCellValue(enquiry.getIsProcessed());
        row.createCell(4).setCellValue(enquiry.getReplyText());
        row.createCell(5).setCellValue(enquiry.getRepliedByName());
        row.createCell(6).setCellValue(enquiry.getRepliedByStaff());
        row.createCell(7).setCellValue(enquiry.getCampId());
    }

    public static void createEnquiry(Enquiry enquiry) {
        instance.create(enquiry);
    }

    public static Enquiry readEnquiry(String enquiryId) {
        return instance.read(enquiryId, 0);
    }

    public static void updateEnquiry(Enquiry enquiry) {
        instance.update(enquiry.getId(), 0, enquiry);
    }

    public static void deleteEnquiry(String enquiryId) {
        instance.delete(enquiryId, 0);
    }

    public static ArrayList<Enquiry> getAllEnquiries() {
        return instance.getAll();
    }

    public static ArrayList<Enquiry> getAllByCampId(String campId) {
        return instance.getAllById(campId, 7);
    }

    // public static void main(String[] args) {
    //     // Test DB_Enquiry
    //     testDB_Enquiry();

    //     // Add tests for other classes as needed
    // }

    // private static void testDB_Enquiry() {
    //     // Dummy data
    //     String enquiryId1 = "enquiry1";
    //     String enquiryId2 = "enquiry2";

    //     // Test createEnquiry
    //     Enquiry enquiry1 = new Enquiry(enquiryId1, "Subject 1", "Description 1", false, null, null, false);
    //     Enquiry enquiry2 = new Enquiry(enquiryId2, "Subject 2", "Description 2", true, "Reply 2", "Staff Name", true);

    //     DB_Enquiry.createEnquiry(enquiry1);
    //     DB_Enquiry.createEnquiry(enquiry2);

    //     // Test readEnquiry
    //     Enquiry retrievedEnquiry1 = DB_Enquiry.readEnquiry(enquiryId1);
    //     System.out.println("Retrieved Enquiry 1: " + retrievedEnquiry1.getSubject());

    //     // Test updateEnquiry
    //     Enquiry updatedEnquiry1 = new Enquiry(enquiryId1, "Updated Subject 1", "Updated Description 1", true, "Updated Reply 1", "Updated Staff Name", true);
    //     DB_Enquiry.updateEnquiry(updatedEnquiry1);

    //     // Test getAllEnquiries
    //     System.out.println("All Enquiries:");
    //     ArrayList<Enquiry> allEnquiries = DB_Enquiry.getAllEnquiries();
    //     for (Enquiry enquiry : allEnquiries) {
    //         System.out.println(enquiry.getSubject());
    //     }

    //     // Test deleteEnquiry
    //     DB_Enquiry.deleteEnquiry(enquiryId1);

    //     // Test getAllEnquiries after deletion
    //     System.out.println("\nAll Enquiries after Deletion:");
    //     ArrayList<Enquiry> enquiriesAfterDeletion = DB_Enquiry.getAllEnquiries();
    //     for (Enquiry enquiry : enquiriesAfterDeletion) {
    //         System.out.println(enquiry.getSubject());
    //     }
    // }
    }
