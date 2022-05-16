public class Question {

    private String[] answers = new String[4];
    private TextUI textUI = new TextUI();
    private String questionToBeAnswered;
    private int correctAnswer;

    public Question(String questionToBeAnswered, String[] answers, int correctAnswer){
        this.questionToBeAnswered = questionToBeAnswered;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    public void askQuestion(){

        System.out.println(questionToBeAnswered);
        int guess = textUI.select("",answers,"");
        if(guess == correctAnswer){
            System.out.println("Your answer was correct!");
        } else System.out.println("Your answer was incorrect.");

    }





}
