package chatbot.service.interfaces;

import chatbot.model.Conversation;
import chatbot.model.Message;
import chatbot.model.User;

import java.util.List;

public interface ChatSInterface {
    Conversation createConversation(Conversation conversation);
    Message sendMessage(Message message);
    Message receiveMessage(Message message);
    boolean updateConversation(Conversation conversation);
    boolean deleteConversation(Conversation conversation);
    boolean deleteMessage(Message message);
    List<Conversation> getAllUserConversations(User user);
    List<Message> getAllConversationMessages(Conversation conversation);
}
