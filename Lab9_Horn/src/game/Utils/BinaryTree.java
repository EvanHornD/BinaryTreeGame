package game.Utils;

import java.util.Random;

public class BinaryTree {

    private static Node rootNode;
    private static Node currentNode;

    public static void add(Node node){
        if(rootNode == null){
            rootNode = node;
            currentNode = node;
            return;
        }
        Random random = new Random();
        Node insertedNode = rootNode;
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

    private static void printRoot(Node root){
        if(root==null){
            return;
        }

        System.out.print(root);
        printRoot(root.left);
        printRoot(root.right);
    }

}
