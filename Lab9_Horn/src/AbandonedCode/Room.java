package AbandonedCode;

import AbandonedCode.QuestionTypes.Question;
import game.Application;
import game.Printing.Printer;
import game.Utils.Input;

public class Room {
    Room left;
    Room right;
    Room previous;
    private Question question;

    public Room(String roomType, int roomNumber){
        switch (roomType) {
            case "QuestionRoom":
                this.question = Questions.getQuestion(roomNumber);
            break;
            case "FinishRoom":
                this.question = Questions.getFinishRoom(roomNumber);
                System.out.println(question);
            break;
            default:
                System.out.println("There was an Error inputing '"+roomType+", "+roomNumber+"'");
            break;
        }
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

        if(left==null){
            s+=" ".repeat((Question.STRING_LENGTH-(Question.HEADER_LENGTH/2)));
        } else{
            s+=" ".repeat(Question.HEADER_LENGTH/2)+"|";
            s+=" ".repeat((Question.STRING_LENGTH-Question.HEADER_LENGTH)-1);
        }
        if(right!=null){
            s+="|";
        }
        s+="\n";

        if(left==null){
            s+=" ".repeat(Question.HEADER_LENGTH);
        }else{
            s+=left.question.getHeader();
        }

        s+= " ".repeat(Question.STRING_LENGTH-(2*Question.HEADER_LENGTH));

        if(right!=null){
            s+=right.question.getHeader();
        }
        s+="\n";

        nodeString = s;
        return s;
    }

    public void answer(){
        if(question.answered){
            return;
        }
        //Printer.printQuestionAnswering(question, null);
        //while(!question.isCorrect(Input.getAnswer())){
        //    Printer.health--;
        //    if (Printer.health<=0) {
        //        Application.gameIsRunning=false;
        //        Printer.printGameEnd(true);
        //        return;
        //    }
        //    Printer.printQuestionAnswering(question, false);
        //}
    }
}
