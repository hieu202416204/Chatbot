package chatbot.repository.interfaces;

import chatbot.model.Conversation;
import chatbot.model.User;

import java.util.List;

public interface ConversationRInterface {
    int createConversation(Conversation conversation); // tra ve id cua conversation
    Conversation findConversationByCID(int conversationID);
    List<Conversation> findConversationByUID(int userID);
    boolean deleteConversation(int conversationID);
    boolean updateConversation(String title, int conversationID);
    List<Conversation> getAllConversations();
    List<Conversation> getAllUserConversations(int userId);
}
