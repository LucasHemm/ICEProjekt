import java.sql.*;

public class Menu {

//author Lucas

    TextUI textUI = new TextUI();
    //DatabaseIO database = new Database();********************
    //Person user;**********************************

    private String JdbcUrl = "jdbc:mysql://localhost/world?" + "autoReconnect=true&useSSL=false";
    private String username = "root";
    private String password = "*******";
    private Connection connection = null;

    public void Menu(){

    }

    //method that runs the whole program
    public void run() {
        //First menu with 3 choices - Login , Create profile, Login as guest.
        String[] firstMenuChoices = {"Login", "Create Profile", "Login as guest"};
        int firstChoice = textUI.select("Please select a way to load and save your tournament", firstMenuChoices, "");

        switch (firstChoice){
            case 0:
                user = database.login();
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
            String[] options = {"Search", "User details", "Surprise me", "Quit"};
            int optionChoice = textUI.select("Choose an option", options, "");

            switch(optionChoice){

                case 0:
                    // this should then go into the search() menu;
                    break;

                case 1:
                    printUserDetails();     //prints user details
                    System.out.println("Press enter to continue");
                    textUI.get();
                    break;

                case 2:
                    //Roulette roulette = new Roulette(); surprise me feature which will give a random type of alcohol.
                    break;
                case 3:
                    //this sets the on to false which will in turn make sure that the switch will break which will then
                    //turn of the program
                    System.out.println("Thanks for using Drunk Drunk go");
                    on = false;
                    break;


            }


        }

    }
    private void guestMenu() {
        boolean on = true;

        while(on){
            String[] options = {"Search", "Surprise me", "Quit"};
            int optionChoice = textUI.select("Choose an option", options, "");

            switch(optionChoice){

                case 0:
                    // this should then go into the search() menu;
                    break;

                case 1:
                    //Roulette roulette = new Roulette(); surprise me feature which will give a random type of alcohol.
                    break;
                case 2:
                    //this sets the on to false which will in turn make sure that the switch will break which will then
                    //turn of the program
                    System.out.println("Thanks for using Drunk Drunk go");
                    on = false;
                    break;
            }
        }

    }

    public void search(){
        boolean check = true;
        boolean alcoholChoiceCheck = true;
        String nameChoice = null;
        String typeChoice = null;
        int priceChoice = 0;
        String notesChoice = null;
        String countrychoice = null;
        String like = "Like";

        boolean beerChoice = false;
        boolean wineChoice = false;
        boolean spiritChoice = false;


        String[] alcoholChoices = {"beer", "wine", "spirit","Continue to search criteria"};
        int alcoholToSearchFor = textUI.select("Please select which alcohol you would like to search for", alcoholChoices, "");
        while(alcoholChoiceCheck){
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
                        break;
                    case 1:
                        System.out.println("Please enter the type of alcohol you would like to search for e.g. white wine or pilsner");
                        typeChoice = textUI.get();
                        break;
                    case 2:
                        System.out.println("please enter a max amount of money you would like to spend");
                        priceChoice = textUI.getInteger();
                        break;
                    case 3:
                        System.out.println("Please enter notes you would like to search for e.g. oak, berries or sweet");
                        notesChoice = textUI.get();
                        break;
                    case 4:
                        System.out.println("Please enter which country you would like to search for");
                        countrychoice = textUI.get();
                        break;
                    case 5:
                        if(nameChoice == null){
                            nameChoice = "IS NOT NULL";
                            like = "";
                        }
                        if(typeChoice == null){
                            typeChoice = "IS NOT NULL";
                        }
                        if(notesChoice == null){
                            notesChoice = "IS NOT NULL";
                        }
                        if(countrychoice == null){
                            countrychoice = "IS NOT NULL";
                        }



                        PreparedStatement statement1 = connection.prepareStatement("SELECT * FROM iceprojekt.beer WHERE Name ? '%?%' && " +
                                "Type = '?' && Price <= ? && Notes ? '%?%' && Country ? '%?%'; ");
                        if(beerChoice) {
                            statement1.setString(1, like);
                            statement1.setString(2, nameChoice);
                            statement1.setString(3, typeChoice);
                            statement1.setInt(4, priceChoice);
                            statement1.setString(5, like);
                            statement1.setString(6, notesChoice);
                            statement1.setString(7, like);
                            statement1.setString(8, countrychoice);
                        }

                        PreparedStatement statement2 = connection.prepareStatement("SELECT * FROM iceprojekt.wine WHERE Name ? '%?%' && " +
                                "Type = '?' && Price <= ? && Notes ? '%?%' && Country ? '%?%'; ");
                        if(wineChoice) {
                            statement2.setString(1, like);
                            statement2.setString(2, nameChoice);
                            statement2.setString(3, typeChoice);
                            statement2.setInt(4, priceChoice);
                            statement2.setString(5, like);
                            statement2.setString(6, notesChoice);
                            statement2.setString(7, like);
                            statement2.setString(8, countrychoice);
                        }

                        PreparedStatement statement3 = connection.prepareStatement("SELECT * FROM iceprojekt.spirits WHERE Name ? '%?%' && " +
                                "Type = '?' && Price <= ? && Notes ? '%?%' && Country ? '%?%'; ");
                        if(spiritChoice) {
                            statement3.setString(1, like);
                            statement3.setString(2, nameChoice);
                            statement3.setString(3, typeChoice);
                            statement3.setInt(4, priceChoice);
                            statement3.setString(5, like);
                            statement3.setString(6, notesChoice);
                            statement3.setString(7, like);
                            statement3.setString(8, countrychoice);
                        }

                        ResultSet resultSet1 = statement1.executeQuery();
                        ResultSet resultSet2 = statement1.executeQuery();
                        ResultSet resultSet3 = statement1.executeQuery();


                        while (resultSet1.next()) {

                            String beerName = resultSet1.getString("Name");
                            String beerType = resultSet1.getString("numOfPlayers");
                            String beerPrice = resultSet1.getString("points");
                            String goalDiff = resultSet1.getString("goalDiff");

                            String teamBuild = teamName + ", " + numOfPlayers + ", " + points + ", " + goalDiff;


                            teamData[counter] = teamBuild;

                            counter++;
                        }


                        break;



                }

            }



        }
        catch(SQLException e){
           e.printStackTrace();

        }



    }

    public void printUserDetails(){

        System.out.println("> Name: " + user.getFirstName() + " " + user.getLastName + " Age: " + user.getAge());
        System.out.println("> E-mail: " + user.getEmail());
        System.out.println("> Password: " + user.getPassword);
        System.out.println("> Favorite beer: " + user.getBeer());
        System.out.println("> Favorite wine: " + user.getWine());
        System.out.println("> Favorite spirit: " + user.getSpirit());

    }

    }



}
