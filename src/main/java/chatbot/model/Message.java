package chatbot.model;

import java.sql.Timestamp;

public class Message {
    private int id;
    private int conversation_id;
    private String content;
    private String role;
    private Timestamp created_at;

    public Message(){}

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", conversation_id=" + conversation_id +
                ", content='" + content + '\'' +
                ", role='" + role + '\'' +
                ", created_at=" + created_at +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getConversation_id() {
        return conversation_id;
    }

    public void setConversation_id(int conversation_id) {
        this.conversation_id = conversation_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }
}
