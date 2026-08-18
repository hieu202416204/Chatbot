package chatbot.model;


import java.sql.Timestamp;
import java.time.LocalDateTime;

public class User {
    private int id;
    private String name;
    private String password_hash;
    private String email;
    private String user_role;
    private LocalDateTime created_at;

    public User(){}
    public User(int id, String name, String email, String password_hash,String user_role, LocalDateTime timestamp){
        this.id = id;
        this.name = name;
        this.email = email;
        this.password_hash = password_hash;
        this.user_role = user_role;
        this.created_at = timestamp;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password_hash='" + password_hash + '\'' +
                ", email='" + email + '\'' +
                ", user_role='" + user_role + '\'' +
                ", created_at=" + created_at +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser_role() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }
}
