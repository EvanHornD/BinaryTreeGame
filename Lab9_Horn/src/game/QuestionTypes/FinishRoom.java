package game.QuestionTypes;

public class FinishRoom extends Question {

    String congratsMessage;

    public FinishRoom(){
        super("YouWin");
        this.congratsMessage = "YouWin";
    }

    public FinishRoom(String finishMessage, String congratsMessage){
        super(congratsMessage);
        this.congratsMessage = congratsMessage;
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
        String s =
        // question header
        "#"+ "=".repeat(STRING_LENGTH) +"#\n"+
        "|";
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
        s+="|\n"+"#"+ "-".repeat(STRING_LENGTH) +"#\n";

        //answer format

        //answer space

        s+="#"+ "=".repeat(STRING_LENGTH) +"#\n";

        questionString = s;
        return s;
    }

}
