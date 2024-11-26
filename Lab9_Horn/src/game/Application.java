package game;

import game.Utils.BinaryTree;
import game.Utils.Node;
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
        BinaryTree.add(new Node(1));
        BinaryTree.add(new Node(2));
        BinaryTree.add(new Node(3));
        BinaryTree.add(new Node(4));
        BinaryTree.add(new Node(5));
        BinaryTree.add(new Node(6));

        // prints in preorder
        BinaryTree.printTree();
    }

    public void run(){
        while(gameIsRunning){

        }
    }


}
