package game.Utils;

import game.Room;
import game.QuestionTypes.Question;

public class Printer {
    public static int health=5;
    private static final String HEALTH_STRING = "HP = ";

    public static Printer instance;
    public Printer(){}

    public static Printer get(){
        if(instance == null){
            instance = new Printer();
        }
        return instance;
    }

    public static void printRoomTraversal(Room room){
        clearLines();
        System.out.println("".repeat((Question.STRING_LENGTH/2)-(HEALTH_STRING.length()/2))+HEALTH_STRING+health);
        Input.printMovement();
        System.out.println(room);
    }

    public static void printQuestionAnswering(Question question, Boolean isCorrect){
        clearLines();
        System.out.println("".repeat((Question.STRING_LENGTH/2)-(HEALTH_STRING.length()/2))+HEALTH_STRING+health);
        System.out.println(question);
        if (isCorrect==null) {
            return;
        }

        System.out.println("That was wrong try again.");
    }

    public static void printGameEnd(Boolean died){
        clearLines();
        if (died==null) {
            System.out.print("Game Closing");
            return;
        }
        if(died){
            System.out.print("Your Ran out of lives Game Closing");
            return;
        }
    }

    public static void clearLines(){
        try { new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();}
        catch (Exception e) {}
    }
}
