import org.junit.jupiter.api.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseTest
{
    @Test
    public void connectionOpen_connectionClosed()
    {
        Connection connection = null;

        String JdbcUrl = "jdbc:mysql://localhost/world?" + "autoReconnect=true&useSSL=false";
        String username = "root";
        String password = "rottboell1234";




        try
        {
            connection = DriverManager.getConnection(JdbcUrl,username,password);

            Assertions.assertTrue(connection.isValid(1));

            connection.close();

            Assertions.assertFalse(connection.isValid(1));

        } catch (SQLException e)
        {
            e.printStackTrace();
            Assertions.assertTrue(false);
        }
    }
}
