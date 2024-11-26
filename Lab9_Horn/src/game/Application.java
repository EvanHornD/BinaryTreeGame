package game;

import game.Utils.Questions;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Application {

    private boolean gameIsRunning = true;

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
            Dungeon.add(new Room(random.nextInt(Questions.size()-i)));
        }

        // prints in preorder
        Dungeon.printTree();
    }

    public void run(){
        while(gameIsRunning){
            gameIsRunning=false;

        }
    }


}
