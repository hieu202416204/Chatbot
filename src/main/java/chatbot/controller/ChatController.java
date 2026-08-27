package chatbot.controller;

import chatbot.DTO.ConversationRequestDTO;
import chatbot.DTO.ConversationResponseDTO;
import chatbot.DTO.MessageRequestDTO;
import chatbot.DTO.MessageResponseDTO;
import chatbot.service.interfaces.ChatSInterface;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/chats")
public class ChatController {
    private final ChatSInterface chatService;
    public ChatController(ChatSInterface chatService){
        this.chatService = chatService;
    }

    @PostMapping("/{user_id}")
    public ResponseEntity<ConversationResponseDTO> createConversation(@PathVariable("user_id") int userId, @Valid @RequestBody ConversationRequestDTO conversation){
        int conversationId = chatService.createConversation(userId, conversation);
        ConversationResponseDTO conversationResponseDTO = new ConversationResponseDTO();
        conversationResponseDTO.setUserId(userId);
        conversationResponseDTO.setTitle(conversation.getTitle());
        conversationResponseDTO.setId(conversationId);
        if(conversationId != -1){
            return ResponseEntity.status(HttpStatus.CREATED).body(conversationResponseDTO);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    @PostMapping("/conversation/{conversation_id}")
    public ResponseEntity<MessageResponseDTO> chat(@PathVariable("conversation_id") int conversationId,
                                                   @Valid @RequestBody MessageRequestDTO messageRequestDTO){
        MessageResponseDTO message = chatService.chat(conversationId, messageRequestDTO);
        if(message!=null){
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    @GetMapping("/{conversation_id}")
    public ResponseEntity<List<MessageResponseDTO>> getAllConversationMessages(@PathVariable("conversation_id") int conversationId){
        List<MessageResponseDTO> list = new ArrayList<>();
        list = chatService.getAllConversationMessages(conversationId);
        if(list!=null){
            return ResponseEntity.status(HttpStatus.OK).body(list);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @PatchMapping("/{conversation_id}")
    public ResponseEntity<Boolean> updateConversation(@PathVariable("conversation_id") int conversationId, @Valid @RequestBody ConversationRequestDTO conversationRequestDTO){
        Boolean check = chatService.updateConversation(conversationId, conversationRequestDTO);
        if(check == true){
            return ResponseEntity.status(HttpStatus.OK).body(true);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @DeleteMapping("/conversation/{conversation_id}")
    public ResponseEntity<Boolean> deleteConversation(@PathVariable("conversation_id") int conversationId){
        return ResponseEntity.status(HttpStatus.OK).body(chatService.deleteConversation(conversationId));
    }
    @DeleteMapping("/message/{message_id}")
    public ResponseEntity<Boolean> deleteMessage(@PathVariable("message_id") int messageId){
        return ResponseEntity.status(HttpStatus.OK).body(chatService.deleteMessage(messageId));
    }
}
