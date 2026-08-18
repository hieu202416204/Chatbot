package chatbot.repository;

import chatbot.config.Connections;
import chatbot.model.Message;
import chatbot.repository.interfaces.MessageRInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MessageRepository implements MessageRInterface {
    @Override
    public boolean createMessage(int conversationID, String content) {
        String sql = """
                insert into \"message\"(conversation_id, content) values(?,?)
                """;
        try(Connection c = Connections.getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, conversationID);
            ps.setString(2, content);
            int check = ps.executeUpdate();
            if(check>0){
                System.out.println("CREATE SUCCESSFUL!");
                return true;
            }
            else{
                System.out.println("FAILED!");
                return false;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Message findMessageByMID(int messageID) {
        String sql = """
                select * from \"message\" where id = ?
                """;
        try(Connection c = Connections.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, messageID);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Message message = new Message(rs.getInt("id"), rs.getInt("conversation_id"), rs.getString("content"),
                        rs.getString("role"), rs.getObject("created_at", LocalDateTime.class));
                System.out.println("FOUND!");
                return message;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Message> findMessageByCID(int conversationID) {
        List<Message> list = new ArrayList<>();
        String sql = """
                select * from \"message\" where conversation_id = ?
                """;
        try(Connection c = Connections.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, conversationID);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Message message = new Message(rs.getInt("id"), rs.getInt("conversation_id"), rs.getString("content"),
                        rs.getString("role"), rs.getObject("created_at", LocalDateTime.class));
                list.add(message);
            }
            System.out.println("FOUND!");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean deleteMessage(int messageID) {
        String sql = """
                delete from \"message\" where id = ?
                """;
        try(Connection c = Connections.getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, messageID);
            int check = ps.executeUpdate();
            if(check>0){
                System.out.println("DELETE SUCCESSFUL!");
                return true;
            }
            else{
                System.out.println("FAILED!");
                return false;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Message> getAllMessages() {
        List<Message> list = new ArrayList<>();
        String sql = """
                select * from \"message\"
                """;
        try(Connection c = Connections.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Message message = new Message(rs.getInt("id"), rs.getInt("conversation_id"), rs.getString("content"),
                        rs.getString("role"), rs.getObject("created_at", LocalDateTime.class));
                list.add(message);
            }
            System.out.println("FOUND!");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
}
