package game.QuestionTypes;

public class FinishRoom extends Question {

    String congratsMessage;

    public FinishRoom(){
        super("YouWin");
        this.congratsMessage = "YouWin";
    }

    public FinishRoom(String finishMessage, String congratsMessage){
        super(finishMessage);
        this.congratsMessage = congratsMessage;
        answered=true;
    }

    
    private String questionString;
    @Override
    public String toString() {
        if(questionString!=null){
            return questionString;
        }
        return createString();
    }

    public String createString(){
        String finishMesssageString = createFinishMessageString();
        String congratsMesssageString = createCongratsMessageString();
        String s =
        // question header
        "#"+ "=".repeat(STRING_LENGTH) +"#\n"+
        finishMesssageString+
        "#"+ "-".repeat(STRING_LENGTH)+"#\n"+
        congratsMesssageString+
        "#"+ "=".repeat(STRING_LENGTH) +"#\n";

        questionString = s;
        return s;
    }

    private String createFinishMessageString(){
        String s = "|";
        for (int i = 0; i < question.length(); i++) {
            if(i>=STRING_LENGTH-3){
                s+="...";
                break;
            }
            s+=question.charAt(i);
        }
        if(question.length()<STRING_LENGTH-3){
            s+= " ".repeat((STRING_LENGTH)-question.length());
        }
        s+="|\n";
        return s;
    }

    private String createCongratsMessageString(){
        String s = "|";
        for (int i = 0; i < congratsMessage.length(); i++) {
            if(i>=STRING_LENGTH-3){
                s+="...";
                break;
            }
            s+=congratsMessage.charAt(i);
        }
        if(congratsMessage.length()<STRING_LENGTH-3){
            s+= " ".repeat((STRING_LENGTH)-congratsMessage.length());
        }
        s+="|\n";
        return s;
    }

    @Override
    public boolean isCorrect(String s) {
        return true;
    }

}
