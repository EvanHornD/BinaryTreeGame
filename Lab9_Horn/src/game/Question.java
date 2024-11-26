package game;

public class Question {

    public static final int STRING_LENGTH=60;
    public static final int HEADER_LENGTH=20;

    String question;
    String[] answers;
    int correctAnswer;

    public Question(){
        this.question = "Question";
        this.answers = new String[]{"Answer"};
        this.correctAnswer = 1;
    }

    public Question(String question, String[] answers, int correctAnswer){
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
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
        s+="|\n"+"#"+ "=".repeat(STRING_LENGTH) +"#\n";

        for (int i = 0; i<answers.length; i++) {
            String answer = answers[i];
            s+="|" + i + ".";
            for (int j = 0; j < answer.length(); j++) {
                if(j>=STRING_LENGTH-5){
                    s+="...";
                    break;
                }
                s+=answer.charAt(j);
            }
            if(answer.length()<STRING_LENGTH-5){
                s+= " ".repeat((STRING_LENGTH-2)-answer.length());
            }
            s+="|\n";
        }

        s+="#"+ "=".repeat(STRING_LENGTH) +"#\n";

        questionString = s;
        return s;
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
}
