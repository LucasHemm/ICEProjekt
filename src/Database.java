import java.sql.*;

public class Database implements IDatabase
{
    //Fields to connect to database
    private final String JdbcUrl = "jdbc:mysql://localhost/iceprojekt?" + "autoReconnect=true&useSSL=false";
    private final String username = "root";
    private final String password = "*******"; //Remember to change password**********************
    private Connection connection = null;

    //Fields tto create an instance of the Person class
    private Beer beer;
    private Wine wine;
    private Spirit spirit;


    @Override
    public void saveData(Person person)
    {
        try {
            connection = DriverManager.getConnection(JdbcUrl, username, password);
            deleteUser(person);
            //Saves the users information
            PreparedStatement statement = connection.prepareStatement("INSERT INTO iceprojekt.user (Email, Password, FirstName, LastName, Age, FavoriteBeer, FavoriteWine, FavoriteSpirit)" +
                    " VALUES(?,?,?,?,?,?,?,?)");
            statement.setString(1,person.getEmail());
            statement.setString(2,person.getPassword());
            statement.setString(3,person.getFirstName());
            statement.setString(4,person.getLastName());
            statement.setInt(5,person.getAge());
            statement.setInt(6, getIndexFromName(person.getBeer().getName(), "beer"));
            statement.setInt(7,getIndexFromName(person.getWine().getName(),"wine"));
            statement.setInt(8,getIndexFromName(person.getSpirit().getName(),"spirit"));

            int result = statement.executeUpdate();

        } catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public Person login(String email, String password1)
    {
        Person person = null;
        try {
            connection = DriverManager.getConnection(JdbcUrl, username, password);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM iceprojekt.user where Email = ? AND Password =?");
            statement.setString(1,email);
            statement.setString(2,password1);
            ResultSet result = statement.executeQuery();

            if(result.next())
            {
                String userEmail = result.getString("Email");
                String userPassword = result.getString("Password");
                String firstName = result.getString("FirstName");
                String lastName = result.getString("LastName");
                int age = result.getInt("Age");
                beer = (Beer) createAlcoholFromIndex(result.getInt("FavoriteBeer"), "beer");
                wine = (Wine) createAlcoholFromIndex(result.getInt("FavoriteWine"), "wine");
                spirit = (Spirit) createAlcoholFromIndex(result.getInt("FavoriteSpirit"), "spirit");

                person = new Person(userEmail, userPassword, firstName, lastName, age, beer, wine, spirit);
            }

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
                statement = connection.prepareStatement("SELECT * FROM iceprojekt.wine where ID = ?");
            }

            else
            {
                statement = connection.prepareStatement("SELECT * FROM iceprojekt.spirits where ID = ?");

            }

            statement.setInt(1,index);
            ResultSet result = statement.executeQuery();

           while (result.next())
            {
                String name = result.getString("Name");
                String type = result.getString("Type");
                int price = result.getInt("Price");
                String notes = result.getString("Notes");
                String country = result.getString("Country");

                if (Alcoholtype.compareToIgnoreCase(beerString) == 0)
                {
                    beer = new Beer(name, type, price, notes, country);
                    return beer;
                }
                else if (Alcoholtype.compareToIgnoreCase(wineString) == 0)
                {
                    wine = new Wine(name, type, price, notes, country);
                    return wine;
                }
                else
                {
                    spirit = new Spirit(name, type, price, notes, country);
                    return spirit;
                }

            }

        } catch(SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

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
                statement = connection.prepareStatement("SELECT * FROM iceprojekt.beer where Name = ?");
            }
            else if(type.compareToIgnoreCase(wineString)==0)
            {
                statement = connection.prepareStatement("SELECT *  FROM iceprojekt.wine where Name = ?");
            }
            else
            {
                statement = connection.prepareStatement("SELECT * FROM iceprojekt.spirits where Name = ?");
            }


            statement.setString(1,name);
            ResultSet result = statement.executeQuery();

            while (result.next())
            {
                ID = result.getInt("ID");
            }

        } catch(SQLException e)
        {
            e.printStackTrace();
        }
        return ID;
    }

    private void deleteUser(Person person)
    {
        try {
            connection = DriverManager.getConnection(JdbcUrl, username, password);

            //Deletes the previous information of the user
            PreparedStatement delete = connection.prepareStatement("DELETE FROM iceprojekt.user WHERE Email = ? and Password = ?");
            delete.setString(1,person.getEmail());
            delete.setString(2, person.getPassword());
            int delResult = delete.executeUpdate();

        } catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
}
