package chatbot.repository;

import chatbot.config.Connections;
import chatbot.model.User;
import chatbot.repository.interfaces.UserRInterface;

import java.sql.*;

public class UserRepository implements UserRInterface {
    @Override
    public boolean createUser(String name, String email, String pwHash) {
        String sql = """
                insert into \"user\"(user_name, email, password_hash)
                values (?, ?, ?)
                """;
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
                    user.setId(userID);
                    user.setUser_role(rs.getString("user_role"));
                    user.setName(rs.getString("user_name"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword_hash(rs.getString("password_hash"));
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
}
