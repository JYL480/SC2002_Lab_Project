import java.util.UUID;

public class RandomIdGenerator {

    public static String generateRandomId() {
        // Generate a random UUID
        UUID uuid = UUID.randomUUID();

        // Convert UUID to a string and remove hyphens
        String randomId = uuid.toString().replaceAll("-", "");

        return randomId;
    }
    // public static void main(String[] args) {
    //     // Example usage
    //     String randomId = generateRandomId();
    //     System.out.println("Random ID: " + randomId);
    // }
}
