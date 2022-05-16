import java.sql.*;
import java.util.ArrayList;

public class Menu {

//author Lucas

    TextUI textUI = new TextUI();
    IDatabase database = new Database();
    Person user;

    private String JdbcUrl = "jdbc:mysql://localhost/iceprojekt?" + "autoReconnect=true&useSSL=false";
    private String username = "root";
    private String password = "Lucas464!";
    private Connection connection = null;
    Quiz quiz = new Quiz();

    public Menu(){

    }

    //method that runs the whole program
    public void run() {
        //First menu with 3 choices - Login , Create profile, Login as guest.
        String[] firstMenuChoices = {"Login", "Create Profile", "Login as guest"};
        int firstChoice = textUI.select("Please select a way to load and save your tournament", firstMenuChoices, "");

        switch (firstChoice) {
            case 0:
                while (user == null) {
                    System.out.println("Please enter E-Mail to your account.");
                    String userEmail = textUI.get();
                    System.out.println("Please enter your password.");
                    String userPassword = textUI.get();
                    user = database.login(userEmail, userPassword);
                    if(user == null){
                        System.out.println("Your E-mail or password is incorrect, please try again.");
                    }
                }
                menu();

                 break;
            case 1:
                System.out.println("Please enter E-Mail");
                String email = textUI.get();
                System.out.println("Please enter Password");
                String password = textUI.get();
                System.out.println("Please enter first name:");
                String firstName = textUI.get();
                System.out.println("Please enter last name:");
                String lastName = textUI.get();
                System.out.println("Please enter age:");
                int age = textUI.getInteger();

                if(age < 18){
                    System.out.println("Apologies, but you are not old enough to register on this application");
                }
                else {
                    user = new Person(email, password, firstName, lastName, age);
                    menu();
                }
                break;
            case 2:
                guestMenu();
                break;
        }
    }

