public class Question {

    //Fields
    private String[] answers = new String[4];
    private TextUI textUI = new TextUI();
    private String questionToBeAnswered;
    private int correctAnswer;


    //Constructor
    public Question(String questionToBeAnswered, String[] answers, int correctAnswer){
        this.questionToBeAnswered = questionToBeAnswered;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    //Asks a question and tells whether it was correct or incorrect
    public void askQuestion(){

        System.out.println(questionToBeAnswered);
        int guess = textUI.select("",answers,"");
        if(guess == correctAnswer){
            System.out.println("Your answer was correct!");
        } else System.out.println("Your answer was incorrect.");

    }
}
