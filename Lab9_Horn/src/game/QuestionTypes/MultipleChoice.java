package game.QuestionTypes;

import game.Application;

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
        String formatString = createFormatString();
        String answersString = createAnswerChoicesString();
        String s =
        "#"+ "=".repeat(STRING_LENGTH) +"#\n"+
        questionString+
        "#"+ "-".repeat(STRING_LENGTH) +"#\n"+
        formatString+
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

    private String createFormatString(){
        String formatString = "--input 1-"+answers.length+"--";
        String s = "|"+
        " ".repeat((STRING_LENGTH/2)-(formatString.length()/2))+formatString+" ".repeat((STRING_LENGTH/2)-(formatString.length()/2)-1)+"|\n";
        return s;
    }

    private String createAnswerChoicesString(){
        String s = "";
        for (int i = 0; i<answers.length; i++) {
            int offset = 5;
            String answer = answers[i];
            s+="|";
            if(answered&&i+1==correctAnswer){
                s+="-->";
                offset+=3;
            }
            s+= i+1 + ".";
            for (int j = 0; j < answer.length(); j++) {
                if(j>=STRING_LENGTH-offset){
                    s+="...";
                    break;
                }
                s+=answer.charAt(j);
            }
            if(answer.length()<STRING_LENGTH-offset){
                s+= " ".repeat((STRING_LENGTH-(offset-3))-answer.length());
            }
            s+="|\n";
        }
        return s;
    }

    @Override
    public boolean isCorrect(String userAnswer) {
        if(!Application.gameIsRunning){
            return true;
        }
        if(Integer.parseInt(userAnswer)==correctAnswer){
            answered=true;
            return true;
        }
        return false;
    }
}
