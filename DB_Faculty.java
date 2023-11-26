import java.util.ArrayList;

import org.apache.poi.ss.usermodel.*;

public class DB_Faculty extends DB_Base<Faculty> {
    private static final String FILE_PATH = DatabaseFilePaths.FACULTY;
    private static final DB_Faculty instance = new DB_Faculty();

    public DB_Faculty() {
        super(FILE_PATH);
    }

    @Override
    protected Faculty createEntity(Row row) {
        String id = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
        String name = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

        return new Faculty(id, name);
    }

    @Override
    protected void writeEntityToRow(Row row, Faculty faculty) {
        row.createCell(0).setCellValue(faculty.getId());
        row.createCell(1).setCellValue(faculty.getName());
    }

    public static void createFaculty(Faculty faculty) {
        instance.create(faculty);
    }

    public static Faculty readFaculty(String facultyId) {
        return instance.read(facultyId, 0);
    }

    public static void updateFaculty(Faculty faculty) {
        instance.update(faculty.getId(), 0, faculty);
    }

    public static void deleteFaculty(String facultyId) {
        instance.delete(facultyId, 0);
    }


    public static ArrayList<Faculty> getAllFaculty() {
        return instance.getAll();
    }

        // public static void main(String[] args) {
        // // Test createFaculty
        // createFaculty(new Faculty(RandomIdGenerator.generateRandomId(), "SCSE"));
        // createFaculty(new Faculty(RandomIdGenerator.generateRandomId(), "SPMS"));
        // createFaculty(new Faculty(RandomIdGenerator.generateRandomId(), "ADM"));
        // createFaculty(new Faculty(RandomIdGenerator.generateRandomId(), "EEE"));
        // createFaculty(new Faculty(RandomIdGenerator.generateRandomId(), "MSE"));
        // createFaculty(new Faculty(RandomIdGenerator.generateRandomId(), "MAE"));
        // createFaculty(new Faculty(RandomIdGenerator.generateRandomId(), "CEE"));
        // createFaculty(new Faculty(RandomIdGenerator.generateRandomId(), "CCEB"));
        // createFaculty(newFaculty);

        // // Test readFaculty
        // Faculty retrievedFaculty = readFaculty("TEST");
        // System.out.println("Retrieved Faculty: " + retrievedFaculty.getName());

        // // Test updateFaculty
        // Faculty updatedFaculty = new Faculty("TEST", "SPMS");
        // updateFaculty(updatedFaculty);

        // // Test readFaculty after update
        // Faculty updatedRetrievedFaculty = readFaculty("TEST");
        // System.out.println("Updated Retrieved Faculty: " + updatedRetrievedFaculty.getName());

        // // Test getAllFaculty
        // System.out.println("All Faculty:");
        // ArrayList<Faculty> allFaculty = getAllFaculty();
        // for (Faculty faculty : allFaculty) {
        //     System.out.println("ID: " + faculty.getId() + ", Name: " + faculty.getName());
        // }

        // // Test deleteFaculty
        // deleteFaculty("TEST");

        // // Test getAllFaculty after deletion
        // System.out.println("\nAll Faculty after Deletion:");
        // ArrayList<Faculty> facultyAfterDeletion = getAllFaculty();
        // for (Faculty faculty : facultyAfterDeletion) {
        //     System.out.println("ID: " + faculty.getId() + ", Name: " + faculty.getName());
        // }
    // }

}
