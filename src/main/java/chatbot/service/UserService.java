package chatbot.service;

import chatbot.model.User;

import chatbot.repository.interfaces.UserRInterface;
import chatbot.service.interfaces.UserSInterface;

public class UserService implements UserSInterface {
    private final UserRInterface userRepository;
    public UserService(UserRInterface userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public User createUser(User user) {
        if(userRepository.createUser(user)){
            User u = userRepository.findUserByEmail(user.getEmail());
            return u;
        }
        return null;
    }

    @Override
    public boolean deleteUser(User user) {
        return userRepository.deleteUserByID(user.getId());
    }

    @Override
    public boolean updateUser(User user) {
        return userRepository.updateUser(user.getId(), user.getName(), user.getEmail(), user.getPassword_hash());
    }

    @Override
    public User login(User user) {
        User userLogin = userRepository.findUserByEmail(user.getEmail());
        if(userLogin!=null){
            if(user.getPassword_hash().equals(userLogin.getPassword_hash())) return userLogin;
        }
        return null;
    }

    // log-out chua sd
    @Override
    public boolean logout(User user) {
        return true;
    }
}
