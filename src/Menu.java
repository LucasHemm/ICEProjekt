public class Menu {

//author Lucas

    TextUI textUI = new TextUI();
    //DatabaseIO database = new Database();********************
    //Person user;**********************************
    public void Menu(){

    }

    //method that runs the whole program
    public void run() {

        //First menu with 3 choices - Login , Create profile, Login as guest.
        String[] firstMenuChoices = {"Login", "Create Profile", "Login as guest"};
        int firstChoice = textUI.select("Please select a way to load and save your tournament", firstMenuChoices, "");

        switch (firstChoice){
            case 0:
                //login with a profile - check with E-mail and Password and make sure they login to the correct acccount
                //user = database.login();**************''
                 break;
            case 1:
                //create a user - make sure that the person is 18 or above otherwise the
                // program will not let you create and account.
                break;
            case 2:
                guestMenu();
                break;
        }
    }



    private void menu() {
        boolean on = true;

        while(on){
            String[] options = {"Search", "User details","Surprise me", "Quit"};
            int optionChoice = textUI.select("Choose an option", options, "");

            switch(optionChoice){

                case 0:
                    // this should then go into the search() menu;
                    break;

                case 1:
                    // this should use a printUserDetails();
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

    }



}
