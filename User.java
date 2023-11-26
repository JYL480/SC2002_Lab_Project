public class User {
    private String id;
    private String password;
    private String name;
    private String email;
    private boolean isNewLogin;
    private String facultyId;

    public User(String id, String password, String name, String email, String facultyId) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.email = email;
        this.isNewLogin = true; // Default value
        this.facultyId = facultyId; // Default value
    }

    // Additional constructor with isNewLogin
    public User(String id, String password, String name, String email, boolean isNewLogin, String facultyId) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.email = email;
        this.isNewLogin = isNewLogin;
        this.facultyId = facultyId;
    }

    // Getter and setter methods for isNewLogin
    public boolean isNewLogin() {
        return isNewLogin;
    }

    public void setNewLogin(boolean isNewLogin) {
        this.isNewLogin = isNewLogin;
    }

    // Getter and setter methods for facultyId
    public String getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId;
    }

    // Getter and setter methods for other attributes
    public String getId() {
        return this.id;
    }

    public String getPassword() {
        return this.password;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

