package game;

import game.Components.TextComponent;
import game.Components.Transform;
import game.Printing.Printer;
import game.Utils.Vector2;

public class Application {

    public static boolean gameIsRunning = true;

    public Application(){
        init();
    }

    private void init(){
        System.out.println("\u001B[48;2;0;0;0m");

        Entity text1 = new Entity("obj1", new Transform(new Vector2(1, 0)), 2);
        text1.addComponent(new TextComponent("""
                                             #==========#
                                             |          |
                                             |          |
                                             |          |
                                             |          |
                                             |          |
                                             |          |
                                             #==========#
                                             """,
                                            0x0f0f0f,0xffffffff,false));
        Printer.addToLayers(text1);

        Entity text2 = new Entity("obj2", new Transform(new Vector2(0, 2)), 1);
        text2.addComponent(new TextComponent("""
                                             #============#
                                             |  Welcome!  |
                                             #============#
                                             """,
                                            0xff0000, true));
        Printer.addToLayers(text2);
    }

    public void run(){
        //Printer.clearTerminal();
        System.out.println("-".repeat(Printer.camera.width()));
        Printer.print();
        System.out.println("-".repeat(Printer.camera.width()));
        //Printer.printWindows();

        Entity textEntity = Printer.getEntity("obj1");
        textEntity.position.move(new Vector2(1,1));
        Printer.update();

        //Printer.clearTerminal();
        System.out.println("-".repeat(Printer.camera.width()));
        Printer.print();
        System.out.println("-".repeat(Printer.camera.width()));
        while (gameIsRunning) {
        }
    }


}
