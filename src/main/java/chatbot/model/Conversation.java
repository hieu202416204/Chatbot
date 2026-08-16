package chatbot.model;

import chatbot.repository.ConversationRepository;

import javax.imageio.plugins.tiff.TIFFImageReadParam;
import java.sql.Timestamp;

public class Conversation {
    private int id;
    private String title;
    private int user_id;
    private Timestamp created_at;
    private Timestamp updated_at;
    public Conversation(){}

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

    public Timestamp getCreated_at() {
        return created_at;
    }

    public Timestamp getUpdated_at() {
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

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }
}
