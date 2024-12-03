package AbandonedCode.QuestionTypes;

import game.Application;

public class OpenEnded extends Question{
    String answerFormat;
    String answer;

    public OpenEnded(){
        super("Question");
        this.answerFormat = "Format()";
        this.answer = "answer";
    }

    @Override
    public String toString() {
        return "";
    }

    @Override
    public boolean isCorrect(String userAnswer) {
        return false;
    }
}
