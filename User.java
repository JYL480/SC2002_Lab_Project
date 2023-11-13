
public class User {
    private String userID;
    private String password;
    private String name;
    private String faculty;
    private String email;

    public User(){
        this(null, null, null, null, null);
    }
    public User(String userID, String password, String name, String faculty, String email){
        this.userID = userID;
        this.password = password;
        this.name = name;
        this.faculty = faculty;
        this.email = email;
    }

    public String getUser(){
        return userID;
    }
    public String getPass(){
        return password;
    }
    public String getName(){
        return name;
    }
    public String getFaculty() {
        return faculty;
    }
    public String getEmail() {
        return email;
    }
    
}