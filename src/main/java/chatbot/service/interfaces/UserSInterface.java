package chatbot.service.interfaces;

import chatbot.DTO.UserRequestDTO;
import chatbot.DTO.UserResponseDTO;


public interface UserSInterface {
    UserResponseDTO createUser(UserRequestDTO user);
    boolean deleteUser(int userId);
    boolean updateUser(int id, UserRequestDTO userId);
    UserResponseDTO login(UserRequestDTO user);
    boolean logout(UserRequestDTO user);
}
