package AbandonedCode;

import java.util.Random;

import game.Application;
import game.Printing.Terminal;
import game.Utils.Input;

public class Dungeon {

    private static Room rootRoom;
    private static Room currentRoom;

    public static void add(Room room){
        if(rootRoom == null){
            rootRoom = room;
            currentRoom = room;
            return;
        }
        Random random = new Random();
        Room insertedRoom = rootRoom;
        while (true) {
            if(random.nextInt(2)==0){
                if(insertedRoom.left==null){
                    insertedRoom.left=room;
                    insertedRoom.left.previous = insertedRoom;
                    return;
                }
                insertedRoom = insertedRoom.left;
            } else {
                if(insertedRoom.right==null){
                    insertedRoom.right=room;
                    insertedRoom.right.previous = insertedRoom;
                    return;
                }
                insertedRoom = insertedRoom.right;
            }
        }
    }

    public static boolean Move(int direction){
        switch (direction) {
            case 0: // move left
                if(currentRoom.left==null){
                    //Printer.printRoomTraversal(currentRoom);
                    System.out.println("There is no room to the left.");
                    return false;
                }
                currentRoom = currentRoom.left;
            break;
            case 1: // move right
                if(currentRoom.right==null){
                    //Printer.printRoomTraversal(currentRoom);
                    System.out.println("There is no room to the right.");
                    return false;
                }
                currentRoom = currentRoom.right;
            break;
            case 2: // move back
                if(currentRoom.previous==null){
                    //Printer.printRoomTraversal(currentRoom);
                    System.out.println("There is room before this.");
                    return false;
                }
                currentRoom = currentRoom.previous;
            break;
            case-2:
            break;
            default:
            //Printer.printRoomTraversal(currentRoom);
                System.out.println("Invalid Move Direction");
            break;
        }
        return true;
    }

    public static void traverse(){
        currentRoom.answer();
        if(!Application.gameIsRunning){
            return;
        }
        //Printer.printRoomTraversal(currentRoom);
        while(!Move(Input.getMovement())){}
    }

    public static void printTree(){
        printRoot(rootRoom);
    }

    private static void printRoot(Room root){
        if(root==null){
            return;
        }

        printRoot(root.left);
        System.out.print(root);
        printRoot(root.right);
    }

    public static void printCurrentRoom(){
        System.out.println(currentRoom);
    }

}