    private void menu() {
        boolean on = true;
        while(on){
            String[] options = {"Search", "User details", "Surprise me","Quiz", "Quit"};
            int optionChoice = textUI.select("Choose an option", options, "");
            switch(optionChoice){
                case 0:
                    userSearch();
                    break;
                case 1:
                    printUserDetails();     //prints user details
                    System.out.println("Press enter to continue");
                    textUI.get();
                    break;
                case 2:
                    Roulette roulette = new Roulette();
                    roulette.run();
                    break;
                case 3:
                    quiz.quizMenu();
                    break;
                case 4:
                    database.saveData(user);
                    System.out.println("Thanks for using Drunk Drunk go");
                    on = false;
                    break;


            }
        }

    }
    private void guestMenu() {
        boolean on = true;
        while(on){
            String[] options = {"Search", "Surprise me", "Quiz", "Quit"};
            int optionChoice = textUI.select("Choose an option", options, "");
            switch(optionChoice){
                case 0:
                    guestSearch();
                    break;
                case 1:
                    Roulette roulette = new Roulette(); //surprise me feature which will give a random type of alcohol.
                    roulette.run();
                    break;
                case 2:
                    quiz.quizMenu();
                    break;
                case 3:
                    System.out.println("Thanks for using Drunk Drunk go");
                    on = false;
                    break;
            }
        }
    }
    public void userSearch(){
        boolean check = true;
        boolean alcoholChoiceCheck = true;
        String nameChoice = "%";
        String typeChoice = "%";
        int priceChoice = 1;
        String notesChoice = "%";
        String countryChoice = "%";
        String like = "Like";
        boolean beerChoice = false;
        boolean wineChoice = false;
        boolean spiritChoice = false;
        ArrayList<Alcohol> alcoholList = new ArrayList<>();

        String[] alcoholChoices = {"beer", "wine", "spirit","Continue to search criteria"};
        while(alcoholChoiceCheck){
            int alcoholToSearchFor = textUI.select("Please select which alcohol you would like to search for", alcoholChoices, "");
            switch (alcoholToSearchFor){
                case 0:
                    System.out.println("You have selected to search for beer");
                    beerChoice = true;
                    break;
                case 1:
                    System.out.println("You have selected to search for wine");
                    wineChoice = true;
                    break;
                case 2:
                    System.out.println("You have selected to search for spirits");
                    spiritChoice = true;
                    break;
                case 3:
                    alcoholChoiceCheck = false;
                    break;
            }
        }
        try{
            connection = DriverManager.getConnection(JdbcUrl, username, password);


            while(check) {
                String[] searchChoices = {"Name","Type","Price", "Notes", "Country","Complete Search"};
                int searchChoice = textUI.select("Please select search criteria(s)", searchChoices, "");
                switch (searchChoice) {

                    case 0:
                        System.out.println("Please enter the name of the alcohol you would like to search for");
                        nameChoice = textUI.get();
                        nameChoice = "%" + nameChoice + "%";
                        break;
                    case 1:
                        System.out.println("Please enter the type of alcohol you would like to search for e.g. white wine or pilsner");
                        typeChoice = textUI.get();
                        typeChoice = "%" + typeChoice + "%";
                        break;
                    case 2:
                        System.out.println("please enter a max amount of money you would like to spend");
                        priceChoice = textUI.getInteger();
                        break;
                    case 3:
                        System.out.println("Please enter notes you would like to search for e.g. oak, berries or sweet");
                        notesChoice = textUI.get();
                        notesChoice = "%" + notesChoice + "%";
                        break;
                    case 4:
                        System.out.println("Please enter which country you would like to search for");
                        countryChoice = textUI.get();
                        countryChoice = "%" + countryChoice + "%";
                        break;
                    case 5:
                       // PreparedStatement statement1 = connection.prepareStatement("SELECT * FROM iceprojekt.beer WHERE Name ? '%?%' && " +
                               // "Type = '?' && Price <= ? && Notes ? '%?%' && Country ? '%?%';");

                        PreparedStatement statement1 = null;
                        ResultSet resultSet1 = null;
                        if(beerChoice) {
                            statement1 = connection.prepareStatement("SELECT * FROM iceprojekt.beer WHERE Name Like ? && Type Like ? && Price >= ? && Notes Like ? && Country Like ?");

                            statement1.setString(1, nameChoice);
                            statement1.setString(2, typeChoice);
                            statement1.setInt(3, priceChoice);
                            statement1.setString(4, notesChoice);
                            statement1.setString(5, countryChoice);
                            resultSet1 = statement1.executeQuery();

                        }

                        //PreparedStatement statement2 = connection.prepareStatement("SELECT * FROM iceprojekt.wine WHERE Name ? '%?%' && " +
                          //      "Type = '?' && Price <= ? && Notes ? '%?%' && Country ? '%?%'");
                        PreparedStatement statement2 = null;
                        ResultSet resultSet2 = null;

                        if(wineChoice) {
                            statement2 = connection.prepareStatement("SELECT * FROM iceprojekt.wine WHERE Name Like ? && Type Like ? && Price >= ? && Notes Like ? && Country Like ?");

                            statement2.setString(1, nameChoice);
                            statement2.setString(2, typeChoice);
                            statement2.setInt(3, priceChoice);
                            statement2.setString(4, notesChoice);
                            statement2.setString(5, countryChoice);
                            resultSet2 = statement2.executeQuery();

                        }

                        //PreparedStatement statement3 = connection.prepareStatement("SELECT * FROM iceprojekt.spirits WHERE Name ? '%?%' && " +
                          //      "Type = '?' && Price <= ? && Notes ? '%?%' && Country ? '%?%'");
                        PreparedStatement statement3 = null;
                        ResultSet resultSet3 = null;
                        if(spiritChoice) {
                            statement3 = connection.prepareStatement("SELECT * FROM iceprojekt.spirits WHERE Name Like ? && Type Like ? && Price >= ? && Notes Like ? && Country Like ?");
                            statement3.setString(1, nameChoice);
                            statement3.setString(2, typeChoice);
                            statement3.setInt(3, priceChoice);
                            statement3.setString(4, notesChoice);
                            statement3.setString(5, countryChoice);
                            resultSet3 = statement3.executeQuery();

                        }





                        int counter = 1;
                        String beerToPrint = " ";
                        if(beerChoice) {
                            while (resultSet1.next()){
                                System.out.println("Beer number: " + counter);
                                String beerName = resultSet1.getString("Name");
                                String beerType = resultSet1.getString("Type");
                                int beerPrice = resultSet1.getInt("Price");
                                String beerNotes = resultSet1.getString("Notes");
                                String beerCountry = resultSet1.getString("Country");


                                Beer beerToAddToFavorite = new Beer(beerName, beerType, beerPrice, beerNotes, beerCountry);
                                alcoholList.add(beerToAddToFavorite);
                                beerToPrint = "> Name: " + beerName + "\n" + "> Type: " + beerType + "\n" + "> Price: "
                                        + beerPrice + " DKK/L" + "\n" + "> Notes: " + beerNotes + "\n" + "> Country: " + beerCountry;

                                System.out.println(beerToPrint + "\n");
                                counter++;
                            }
                        }
                        String wineToPrint = " ";
                        if(wineChoice) {
                            while (resultSet2.next()) {

                                System.out.println("Wine number: " + counter);
                                String wineName = resultSet2.getString("Name");
                                String wineType = resultSet2.getString("Type");
                                int winePrice = resultSet2.getInt("Price");
                                String wineNotes = resultSet2.getString("Notes");
                                String wineCountry = resultSet2.getString("Country");

                                Wine wineToAddToFavorite = new Wine(wineName, wineType, winePrice, wineNotes, wineCountry);
                                alcoholList.add(wineToAddToFavorite);

                                wineToPrint = "> Name: " + wineName + "\n" + "> Type: " + wineType + "\n" + "> Price: "
                                        + winePrice+ " DKK/L" + "\n" + "> Notes: " + wineNotes + "\n" + "> Country: " + wineCountry;

                                System.out.println(wineToPrint + "\n");
                                counter++;
                            }
                        }

                        String spiritToPrint = " ";
                        if(spiritChoice) {
                            while (resultSet3.next()) {

                                System.out.println("Beer number: " + counter);

                                String spiritName = resultSet3.getString("Name");
                                String spiritType = resultSet3.getString("Type");
                                int spiritPrice = resultSet3.getInt("Price");
                                String spiritNotes = resultSet3.getString("Notes");
                                String spiritCountry = resultSet3.getString("Country");

                                Spirit spiritToAddToFavorite = new Spirit(spiritName, spiritType, spiritPrice, spiritNotes, spiritCountry);
                                alcoholList.add(spiritToAddToFavorite);

                                spiritToPrint = "> Name: " + spiritName + "\n" + "> Type: " + spiritType + "\n" + "> Price: "
                                        + spiritPrice + " DKK/L"+ "\n" + "> Notes: " + spiritNotes + "\n" + "> Country: " + spiritCountry;

                                System.out.println(spiritToPrint+ "\n");
                                counter++;
                            }
                        }

                        String[] favoriteArr = {"Yes","no"};
                        int favoriteChoice = textUI.select("Would you like to add any of these alcohol to your favorite?",favoriteArr,"");

                        switch (favoriteChoice){
                            case 0:
                                ArrayList<String> alcoholListNames = new ArrayList<>();
                                for(Alcohol a: alcoholList){
                                    alcoholListNames.add(a.getName());
                                }
                                int alcoholToFavorite = textUI.select("Please select the name of the alcohol you would like to add", alcoholListNames,"");
                                Alcohol alcoholSave = alcoholList.get(alcoholToFavorite);
                                if(alcoholSave instanceof Beer){
                                    user.setBeer((Beer) alcoholSave);
                                }
                                if(alcoholSave instanceof Wine){
                                    user.setWine((Wine) alcoholSave);

                                }
                                if(alcoholSave instanceof Spirit){
                                    user.setSpirit((Spirit) alcoholSave);
                                }
                                break;
                            case 1:
                                System.out.println("Press enter to continue back to main menu" + "DENNE SKAL MÅSKE FJERNES");
                                textUI.get();
                                break;
                        }

                        System.out.println("Press enter to continue");
                        textUI.get();
                        check = false;
                        break;
                }

            }
        }
        catch(SQLException e){
           e.printStackTrace();

        }
    }

