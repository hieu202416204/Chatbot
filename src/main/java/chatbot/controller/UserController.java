package chatbot.controller;

import chatbot.DTO.UserRequestDTO;
import chatbot.DTO.UserResponseDTO;
import chatbot.service.interfaces.ChatSInterface;
import chatbot.service.interfaces.UserSInterface;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final ChatSInterface chatService;
    private final UserSInterface userService;
    public UserController(ChatSInterface chatService, UserSInterface userService){
        this.userService = userService;
        this.chatService = chatService;
    }
    @PostMapping("")
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO user){
        UserResponseDTO responseDTO = userService.createUser(user);
        if(responseDTO != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("FAIL", "EMAIL EXISTED").build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Boolean> updateUser(@PathVariable("id") int id, @Valid @RequestBody UserRequestDTO user){
        boolean status = userService.updateUser(id, user);
        if(status){
            return ResponseEntity.status(HttpStatus.OK).body(true);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).header("FAIL", "CAN NOT UPDATED").build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteUser(@NotBlank @PathVariable("id") int id){
        boolean status = userService.deleteUser(id);
        if(status){
            return ResponseEntity.status(HttpStatus.OK).header("SUCCESS", "DELETED").build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).header("FAIL", "CAN NOT DELETE").build();
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody UserRequestDTO user){
        UserResponseDTO response = userService.login(user);
        if(response != null){
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
}
