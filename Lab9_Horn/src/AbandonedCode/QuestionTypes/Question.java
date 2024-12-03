package AbandonedCode.QuestionTypes;

public abstract class Question {

    public static final int STRING_LENGTH=120;
    public static final int HEADER_LENGTH=50;

    String question;
    public boolean answered;

    public Question(String question){
        this.question = question;
        answered=false;
    }
    
    public String getHeader(){
        return "";
    }

    public abstract boolean isCorrect(String s);
}