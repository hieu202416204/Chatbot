package chatbot.service.interfaces;

import chatbot.model.User;


public interface UserSInterface {
    User createUser(User user);
    boolean deleteUser(User user);
    boolean updateUser(User user);
    User login(User user);
    boolean logout(User user);
}