    public void guestSearch(){
        boolean check = true;
        boolean alcoholChoiceCheck = true;
        String nameChoice = "%";
        String typeChoice = "%";
        int priceChoice = 1;
        String notesChoice = "%";
        String countryChoice = "%";
        String like = "Like";
        boolean beerChoice = false;
        boolean wineChoice = false;
        boolean spiritChoice = false;
        ArrayList<Alcohol> alcoholList = new ArrayList<>();

        String[] alcoholChoices = {"beer", "wine", "spirit","Continue to search criteria"};
        while(alcoholChoiceCheck){
            int alcoholToSearchFor = textUI.select("Please select which alcohol you would like to search for", alcoholChoices, "");
            switch (alcoholToSearchFor){
                case 0:
                    System.out.println("You have selected to search for beer");
                    beerChoice = true;
                    break;
                case 1:
                    System.out.println("You have selected to search for wine");
                    wineChoice = true;
                    break;
                case 2:
                    System.out.println("You have selected to search for spirits");
                    spiritChoice = true;
                    break;
                case 3:
                    alcoholChoiceCheck = false;
                    break;
            }
        }
        try{
            connection = DriverManager.getConnection(JdbcUrl, username, password);


            while(check) {
                String[] searchChoices = {"Name","Type","Price", "Notes", "Country","Complete Search"};
                int searchChoice = textUI.select("Please select search criteria(s)", searchChoices, "");
                switch (searchChoice) {

                    case 0:
                        System.out.println("Please enter the name of the alcohol you would like to search for");
                        nameChoice = textUI.get();
                        nameChoice = "%" + nameChoice + "%";
                        break;
                    case 1:
                        System.out.println("Please enter the type of alcohol you would like to search for e.g. white wine or pilsner");
                        typeChoice = textUI.get();
                        typeChoice = "%" + typeChoice + "%";
                        break;
                    case 2:
                        System.out.println("please enter a max amount of money you would like to spend");
                        priceChoice = textUI.getInteger();
                        break;
                    case 3:
                        System.out.println("Please enter notes you would like to search for e.g. oak, berries or sweet");
                        notesChoice = textUI.get();
                        notesChoice = "%" + notesChoice + "%";
                        break;
                    case 4:
                        System.out.println("Please enter which country you would like to search for");
                        countryChoice = textUI.get();
                        countryChoice = "%" + countryChoice + "%";
                        break;
                    case 5:
                        // PreparedStatement statement1 = connection.prepareStatement("SELECT * FROM iceprojekt.beer WHERE Name ? '%?%' && " +
                        // "Type = '?' && Price <= ? && Notes ? '%?%' && Country ? '%?%';");

                        PreparedStatement statement1 = null;
                        ResultSet resultSet1 = null;
                        if(beerChoice) {
                            statement1 = connection.prepareStatement("SELECT * FROM iceprojekt.beer WHERE Name Like ? && Type Like ? && Price >= ? && Notes Like ? && Country Like ?");

                            statement1.setString(1, nameChoice);
                            statement1.setString(2, typeChoice);
                            statement1.setInt(3, priceChoice);
                            statement1.setString(4, notesChoice);
                            statement1.setString(5, countryChoice);
                            resultSet1 = statement1.executeQuery();

                        }

                        //PreparedStatement statement2 = connection.prepareStatement("SELECT * FROM iceprojekt.wine WHERE Name ? '%?%' && " +
                        //      "Type = '?' && Price <= ? && Notes ? '%?%' && Country ? '%?%'");
                        PreparedStatement statement2 = null;
                        ResultSet resultSet2 = null;

                        if(wineChoice) {
                            statement2 = connection.prepareStatement("SELECT * FROM iceprojekt.wine WHERE Name Like ? && Type Like ? && Price >= ? && Notes Like ? && Country Like ?");

                            statement2.setString(1, nameChoice);
                            statement2.setString(2, typeChoice);
                            statement2.setInt(3, priceChoice);
                            statement2.setString(4, notesChoice);
                            statement2.setString(5, countryChoice);
                            resultSet2 = statement2.executeQuery();

                        }

                        //PreparedStatement statement3 = connection.prepareStatement("SELECT * FROM iceprojekt.spirits WHERE Name ? '%?%' && " +
                        //      "Type = '?' && Price <= ? && Notes ? '%?%' && Country ? '%?%'");
                        PreparedStatement statement3 = null;
                        ResultSet resultSet3 = null;
                        if(spiritChoice) {
                            statement3 = connection.prepareStatement("SELECT * FROM iceprojekt.spirits WHERE Name Like ? && Type Like ? && Price >= ? && Notes Like ? && Country Like ?");
                            statement3.setString(1, nameChoice);
                            statement3.setString(2, typeChoice);
                            statement3.setInt(3, priceChoice);
                            statement3.setString(4, notesChoice);
                            statement3.setString(5, countryChoice);
                            resultSet3 = statement3.executeQuery();

                        }





                        int counter = 1;
                        String beerToPrint = " ";
                        if(beerChoice) {
                            while (resultSet1.next()){
                                System.out.println("Beer number: " + counter);
                                String beerName = resultSet1.getString("Name");
                                String beerType = resultSet1.getString("Type");
                                int beerPrice = resultSet1.getInt("Price");
                                String beerNotes = resultSet1.getString("Notes");
                                String beerCountry = resultSet1.getString("Country");


                                Beer beerToAddToFavorite = new Beer(beerName, beerType, beerPrice, beerNotes, beerCountry);
                                alcoholList.add(beerToAddToFavorite);
                                beerToPrint = "> Name: " + beerName + "\n" + "> Type: " + beerType + "\n" + "> Price: "
                                        + beerPrice + " DKK/L" + "\n" + "> Notes: " + beerNotes + "\n" + "> Country: " + beerCountry;

                                System.out.println(beerToPrint + "\n");
                                counter++;
                            }
                        }
                        String wineToPrint = " ";
                        if(wineChoice) {
                            while (resultSet2.next()) {

                                System.out.println("Wine number: " + counter);
                                String wineName = resultSet2.getString("Name");
                                String wineType = resultSet2.getString("Type");
                                int winePrice = resultSet2.getInt("Price");
                                String wineNotes = resultSet2.getString("Notes");
                                String wineCountry = resultSet2.getString("Country");

                                Wine wineToAddToFavorite = new Wine(wineName, wineType, winePrice, wineNotes, wineCountry);
                                alcoholList.add(wineToAddToFavorite);

                                wineToPrint = "> Name: " + wineName + "\n" + "> Type: " + wineType + "\n" + "> Price: "
                                        + winePrice+ " DKK/L" + "\n" + "> Notes: " + wineNotes + "\n" + "> Country: " + wineCountry;

                                System.out.println(wineToPrint + "\n");
                                counter++;
                            }
                        }

                        String spiritToPrint = " ";
                        if(spiritChoice) {
                            while (resultSet3.next()) {

                                System.out.println("Beer number: " + counter);

                                String spiritName = resultSet3.getString("Name");
                                String spiritType = resultSet3.getString("Type");
                                int spiritPrice = resultSet3.getInt("Price");
                                String spiritNotes = resultSet3.getString("Notes");
                                String spiritCountry = resultSet3.getString("Country");

                                Spirit spiritToAddToFavorite = new Spirit(spiritName, spiritType, spiritPrice, spiritNotes, spiritCountry);
                                alcoholList.add(spiritToAddToFavorite);

                                spiritToPrint = "> Name: " + spiritName + "\n" + "> Type: " + spiritType + "\n" + "> Price: "
                                        + spiritPrice + " DKK/L"+ "\n" + "> Notes: " + spiritNotes + "\n" + "> Country: " + spiritCountry;

                                System.out.println(spiritToPrint+ "\n");
                                counter++;
                            }
                        }
                        System.out.println("Press enter to continue");
                        textUI.get();
                        check = false;
                        break;
                }

            }
        }
        catch(SQLException e){
            e.printStackTrace();

        }
    }

    public void printUserDetails(){
        System.out.println("> Name: " + user.getFirstName() + " " + user.getLastName() + " Age: " + user.getAge());
        System.out.println("> E-mail: " + user.getEmail());
        System.out.println("> Password: " + user.getPassword());
        System.out.println("> Favorite beer: " + user.getBeer());
        System.out.println("> Favorite wine: " + user.getWine());
        System.out.println("> Favorite spirit: " + user.getSpirit());
    }
}