package game.Utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Input {

    public static Scanner userInput = new Scanner(System.in);
    private static final Map<String, Integer> MOVEMENT_COMMANDS = new HashMap<>(Map.of(
        "left",0,
        "back",2,
        "right",1
    ));

    public static final String EXIT = "exit"; 

    public static int getMovement(){
        String input = userInput.nextLine().toLowerCase();
        if(!MOVEMENT_COMMANDS.containsKey(input)){
            return -1;
        }
        return MOVEMENT_COMMANDS.get(input);
    }
}
