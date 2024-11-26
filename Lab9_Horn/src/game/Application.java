package game;

import game.Utils.Questions;

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
        Dungeon.add(new Room(1));
        Dungeon.add(new Room(2));
        Dungeon.add(new Room(3));
        Dungeon.add(new Room(4));
        Dungeon.add(new Room(5));
        Dungeon.add(new Room(6));

        // prints in preorder
        Dungeon.printTree();
    }

    public void run(){
        while(gameIsRunning){

        }
    }


}
