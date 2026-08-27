package chatbot.repository;

import chatbot.config.Connections;
import chatbot.model.Conversation;
import chatbot.model.User;
import chatbot.repository.interfaces.ConversationRInterface;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Repository
public class ConversationRepository implements ConversationRInterface {
    @Override
    public int createConversation(Conversation conversation) {
        String sql = """
                insert into conversation(title, user_id) values(?,?)
                """;
        String title = conversation.getTitle();
        int userID = conversation.getUser_id();
        try(Connection c = Connections.getConnection(); PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1,title);
            ps.setInt(2, userID);
            int check = ps.executeUpdate();
            if(check>0){
                System.out.println("ADD SUCCESSFUL!");
                ResultSet rs = ps.getGeneratedKeys();
                if(rs.next()){
                    int keyID = rs.getInt(1);
                    return keyID;
                }
                return -1;
            }
            else{
                System.out.println("ADD FAILED!");
                return -1;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public Conversation findConversationByCID(int conversationID) {
        String sql = """
                select * from conversation where id = ?
                """;
        try(Connection c = Connections.getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, conversationID);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Conversation conversation = new Conversation(rs.getInt("id"), rs.getString("title"),
                        rs.getInt("user_id"), rs.getObject("created_at", LocalDateTime.class),rs.getObject("updated_at", LocalDateTime.class));
                System.out.println("FOUND!");
                return conversation;
            }
            else{
                System.out.println("NOT FOUND!");
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Conversation> findConversationByUID(int userID) {
        List<Conversation> list = new ArrayList<>();
        String sql = """
                select * from conversation where user_id = ?
                """;
        try(Connection c = Connections.getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();
            int sum = 0;
            while(rs.next()){
                Conversation conversation = new Conversation(rs.getInt("id"), rs.getString("title"),
                        rs.getInt("user_id"), rs.getObject("created_at", LocalDateTime.class),rs.getObject("updated_at", LocalDateTime.class));
                list.add(conversation);
                sum++;
            }
            System.out.println("FOUND " + sum +" CONVERSATIONS!");
            return list;
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteConversation(int conversationID) {
        String sql = """
                delete from conversation where id = ?
                """;
        try(Connection c = Connections.getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, conversationID);
            int check = ps.executeUpdate();
            if(check>0){
                System.out.println("DELETED SUCCESSFUL!");
                return true;
            }
            else{
                System.out.println("DELETE FAILED!");
                return false;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateConversation(String title, int conversationID) {
        String sql = """
                update conversation set title = ? where id = ?
                """;
        try(Connection c = Connections.getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1, title);
            ps.setInt(2, conversationID);
            int check = ps.executeUpdate();
            if(check>0){
                System.out.println("UPDATED SUCCESSFUL!");
                return true;
            }
            else{
                System.out.println("UPDATE FAIL!");
                return false;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Conversation> getAllConversations() {
        List<Conversation> list = new ArrayList<>();
        String sql = """
                select * from conversation
                """;
        try(Connection c = Connections.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Conversation conversation = new Conversation(rs.getInt("id"), rs.getString("title"),
                        rs.getInt("user_id"), rs.getObject("created_at", LocalDateTime.class), rs.getObject("updated_at", LocalDateTime.class));
                list.add(conversation);
            }
            return list;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Conversation> getAllUserConversations(int userId){
        List<Conversation> list = new ArrayList<>();
        String sql = """
                select * from conversation where user_id = ?
                """;
        try(Connection c = Connections.getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Conversation conversation = new Conversation(rs.getInt("id"),rs.getString("title"), rs.getInt("user_id"), rs.getObject("created_at", LocalDateTime.class), rs.getObject("updated_at", LocalDateTime.class));
                list.add(conversation);
            }
            System.out.println("SUCCESSFUL!");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

}
