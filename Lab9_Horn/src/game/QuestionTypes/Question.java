package game.QuestionTypes;

public abstract class Question {

    public static final int STRING_LENGTH=120;
    public static final int HEADER_LENGTH=50;

    String question;
    public boolean answered;

    public Question(String question){
        this.question = question;
        answered=false;
    }

    private String questionHeader;
    public String getHeader(){
        if(questionHeader!=null){
            return questionHeader;
        }
        return createHeader();
    }

    public String createHeader(){
        String s ="";
        for (int i = 0; i < question.length(); i++) {
            if(i>=HEADER_LENGTH-3){
                s+="...";
                break;
            }
            s+=question.charAt(i);
        }
        if(question.length()<HEADER_LENGTH-3){
            s+= " ".repeat((HEADER_LENGTH)-question.length());
        }

        questionHeader = s;
        return s;
    }

    public abstract boolean isCorrect(String s);
}