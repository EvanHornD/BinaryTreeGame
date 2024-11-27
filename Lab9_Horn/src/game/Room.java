package game;

import game.QuestionTypes.Question;
import game.Utils.Questions;

public class Room {
    Room left;
    Room right;
    Room previous;
    private Question question;

    public Room(int question){
        this.question = Questions.getQuestion(question);
    }

    private String nodeString;
    @Override
    public String toString() {

        if(nodeString!=null){
            return nodeString;
        }
        return createString();
    }

    public String createString(){
        String s = "";

        if(previous!=null){
            s+=" ".repeat((Question.STRING_LENGTH/2)-(Question.HEADER_LENGTH/2));
            s+=previous.question.getHeader()+"\n";
            s+=" ".repeat((Question.STRING_LENGTH/2))+"|"+"\n";
        }

        s+=question.toString();

        s+=" ".repeat(Question.HEADER_LENGTH/2)+"|";
        s+=" ".repeat((Question.STRING_LENGTH-Question.HEADER_LENGTH)-1)+"|\n";

        if(left==null){
            s+=" ".repeat((Question.HEADER_LENGTH/2)-2)+"null"+" ".repeat((Question.HEADER_LENGTH/2)-2);
        }else{
            s+=left.question.getHeader();
        }

        s+= " ".repeat(Question.STRING_LENGTH-(2*Question.HEADER_LENGTH));

        if(right==null){
            s+=" ".repeat((Question.HEADER_LENGTH/2)-2)+"null";
        }else{
            s+=right.question.getHeader();
        }
        s+="\n";

        nodeString = s;
        return s;
    }
}
