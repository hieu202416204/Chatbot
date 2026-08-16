package chatbot.repository.interfaces;

import chatbot.model.User;

public interface UserRInterface {
    boolean createUser(String name, String email, String pwHash);
    User findUserByID(int userID);
    User findUserByEmail(String userEmail);
    boolean deleteUserByID(int userID);
    boolean updateUser(int userID, String name, String email, String pwHash);

}
