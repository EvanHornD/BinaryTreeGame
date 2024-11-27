package game.QuestionTypes;

public class MultipleChoice extends Question{
    String[] answers;
    int correctAnswer;

    public MultipleChoice(){
        super("Question");
        this.answers = new String[]{"Answer"};
        this.correctAnswer = 1;
    }

    public MultipleChoice(String question, String[] answers, int correctAnswer){
        super(question);
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
        String questionString = createQuestionString();
        String answersString = createAnswerChoicesString();
        String s =
        "#"+ "=".repeat(STRING_LENGTH) +"#\n"+
        questionString+
        "|\n"+"#"+ "-".repeat(STRING_LENGTH) +"#\n"+
        answersString+
        "#"+ "=".repeat(STRING_LENGTH) +"#\n";
        questionString = s;
        return s;
    }


    private String createQuestionString(){
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

    private String createAnswerChoicesString(){
        String s = "";
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
        return s;
    }
}
