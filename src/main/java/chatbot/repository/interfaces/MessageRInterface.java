package chatbot.repository.interfaces;

import chatbot.model.Message;

import java.util.List;

public interface MessageRInterface {
    boolean createMessage(int conversationID, String content);
    Message findMessageByMID(int messageID);
    List<Message> findMessageByCID(int conversationID);
    boolean deleteMessage(int messageID);
    List<Message> getAllMessages();
}
