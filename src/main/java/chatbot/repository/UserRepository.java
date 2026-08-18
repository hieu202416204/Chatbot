package chatbot.repository;

import chatbot.config.Connections;
import chatbot.model.User;
import chatbot.repository.interfaces.UserRInterface;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements UserRInterface {
    @Override
    public boolean createUser(User user) {
        String sql = """
                insert into \"user\"(user_name, email, password_hash)
                values (?, ?, ?)
                """;
        String name = user.getName();
        String email = user.getEmail();
        String pwHash = user.getPassword_hash();
        try(Connection c = Connections.getConnection(); PreparedStatement ps = c.prepareStatement(sql))
        {
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, pwHash);
            int check = ps.executeUpdate();
            if(check>0){
                System.out.println("ADD NEW USER SUCCESSFUL!");
                return true;
            }
            else{
                System.out.println("ADD FAILED!");
                return false;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User findUserByID(int userID) {
        User user = new User();
        String sql = """
                select * from \"user\" where id = ?
                """;
        try(Connection c = Connections.getConnection(); PreparedStatement ps = c.prepareStatement(sql))
        {
            ps.setInt(1, userID);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    user.setId(rs.getInt("id"));
                    user.setUser_role(rs.getString("user_role"));
                    user.setName(rs.getString("user_name"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword_hash(rs.getString("password_hash"));
                    user.setCreated_at(rs.getObject("created_at", LocalDateTime.class));
                    System.out.println("FOUND USER!");
                    return user;
                }
                else System.out.println("NOT FOUND!");
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User findUserByEmail(String userEmail) {
        User user = new User();
        String sql = """
                select * from \"user\" where email = ?
                """;
        try(Connection c = Connections.getConnection(); PreparedStatement ps = c.prepareStatement(sql))
        {
            ps.setString(1, userEmail);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    user.setId(rs.getInt("id"));
                    user.setUser_role(rs.getString("user_role"));
                    user.setName(rs.getString("user_name"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword_hash(rs.getString("password_hash"));
                    user.setCreated_at(rs.getObject("created_at", LocalDateTime.class));
                    System.out.println("FOUND USER!");
                    return user;
                }
                else System.out.println("NOT FOUND!");
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteUserByID(int userID) {
        String sql = """
                delete from \"user\" where id = ?
                """;
        try(Connection c = Connections.getConnection(); PreparedStatement ps = c.prepareStatement(sql))
        {
            ps.setInt(1, userID);
            int check = ps.executeUpdate();
            if(check>0){
                System.out.println("DELETED!");
                return true;
            }
            else{
                System.out.println("DELETED FAIL!");
                return false;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateUser(int userID, String name, String email, String pwHash) {
        String sql = """
                update \"user\" set user_name = ?, email = ?, password_hash = ? where id = ?
                """;
        try(Connection c = Connections.getConnection(); PreparedStatement ps = c.prepareStatement(sql))
        {
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, pwHash);
            ps.setInt(4, userID);
            int check = ps.executeUpdate();
            if(check>0){
                System.out.println("UPDATED!");
                return true;
            }
            else{
                System.out.println("UPDATED FAIL!");
                return false;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<User> getAllUsers(){
        List<User> list = new ArrayList<>();
        String sql = """
                select * from \"user\"
                """;
        try(Connection c = Connections.getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                LocalDateTime time = resultSet.getObject("created_at", LocalDateTime.class);
                User user = new User(resultSet.getInt("id"), resultSet.getString("user_name"),
                        resultSet.getString("email"), resultSet.getString("password_hash"),resultSet.getString("user_role"), time);
                list.add(user);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
}
