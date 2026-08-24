package chatbot.DTO;

import jakarta.validation.constraints.NotBlank;

public class MessageRequestDTO {
    @NotBlank( message = "Content must not be null")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
