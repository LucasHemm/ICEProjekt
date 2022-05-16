import java.util.LinkedList;
import java.util.Queue;

public class Quiz {

    boolean check = true;
    Queue<Question> beerQuestions = new LinkedList<>();
    Queue<Question> wineQuestions = new LinkedList<>();
    Queue<Question> spiritQuestions = new LinkedList<>();
    TextUI textUI = new TextUI();


    public Quiz(){
        // BEERS

        String[] beerArray1 = {"Ignore panda awareness","Indian pale ale", "Indian proud ale", "Indonesian pale ale"};
        Question beerQuestion1 = new Question("What does IPA stand for?",beerArray1,1);
        beerQuestions.add(beerQuestion1);

        String[] beerArray2 = {"Belgium", "Denmark", "Germany", "Czech Republic"};
        Question beerQuestion2 = new Question("Which country consumes the most beer per capita?",beerArray2,3);
        beerQuestions.add(beerQuestion2);

        String[] beerArray3 = {"Duff Beer", "Huff Beer", "Tough Beer", "Stuff Beer"};
        Question beerQuestion3 = new Question("What is the name of the beer commonly consumed \n in popular cartoon The Simpsons?",beerArray3,0);
        beerQuestions.add(beerQuestion3);

        // WINE

        String[] wineArray1 = {"Pink","Red", "White","Green"};
        Question wineQuestion1 = new Question("What kind of grape is Carbernet Sauvignon?",wineArray1,1);
        beerQuestions.add(wineQuestion1);

        String[] wineArray2 = {"Marcusiozo Lucazo Matiazo vino", "Pinot Grigio", "Riesling","Chardonnay"};
        Question wineQuestion2 = new Question("Which wine is often described as buttery?",wineArray2,3);
        beerQuestions.add(wineQuestion2);

        String[] wineArray3 = {"France","Italy","Argentina","Austria"};
        Question wineQuestion3 = new Question("Malbec originated in what country?",wineArray3,0);
        beerQuestions.add(wineQuestion3);
        // SPIRITS

        String[] spiritArray1 = {"2% sugar content", "40% alcohol content", "20% alcohol content", "10% alcohol content"};
        Question spiritQuestion1 = new Question("A spirit is defined as a distilled alcoholic beverage that has at least: ",spiritArray1,2);
        beerQuestions.add(spiritQuestion1);

        String[] spiritArray2 = {"Vodka", "Cointreau", "Tequila", "Cognac"};
        Question spiritQuestion2 = new Question("Which of the following is NOT classified as a spirit?",spiritArray2,1);
        beerQuestions.add(spiritQuestion2);

        String[] spiritArray3 = {"The flavour comes from Juniper berries", "It originated in Holland", "London Dry Gin has to be made in London", "It is used in Martinis"};
        Question spiritQuestion3 = new Question("Which of the following statements is NOT true about gin?",spiritArray3,2);
        beerQuestions.add(spiritQuestion3);


    }

    public void quizMenu(){
        while(check){
            String[] choices = {"Beer question", "Wine question", "Spirit question", "Return to main menu"};
            int choice = textUI.select("Please select an option", choices,"");

            switch(choice){

                case 0:
                    runBeer();
                    break;
                case 1:
                    runWine();
                    break;
                case 2:
                    runSpirit();
                    break;
                case 3:
                    check = false;
                    System.out.println("Thanks for trying the quiz.");
                    break;
            }
        }
    }


    public void runBeer(){


    }

    public void runWine(){

    }

    public void runSpirit(){

    }




}
