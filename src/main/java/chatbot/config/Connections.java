package chatbot.config;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connections {
    public static Connection getConnection() {
        Connection c = null;

        try
        {
            c = DriverManager.getConnection(DatabaseConfig.URL, DatabaseConfig.USER_NAME, DatabaseConfig.PASSWORD);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return c;
    }
    public static void closeConnection(Connection c){
        try
        {
            if(c!=null) c.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
