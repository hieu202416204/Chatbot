package chatbot.service;

import chatbot.DTO.ConversationRequestDTO;
import chatbot.DTO.ConversationResponseDTO;
import chatbot.DTO.MessageRequestDTO;
import chatbot.DTO.MessageResponseDTO;
import chatbot.model.Conversation;
import chatbot.model.Message;
import chatbot.repository.interfaces.ConversationRInterface;
import chatbot.repository.interfaces.MessageRInterface;
import chatbot.service.interfaces.ChatSInterface;

import java.util.ArrayList;
import java.util.List;

public class ChatService implements ChatSInterface {
    private final ConversationRInterface conversationRepository;
    private final MessageRInterface messageRepository;
    public ChatService(ConversationRInterface conversationRepository, MessageRInterface messageRepository){
        this.conversationRepository = conversationRepository;
        this.messageRepository = messageRepository;
    }


    @Override
    public int createConversation(int userId, ConversationRequestDTO conversation) {
        Conversation c = new Conversation();
        c.setTitle(conversation.getTitle());
        c.setUser_id(userId);
        return conversationRepository.createConversation(c);
    }

    @Override
    public MessageResponseDTO chat(int conversationId, String role,  MessageRequestDTO message) {
        // client gui
        Message newMessage = new Message();
        newMessage.setContent(message.getContent());
        newMessage.setConversation_id(conversationId);
        newMessage.setRole(role);
        messageRepository.createMessage(newMessage);

        // ai tra ve
        Message newMessageR = new Message();
        newMessageR.setContent(message.getContent()); // goi ai o day
        newMessageR.setConversation_id(conversationId);
        newMessageR.setRole("ASSISTANT");
        int responseId = messageRepository.createMessage(newMessageR);

        // phan hoi lai
        MessageResponseDTO m = new MessageResponseDTO();
        m.setContent(newMessageR.getContent());
        m.setId(responseId);
        return m;
    }

    @Override
    public boolean updateConversation(int id, ConversationRequestDTO conversation) {
        Conversation c = conversationRepository.findConversationByCID(id);
        if(c!=null){
            return conversationRepository.updateConversation(conversation.getTitle(), id);
        }
        return false;
    }

    @Override
    public boolean deleteConversation(int id) {
        return conversationRepository.deleteConversation(id);
    }

    @Override
    public boolean deleteMessage(int messageId) {
        return messageRepository.deleteMessage(messageId);
    }

    @Override
    public List<ConversationResponseDTO> getAllUserConversations(int userId) {
        List<ConversationResponseDTO> response = new ArrayList<>();
        List<Conversation> request = conversationRepository.getAllUserConversations(userId);
        if(request!=null){
            for(Conversation c : request){
                ConversationResponseDTO newC = new ConversationResponseDTO();
                newC.setId(c.getId());
                newC.setTitle(c.getTitle());
                newC.setUserId(c.getUser_id());
                response.add(newC);
            }
        }
        return response;
    }

    @Override
    public List<MessageResponseDTO> getAllConversationMessages(int id) {
        List<MessageResponseDTO> response = new ArrayList<>();
        List<Message> request = messageRepository.getAllConversationMessages(id);
        if(request!=null){
            for(Message m : request){
                MessageResponseDTO newM = new MessageResponseDTO();
                newM.setId(m.getId());
                newM.setContent(m.getContent());
                response.add(newM);
            }
        }
        return response;
    }
}
