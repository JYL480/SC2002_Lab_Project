public class Faculty {

    private String id;
    private String name;

    // Default constructor
    public Faculty() {
        // Default constructor
    }

    // Parameterized constructor
    public Faculty(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getter method for id
    public String getId() {
        return id;
    }

    // Setter method for id
    public void setId(String id) {
        this.id = id;
    }

    // Getter method for name
    public String getName() {
        return name;
    }

    // Setter method for name
    public void setName(String name) {
        this.name = name;
    }
}
