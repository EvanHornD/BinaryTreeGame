package game;

import game.Utils.Input;
import game.Utils.Questions;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Application {

    public static boolean gameIsRunning = true;

    public Application(){
        init();
    }

    private void init(){
        Questions.readFile("Lab9_Horn\\assets\\computer_science_questions.csv");
        resetGameTree();
    }

    private void resetGameTree(){
        List<Integer> ints = new ArrayList<>();
        for (int i = 0; i < Questions.size(); i++) {
            ints.add(i);
        }

        Random random = new Random();
        for (int i = 0; i < Questions.size(); i++) {
            int index = random.nextInt(Questions.size()-i);
            int questionNumber = ints.get(index);
            Dungeon.add(new Room("QuestionRoom",questionNumber));
            ints.remove(index);
        }

        Dungeon.add(new Room("FinishRoom",Questions.finishMessagesSize()-1));
        // prints in inorder
        //Dungeon.printTree();
    }

    public void run(){
        Input.printMovement();
        while(gameIsRunning){
            Dungeon.traverse();
        }
    }


}
