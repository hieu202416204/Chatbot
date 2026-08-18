package chatbot.repository.interfaces;

import chatbot.model.Conversation;
import chatbot.model.Message;

import java.util.List;

public interface MessageRInterface {
    int createMessage(Message message); // tra ve id tin nhan
    Message findMessageByMID(int messageID);
    List<Message> findMessageByCID(int conversationID);
    boolean deleteMessage(int messageID);
    List<Message> getAllMessages();
    List<Message> getAllConversationMessages(Conversation conversation);
}
