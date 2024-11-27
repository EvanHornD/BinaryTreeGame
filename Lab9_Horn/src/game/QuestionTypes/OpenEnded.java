package game.QuestionTypes;

import game.Application;

public class OpenEnded extends Question{
    String answerFormat;
    String answer;

    public OpenEnded(){
        super("Question");
        this.answerFormat = "Format()";
        this.answer = "answer";
    }

    public OpenEnded(String question, String answerFormat, String answer){
        super(question);
        this.answerFormat = answerFormat;
        this.answer = answer;
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
        String formatString = createFormatString();
        String answerString = createAnswerString();
        String s =
        "#"+ "=".repeat(STRING_LENGTH) +"#\n"+
        questionString+
        "#"+ "-".repeat(STRING_LENGTH) +"#\n"+
        formatString+
        answerString+
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

    private String createFormatString(){
        String s = "|";
        for (int i = 0; i < answerFormat.length(); i++) {
            if(i>=STRING_LENGTH-3){
                s+="...";
                break;
            }
            s+=answerFormat.charAt(i);
        }
        if(answerFormat.length()<STRING_LENGTH-3){
            s+= " ".repeat((STRING_LENGTH)-answerFormat.length());
        }
        s+="|\n";
        return s;
    }

    private String createAnswerString(){
        String answerString;
        if(answered){
            answerString=answer;
        } else {
            answerString="_".repeat(answer.length());
        }
        String s = "|"+answerString+" ".repeat((STRING_LENGTH)-answer.length())+"|\n";
        return s;
    }

    @Override
    public boolean isCorrect(String userAnswer) {
        if(!Application.gameIsRunning){
            return true;
        }
        if(userAnswer.toLowerCase().equals(answer)){
            answered=true;
            return true;
        }
        return false;
    }
}
