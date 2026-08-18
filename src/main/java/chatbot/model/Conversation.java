package chatbot.model;

import java.time.LocalDateTime;

public class Conversation {
    private int id;
    private String title;
    private int user_id;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    public Conversation(){}
    public Conversation(int id, String title, int user_id, LocalDateTime created_at, LocalDateTime updated_at){
        this.id = id;
        this.title = title;
        this.user_id = user_id;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "Conversation{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", user_id=" + user_id +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getUser_id() {
        return user_id;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }
}
