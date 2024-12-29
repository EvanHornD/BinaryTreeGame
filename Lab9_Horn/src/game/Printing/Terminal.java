package game.Printing;

import game.Application;
import game.Utils.Vector2;

public final class Terminal {

    private static String Window = "";

    public static void update(){
        Application.scene.renderer().updateWindows();
        Window = Application.scene.renderer().toString();
    }

    public static String emptyWindow(){
        Vector2 dimensions = Application.scene.renderer().camera.dimensions;
        String emptyWindow = "\033[1;0H";
        for (int i = 0; i < dimensions.y; i++) {
            emptyWindow += " ".repeat((int)dimensions.x) + "\033["+(i)+";0H";
        }
        return emptyWindow;
    }

    public static void print(){
        System.out.print(Window);
        System.out.flush();
    }

    public static void clearTerminal(){
        System.out.print("\033[H\033[2J");
        System.out.print(emptyWindow());
        System.out.flush();
    }
}