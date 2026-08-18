package chatbot.repository.interfaces;

import chatbot.model.User;

import java.util.List;

public interface UserRInterface {
    boolean createUser(User user);
    User findUserByID(int userID);
    User findUserByEmail(String userEmail);
    boolean deleteUserByID(int userID);
    boolean updateUser(int userID, String name, String email, String pwHash);

    List<User> getAllUsers();
}
