package chatbot.service;

import chatbot.model.Conversation;
import chatbot.model.Message;
import chatbot.model.User;
import chatbot.repository.interfaces.ConversationRInterface;
import chatbot.repository.interfaces.MessageRInterface;
import chatbot.service.interfaces.ChatSInterface;

import java.util.List;

public class ChatService implements ChatSInterface {
    private final ConversationRInterface conversationRepository;
    private final MessageRInterface messageRepository;
    public ChatService(ConversationRInterface conversationRepository, MessageRInterface messageRepository){
        this.conversationRepository = conversationRepository;
        this.messageRepository = messageRepository;
    }

    @Override
    public Conversation createConversation(Conversation conversation) {
        int conversationID = conversationRepository.createConversation(conversation);
        if(conversationID!=-1){
            Conversation newConversation = conversationRepository.findConversationByCID(conversationID);
            return newConversation;
        }
        return null;
    }

    @Override
    public Message sendMessage(Message message) {
        int messageID = messageRepository.createMessage(message);
        if(messageID!=-1) {
            return messageRepository.findMessageByMID(messageID);
        }
        return null;
    }

    @Override
    public Message receiveMessage(Message message) {
        return null;
    }

    @Override
    public boolean updateConversation(Conversation conversation) {
        return conversationRepository.updateConversation(conversation.getTitle(), conversation.getId());
    }

    @Override
    public boolean deleteConversation(Conversation conversation) {
        return conversationRepository.deleteConversation(conversation.getId());
    }

    @Override
    public boolean deleteMessage(Message message) {
        return messageRepository.deleteMessage(message.getId());
    }

    @Override
    public List<Conversation> getAllUserConversations(User user) {
        return conversationRepository.getAllUserConversations(user);
    }

    @Override
    public List<Message> getAllConversationMessages(Conversation conversation) {
        return messageRepository.getAllConversationMessages(conversation);
    }
}
