import chatbot.config.Connections;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        Connection c = Connections.getConnection();

        System.out.println(c);
       //Connections.closeConnection(c);
    }
}