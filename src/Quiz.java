import java.util.LinkedList;
import java.util.Queue;

public class Quiz {

    private boolean check = true;
    private Queue<Question> beerQuestions = new LinkedList<>();
    private Queue<Question> wineQuestions = new LinkedList<>();
    private Queue<Question> spiritQuestions = new LinkedList<>();
    private TextUI textUI = new TextUI();


    public Quiz(){ // when a quiz object is created it will immediatly create the different questions the user can get asked.
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
        wineQuestions.add(wineQuestion1);

        String[] wineArray2 = {"Lucazo Marcusiozo Matiazo vino", "Pinot Grigio", "Riesling","Chardonnay"};
        Question wineQuestion2 = new Question("Which wine is often described as buttery?",wineArray2,3);
        wineQuestions.add(wineQuestion2);

        String[] wineArray3 = {"France","Italy","Argentina","Austria"};
        Question wineQuestion3 = new Question("Malbec originated in what country?",wineArray3,0);
        wineQuestions.add(wineQuestion3);
        // SPIRITS

        String[] spiritArray1 = {"2% sugar content", "40% alcohol content", "20% alcohol content", "10% alcohol content"};
        Question spiritQuestion1 = new Question("A spirit is defined as a distilled alcoholic beverage that has at least: ",spiritArray1,2);
        spiritQuestions.add(spiritQuestion1);

        String[] spiritArray2 = {"Vodka", "Cointreau", "Tequila", "Cognac"};
        Question spiritQuestion2 = new Question("Which of the following is NOT classified as a spirit?",spiritArray2,1);
        spiritQuestions.add(spiritQuestion2);

        String[] spiritArray3 = {"The flavour comes from Juniper berries", "It originated in Holland", "London Dry Gin has to be made in London", "It is used in Martinis"};
        Question spiritQuestion3 = new Question("Which of the following statements is NOT true about gin?",spiritArray3,2);
        spiritQuestions.add(spiritQuestion3);


    }

    public void quizMenu(){ // this is the quiz menu where the user can choose what types of questions to answer
        while(check){
            String[] choices = {"Beer question", "Wine question", "Spirit question", "Return to main menu"};
            int choice = textUI.select("Please select a question", choices,"");

            switch(choice){

                case 0: // asks a beer question
                    runBeer();
                    break;
                case 1: // asks a wine questions
                    runWine();
                    break;
                case 2: // asks a spirit question
                    runSpirit();
                    break;
                case 3: // goes back to main menu
                    check = false;
                    System.out.println("Thanks for trying the quiz.");
                    break;
            }
        }
    }


    private void runBeer(){ //this asks the head of the beer questions queue.
        Question tmpQuestion = beerQuestions.remove(); // the question gets removed and stored
        tmpQuestion.askQuestion(); // the question is printed to the user where they then can guess.
        beerQuestions.add(tmpQuestion); //the former head of the queue is then added back into the queue at the start.
        System.out.println("Press enter to continue");
        textUI.get();
    }

    private void runWine(){ //this asks the head of the wine questions queue.
        Question tmpQuestion = wineQuestions.remove(); // the question gets removed and stored
        tmpQuestion.askQuestion(); // the question is printed to the user where they then can guess.
        wineQuestions.add(tmpQuestion); //the former head of the queue is then added back into the queue at the start.
        System.out.println("Press enter to continue");
        textUI.get();
    }

    private void runSpirit(){ //this asks the head of the spirit questions queue.
        Question tmpQuestion = spiritQuestions.remove(); // the question gets removed and stored
        tmpQuestion.askQuestion(); // the question is printed to the user where they then can guess.
        spiritQuestions.add(tmpQuestion); //the former head of the queue is then added back into the queue at the start.
        System.out.println("Press enter to continue");
        textUI.get();
    }




}
