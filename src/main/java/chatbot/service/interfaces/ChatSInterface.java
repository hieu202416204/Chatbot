package chatbot.service.interfaces;

import chatbot.DTO.ConversationRequestDTO;
import chatbot.DTO.ConversationResponseDTO;
import chatbot.DTO.MessageRequestDTO;
import chatbot.DTO.MessageResponseDTO;

import java.util.List;

public interface ChatSInterface {
    int createConversation(int userId, ConversationRequestDTO conversation);
    MessageResponseDTO chat(int conversationId, String role, MessageRequestDTO message);
    boolean updateConversation(int id, ConversationRequestDTO conversation);
    boolean deleteConversation(int id);
    boolean deleteMessage(int messageId);
    List<ConversationResponseDTO> getAllUserConversations(int userId);
    List<MessageResponseDTO> getAllConversationMessages(int id);
}
