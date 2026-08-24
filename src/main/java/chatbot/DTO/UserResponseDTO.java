package chatbot.DTO;

import jakarta.validation.constraints.NotBlank;

public class UserResponseDTO {
    @NotBlank(message = "ID must not be null")
    private int id;
    @NotBlank(message = "Name must not be null")
    private String name;
    @NotBlank(message = "Email must not be null")
    private String email;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
