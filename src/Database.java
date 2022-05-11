import java.sql.*;

public class Database implements IDatabase
{
    //Fields
    private String JdbcUrl = "jdbc:mysql://localhost/world?" + "autoReconnect=true&useSSL=false";
    private String username = "root";
    private String password = "*****"; //Remember to change password**********************
    private Connection connection = null;
    private Beer beer;
    private Wine wine;
    private Spirit spirit;


    @Override
    public void saveData(Person person)
    {

    }

    @Override
    public Person login(String email, String password1)
    {
        try {
            connection = DriverManager.getConnection(JdbcUrl, username, password);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM iceprojekt.user where Email = ? AND Password =?");

            statement.setString(1,email);
            statement.setString(2,password);
            ResultSet result = statement.executeQuery();

            String email = result.getString("Email");
            String password1 = result.getString("Password");
            String firstName = result.getString("FirstName");
            String lastName = result.getString("LastName");
            int age = result.getInt("Age");
            beer = (Beer) createAlcoholFromIndex(result.getInt("ID"),"beer");
            wine = (Wine) createAlcoholFromIndex(result.getInt("ID"),"wine");
            spirit = (Spirit) createAlcoholFromIndex(result.getInt("ID"),"spirit");

            Person person = new Person(email, password1, )

        } catch(SQLException e)
        {
            e.printStackTrace();
        }

        return person;
    }

    private Alcohol createAlcoholFromIndex(int index, String Alcoholtype)
    {
        PreparedStatement statement;
        String beerString = "beer";
        String wineString = "wine";

        try {
            connection = DriverManager.getConnection(JdbcUrl, username, password);
            if(Alcoholtype.compareToIgnoreCase(beerString)==0)
            {
                statement = connection.prepareStatement("SELECT * FROM iceprojekt.beer where ID = ?");
            }
            else if(Alcoholtype.compareToIgnoreCase(wineString)==0)
            {
                statement = connection.prepareStatement("SELECT ID FROM iceprojekt.wine where ID = ?");
            }
            else
            {
                statement = connection.prepareStatement("SELECT ID FROM iceprojekt.spirit where ID = ?");
            }

            statement.setInt(1,index);
            ResultSet result = statement.executeQuery();

            String name = result.getString("Name");
            String type = result.getString("Type");
            int price = result.getInt("Price");
            String notes = result.getString("Notes");
            String country = result.getString("Result");

            if(Alcoholtype.compareToIgnoreCase(beerString)==0)
            {
                beer = new Beer(name,type,price,notes,country);
                return beer;
            }
            else if(Alcoholtype.compareToIgnoreCase(wineString)==0)
            {
                wine = new Wine(name,type,price,notes,country);
                return beer;
            }
            else
            {
                spirit = new Spirit(name,type,price,notes,country);
                return beer;
            }

        } catch(SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }
    /*
    private Beer createBeerFromIndex(int index)
    {
        try {
            connection = DriverManager.getConnection(JdbcUrl, username, password);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM iceprojekt.beer where ID = ? ");

            statement.setInt(1,index);
            ResultSet result = statement.executeQuery();

            String name = result.getString("Name");
            String type = result.getString("Type");
            int price = result.getInt("Price");
            String notes = result.getString("Notes");
            String country = result.getString("Result");

            beer = new Beer(name,type,price,notes,country);

        } catch(SQLException e)
        {
            e.printStackTrace();
        }

        return beer;
    }

    private Wine createWineFromIndex(int index)
    {
        try {
            connection = DriverManager.getConnection(JdbcUrl, username, password);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM iceprojekt.wine where ID = ? ");

            statement.setInt(1,index);
            ResultSet result = statement.executeQuery();

            String name = result.getString("Name");
            String type = result.getString("Type");
            int price = result.getInt("Price");
            String notes = result.getString("Notes");
            String country = result.getString("Result");

            wine = new Wine(name,type,price,notes,country);

        } catch(SQLException e)
        {
            e.printStackTrace();
        }

        return wine;

    }

    private Spirit createSpiritFromIndex(int index)
    {
        try {
            connection = DriverManager.getConnection(JdbcUrl, username, password);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM iceprojekt.beer where ID = ? ");

            statement.setInt(1,index);
            ResultSet result = statement.executeQuery();

            String name = result.getString("Name");
            String type = result.getString("Type");
            int price = result.getInt("Price");
            String notes = result.getString("Notes");
            String country = result.getString("Result");

            spirit = new Spirit(name,type,price,notes,country);

        } catch(SQLException e)
        {
            e.printStackTrace();
        }
        return spirit;
    }

     */

    private int getIndexFromName(String name, String type)
    {
        int ID = 0;
        PreparedStatement statement;
        String beerString = "beer";
        String wineString = "wine";

        try {
            connection = DriverManager.getConnection(JdbcUrl, username, password);
            if(type.compareToIgnoreCase(beerString)==0)
            {
                statement = connection.prepareStatement("SELECT ID FROM iceprojekt.beer where Name = ?");
            }
            else if(type.compareToIgnoreCase(wineString)==0)
            {
                statement = connection.prepareStatement("SELECT ID FROM iceprojekt.wine where Name = ?");
            }
            else
            {
                statement = connection.prepareStatement("SELECT ID FROM iceprojekt.spirit where Name = ?");
            }
            statement.setString(1,name);
            ResultSet result = statement.executeQuery();

            ID = result.getInt("ID");

        } catch(SQLException e)
        {
            e.printStackTrace();
        }

        return ID;
    }
    /*
    private int getIndexFromBeerName(String name)
    {
        int ID = 0;
        try {
            connection = DriverManager.getConnection(JdbcUrl, username, password);
            PreparedStatement statement = connection.prepareStatement("SELECT ID FROM iceprojekt.beer where name = ?");

            statement.setString(1,name);
            ResultSet result = statement.executeQuery();


            ID = result.getInt("ID");


        } catch(SQLException e)
        {
            e.printStackTrace();
        }

        return ID;
    }

    private int getIndexFromWineName(String name)
    {
        int ID = 0;
        try {
            connection = DriverManager.getConnection(JdbcUrl, username, password);
            PreparedStatement statement = connection.prepareStatement("SELECT ID FROM iceprojekt.wine where name = ?");

            statement.setString(1,name);
            ResultSet result = statement.executeQuery();


            ID = result.getInt("ID");


        } catch(SQLException e)
        {
            e.printStackTrace();
        }

        return ID;
    }

    private int getIndexFromSpiritName(String name)
    {
        int ID = 0;
        try {
            connection = DriverManager.getConnection(JdbcUrl, username, password);
            PreparedStatement statement = connection.prepareStatement("SELECT ID FROM iceprojekt.spirits where name = ?");

            statement.setString(1,name);
            ResultSet result = statement.executeQuery();


            ID = result.getInt("ID");


        } catch(SQLException e)
        {
            e.printStackTrace();
        }

        return ID;
    }

     */
}
