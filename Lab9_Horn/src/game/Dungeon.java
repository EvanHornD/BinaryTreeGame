package game;

import java.util.Random;

public class Dungeon {

    private static Room rootNode;
    private static Room currentNode;

    public static void add(Room node){
        if(rootNode == null){
            rootNode = node;
            currentNode = node;
            return;
        }
        Random random = new Random();
        Room insertedNode = rootNode;
        while (true) {
            if(random.nextInt(2)==0){
                if(insertedNode.left==null){
                    insertedNode.left=node;
                    insertedNode.left.previous = insertedNode;
                    return;
                }
                insertedNode = insertedNode.left;
            } else {
                if(insertedNode.right==null){
                    insertedNode.right=node;
                    insertedNode.right.previous = insertedNode;
                    return;
                }
                insertedNode = insertedNode.right;
            }
        }
    }

    public static void printTree(){
        printRoot(rootNode);
    }

    private static void printRoot(Room root){
        if(root==null){
            return;
        }

        System.out.print(root);
        printRoot(root.left);
        printRoot(root.right);
    }

}
