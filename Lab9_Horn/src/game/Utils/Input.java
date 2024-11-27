package game.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import game.Application;
import game.QuestionTypes.Question;

public class Input {

    public static Scanner userInput = new Scanner(System.in);
    private static final String MOVEMENT_HEADER = "Movement Commands";
    private static final Map<String, Integer> MOVEMENT_COMMANDS = new HashMap<>(Map.of(
        "left",0,
        "back",2,
        "right",1
    ));

    private static final String EXIT = "exit"; 

    public static Input instance;
    public Input(){}

    public static Input get(){
        if(instance == null){
            instance = new Input();
        }
        return instance;
    }


    public static int getMovement(){
        String input = userInput.nextLine().toLowerCase();
        if(input.equals(EXIT)){
            Application.gameIsRunning=false;
            Printer.printGameEnd(null);
        }
        if(!MOVEMENT_COMMANDS.containsKey(input)){
            return -1;
        }
        return MOVEMENT_COMMANDS.get(input);
    }

    public static String getAnswer(){
        String input = userInput.nextLine();
        if(input.equals(EXIT)){
            Application.gameIsRunning=false;
            Printer.printGameEnd(null);
        }
        return input;
    }

    private static String movementString; 
    public static void printMovement(){
        if(movementString!=null){
            System.out.println(movementString);
            return;
        }
        String[] commands = getMovementCommands();
        String commandString = createCommandsString(commands);
        int commandsLength = Math.max(commandString.length(), Question.STRING_LENGTH);
        String s= 
        "#"+ "=".repeat(commandsLength) +"#\n"+
        '|'+" ".repeat((commandsLength/2)-(MOVEMENT_HEADER.length()/2))+MOVEMENT_HEADER+" ".repeat((commandsLength/2)-(MOVEMENT_HEADER.length()/2)-1)+"|\n"+
        "#"+ "-".repeat(commandsLength) +"#\n"+
        '|'+" ".repeat((commandsLength/2)-(commandString.length()/2))+commandString+" ".repeat((commandsLength/2)-(commandString.length()/2))+"|\n"+
        "#"+ "=".repeat(commandsLength) +"#";
        movementString=s;
        System.out.println(s);
    }

    private static String[] getMovementCommands(){
        List<String> commands = new ArrayList<>();
        for(Map.Entry<String,Integer> command : MOVEMENT_COMMANDS.entrySet()){
            commands.add(command.getKey());
        }
        return commands.toArray(new String[0]);
    }

    private static String createCommandsString(String[] commands){
        String s = "";
        for (int i = 0; i < commands.length; i++) {
            s+=commands[i];
            if(i<commands.length-1){
                s+='|';
            }
        }
        s+="|"+EXIT;
        return s;
    }
}
