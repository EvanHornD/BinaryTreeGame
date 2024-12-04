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
        Printer.width = 20;
        Printer.height = 7;

        Entity text1 = new Entity("obj1", new Transform(new Vector2(0, 0)), 0);
        text1.addComponent(new TextComponent("""
                                             #==========#
                                             |          |
                                             |          |
                                             |          |
                                             |          |
                                             #==========#
                                             """,
                                            0x0f0f0f,0xff000000,false));
        Printer.addToLayers(text1);

        Entity text2 = new Entity("obj2", new Transform(new Vector2(3, 0)), 1);
        text2.addComponent(new TextComponent("Welcome", 0xff0000, true));
        Printer.addToLayers(text2);
    }

    public void run(){
        //Printer.clearTerminal();
        System.out.println("-".repeat(Printer.width));
        Printer.print();
        System.out.println("-".repeat(Printer.width));

        Printer.printWindows();

        Entity textEntity = Printer.getEntity("obj2");
        textEntity.position.movePosition(new Vector2(0,1));
        Printer.update();

        //Printer.clearTerminal();
        System.out.println("-".repeat(Printer.width));
        Printer.print();
        System.out.println("-".repeat(Printer.width));

        while (gameIsRunning) {
        }
    }


}
