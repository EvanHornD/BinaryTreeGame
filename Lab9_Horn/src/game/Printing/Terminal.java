package game.Printing;

import game.Application;
import game.Utils.Vector2;

public final class Terminal {

    private static String Window = "";

    public static void update(){
        Application.scene.renderer().updateWindows();
        Window = Application.scene.renderer().toString();
    }

    public static void drawWindow(Vector2 dimensions){
        Window = "";
        for (int i = 0; i < dimensions.y; i++) {
            Window += " ".repeat((int)dimensions.x) + '\n';
        }
        clearTerminal();
        print();
    }

    public static void print(){
        System.out.print(Window);
        System.out.flush();
    }

    public static void clearTerminal(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}