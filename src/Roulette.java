import java.sql.*;
import java.util.Random;

public class Roulette {

    //Fields with log-on information
    private String JdbcUrl = "jdbc:mysql://localhost/world?" + "autoReconnect=true&useSSL=false";
    private String username = "root";
    private String password = "Lucas464!"; //Remember to change password**********************
    private Connection connection = null;

    //Generates a random number to be used in the "run" function
    private int randomNum(int max) {
        Random rand = new Random();
        int result = rand.nextInt(3);
        return result;
    }

    public void run() {
        try {
            connection = DriverManager.getConnection(JdbcUrl, username, password);

            //Switch statement that randomizes the alcohol chosen for Surprise Me!
            switch (randomNum(3)) {
                case 0: //If 0 is chosen from randomNum(), this case will run

                    //Randomizing between all the beer, if beer is chosen
                    int beerChoice = randomNum(5);

                    //Connection to database with SQL query
                    PreparedStatement statement1 = connection.prepareStatement("SELECT * FROM iceprojekt.beer where ID = ? ");
                    statement1.setInt(1, beerChoice);
                    ResultSet beerResult = statement1.executeQuery();

                    String beerBuild = " ";
                    while (beerResult.next()) {

                        //Getting all the criteria for set beer, to be used in "beerBuild"
                        String beerName = beerResult.getString("Name");
                        String beerType = beerResult.getString("Type");
                        String beerPrice = beerResult.getString("Price");
                        String beerNote = beerResult.getString("Notes");
                        String beerCountry = beerResult.getString("Country");

                        //Building a string to print all criteria for a randomized beer
                        beerBuild = "Your Surprise Me Beer!" + "\n" + "> Name: " + beerName + "\n" + "> Type: " + beerType + "\n" +
                                "> Price: " + beerPrice+ " DKK/L" + "\n" + "> Note: " + beerNote + "\n" + "> Country: " + beerCountry;
                    }
                        System.out.println(beerBuild);

                    break;


                case 1: //If 1 is generated from randomNum(), this case will run

                    //Randomizing between all the wine, if wine is chosen
                    int wineChoice = randomNum(5);

                    //Connection to database with SQL query
                    PreparedStatement statement2 = connection.prepareStatement("SELECT * FROM iceprojekt.wine where ID = ? ");
                    statement2.setInt(1, wineChoice);
                    ResultSet wineResult = statement2.executeQuery();

                    String wineBuild = " ";
                    while (wineResult.next()) {

                        //Getting all the criteria for set wine, to be used in "wineBuild"
                        String wineName = wineResult.getString("Name");
                        String wineType = wineResult.getString("Type");
                        String winePrice = wineResult.getString("Price");
                        String wineNote = wineResult.getString("Notes");
                        String wineCountry = wineResult.getString("Country");

                        //Building a string to print all criteria for a randomized wine
                        wineBuild = "Your Surprise Me Wine!" + "\n" + "> Name: " + wineName + "\n" + "> Type: " + wineType + "\n" +
                                "> Price: " + winePrice+ " DKK/L" + "\n" + "> Note: " + wineNote + "\n" + "> Country: " + wineCountry;
                    }
                    System.out.println(wineBuild);

                    break;

                case 2: //If 2 is generated from randomNum(), this case will run

                    //Randomizing between all the spirit, if spirit is chosen
                    int spiritChoice = randomNum(5);

                    //Connection to database with SQL query
                    PreparedStatement statement3 = connection.prepareStatement("SELECT * FROM iceprojekt.spirits where ID = ? ");
                    statement3.setInt(1, spiritChoice);
                    ResultSet spiritResult = statement3.executeQuery();

                    String spiritBuild = " ";

                    while (spiritResult.next()) {

                        //Getting all the criteria for set spirit, to be used in "spiritBuild"
                        String spiritName = spiritResult.getString("Name");
                        String spiritType = spiritResult.getString("Type");
                        String spiritPrice = spiritResult.getString("Price");
                        String spiritNote = spiritResult.getString("Notes");
                        String spiritCountry = spiritResult.getString("Country");

                        //Building a string to print all criteria for a randomized spirit
                        spiritBuild = "Your Surprise Me Spirit!" + "\n" + "> Name: " + spiritName + "\n" + "> Type: " + spiritType + "\n" +
                                "> Price: " + spiritPrice+ " DKK/L" + "\n" + "> Note: " + spiritNote + "\n" + "> Country: " + spiritCountry;
                    }
                        System.out.println(spiritBuild);

                        break;
                    }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }