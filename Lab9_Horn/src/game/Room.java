package game;

import game.Utils.Questions;

public class Room {
    Room left;
    Room right;
    Room previous;
    private int questionNumber;

    public Room(int questionNumber){
        this.questionNumber = questionNumber;
    }

    public Room(int questionNumber, Room previous){
        this.questionNumber = questionNumber;
        this.previous = previous;
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
            s+=Questions.getQuestion(previous.questionNumber).getHeader()+"\n";
            s+=" ".repeat((Question.STRING_LENGTH/2))+"|"+"\n";
        }

        s+=Questions.getQuestion(questionNumber).toString();

        s+=" ".repeat(Question.HEADER_LENGTH/2)+"|";
        s+=" ".repeat((Question.STRING_LENGTH-Question.HEADER_LENGTH)-1)+"|\n";

        if(left==null){
            s+=" ".repeat((Question.HEADER_LENGTH/2)-2)+"null"+" ".repeat((Question.HEADER_LENGTH/2)-2);
        }else{
            s+=Questions.getQuestion(left.questionNumber).getHeader();
        }

        s+= " ".repeat(Question.STRING_LENGTH-(2*Question.HEADER_LENGTH));

        if(right==null){
            s+=" ".repeat((Question.HEADER_LENGTH/2)-2)+"null";
        }else{
            s+=Questions.getQuestion(right.questionNumber).getHeader();
        }
        s+="\n";

        nodeString = s;
        return s;
    }
}
