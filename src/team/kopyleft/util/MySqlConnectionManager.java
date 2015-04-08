package team.kopyleft.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by cfwloader on 4/8/15.
 */
public class MySqlConnectionManager {

    static{
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private MySqlConnectionManager(){}

    public static Connection getDefaultConnection(){
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/fastrepair", "clown", "123456");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
