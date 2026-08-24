package chatbot.DTO;

import jakarta.validation.constraints.NotBlank;

public class MessageResponseDTO {
    private String content;

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
