package AbandonedCode.QuestionTypes;

import game.Application;

public class MultipleChoice extends Question{
    String[] answers;
    int correctAnswer;

    public MultipleChoice(){
        super("Question");
        this.answers = new String[]{"Answer"};
        this.correctAnswer = 1;
    }

    @Override
    public String toString() {

        return  "";
    }

    @Override
    public boolean isCorrect(String userAnswer) {
        return false;
    }
}
