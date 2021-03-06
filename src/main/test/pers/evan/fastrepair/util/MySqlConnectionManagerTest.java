package pers.evan.fastrepair.util;

import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by cfwloader on 4/8/15.
 */
public class MySqlConnectionManagerTest {
    @Test
    public void testGetDefaultConnection() throws SQLException {
        Connection connection = MySqlConnectionManager.getDefaultConnection();
        Statement statement = connection.createStatement();
        statement.close();
        connection.close();
    }
}
