package chatbot.model;


import java.sql.Timestamp;

public class User {
    private int id;
    private String name;
    private String password_hash;
    private String email;
    private String user_role;
    private Timestamp created_at;

    public User(){}

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

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }
}
