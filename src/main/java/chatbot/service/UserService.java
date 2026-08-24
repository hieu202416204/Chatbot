package chatbot.service;

import chatbot.DTO.UserRequestDTO;
import chatbot.DTO.UserResponseDTO;
import chatbot.model.User;

import chatbot.repository.interfaces.UserRInterface;
import chatbot.service.interfaces.UserSInterface;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserSInterface {
    private final UserRInterface userRepository;
    public UserService(UserRInterface userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        User user = new User(userRequestDTO.getName(), userRequestDTO.getEmail(), userRequestDTO.getPwHash());
        if(userRepository.createUser(user)){
            User u = userRepository.findUserByEmail(user.getEmail());
            UserResponseDTO response = new UserResponseDTO();
            response.setName(u.getName());
            response.setEmail(u.getEmail());
            response.getId();
            return response;
        }
        return null;
    }

    @Override
    public boolean deleteUser(int userId) {
        return userRepository.deleteUserByID(userId);
    }

    @Override
    public boolean updateUser(int id, UserRequestDTO user) {
        return userRepository.updateUser(id, user.getName(), user.getEmail(), user.getPwHash());
    }

    @Override
    public UserResponseDTO login(UserRequestDTO user) {
        User userLogin = userRepository.findUserByEmail(user.getEmail());
        if(userLogin!=null){
            if(user.getPwHash().equals(userLogin.getPassword_hash())){
                UserResponseDTO response = new UserResponseDTO();
                response.setName(userLogin.getName());
                response.setEmail(userLogin.getEmail());
                response.setId(userLogin.getId());
                return response;
            }
        }
        return null;
    }

    // log-out chua sd
    @Override
    public boolean logout(UserRequestDTO user) {
        return true;
    }
}
