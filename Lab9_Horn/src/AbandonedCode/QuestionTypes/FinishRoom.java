package AbandonedCode.QuestionTypes;

public class FinishRoom extends Question {

    String congratsMessage;

    public FinishRoom(){
        super("YouWin");
        this.congratsMessage = "YouWin";
    }

    @Override
    public String toString() {
        return "";
    }

    @Override
    public boolean isCorrect(String s) {
        return true;
    }

}
