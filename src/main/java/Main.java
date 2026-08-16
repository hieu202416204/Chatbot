import chatbot.config.Connections;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        Connection c = Connections.getConnection();
        try {
            Statement st = c.createStatement();
            String sql = "insert into \"user\"(user_name, email, password_hash) values\n" +
                    "('tranloton', 'tran.lt21482@gmail.com', 'jsvbd!&^72')";
                int check = st.executeUpdate(sql);
                if(check>0){
                    System.out.println("Cap nhat thanh cong " + check+ " hang!");
                }
                else System.out.println("Cap nhat that bai!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(c);
    }
}