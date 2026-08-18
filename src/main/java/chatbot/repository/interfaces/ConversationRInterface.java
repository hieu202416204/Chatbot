package chatbot.repository.interfaces;

import chatbot.model.Conversation;

import java.util.List;

public interface ConversationRInterface {
    boolean createConversation(String title, int userID);
    Conversation findConversationByCID(int conversationID);
    List<Conversation> findConversationByUID(int userID);
    boolean deleteConversation(int conversationID);
    boolean updateConversation(String title, int conversationID);
    List<Conversation> getAllConversations();

}
