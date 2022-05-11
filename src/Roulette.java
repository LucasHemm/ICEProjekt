import java.sql.*;
import java.util.Random;

public class Roulette {

    //Fields with log-on information
    private String JdbcUrl = "jdbc:mysql://localhost/world?" + "autoReconnect=true&useSSL=false";
    private String username = "root";
    private String password = "*****"; //Remember to change password**********************
    private Connection connection = null;

    public void roulette() {

        try {
            connection = DriverManager.getConnection(JdbcUrl, username, password);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM iceprojekt ORDER BY id");

            ResultSet random = statement.executeQuery();

            String[] randomAlco = {"Beer", "Wine", "Spirit"};
            int randomAlcohol = textUI.select("You'll receive a randomly generated alcohol from these three", randomAlco, "");
            switch (randomAlcohol) {
                case 0:
                    //Beer random
                    //SQL query

                    Beer beer = new Beer(id);
                    break;
                case 1:
                    //Wine
                    break;
                case 2:
                    //Spirit
                    break;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}