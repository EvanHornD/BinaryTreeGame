package game;

import java.util.Arrays;

import game.Components.TextComponent;
import game.Components.Transform;
import game.Printing.Pixel;
import game.Printing.Terminal;
import game.Printing.Texture;
import game.Scenes.Scene;
import game.UserInput.RawConsoleInput;
import game.Utils.Color;
import game.Utils.Time;
import game.Utils.Vector2;

public class Application {

    public static boolean gameIsRunning = true;
    public static Scene scene;

    public Application(){
        init();
    }

    private void init(){
        scene = new Scene();
        Terminal.clearTerminal();


        Texture texture1 = new Texture.Builder()
        .setText("""
                #==========#
                |          |
                |          |
                |          |
                |          |
                |          |
                |          |
                #==========#
                """)
        .setTextColor(new Color(200,100,200))
        .setBackGroundColor(new Color(255,16,16,16))
        .build();

        Entity text1 = new Entity("test", new Transform(new Vector2(0, 0)), 0);
        text1.addComponent(new TextComponent(texture1));
        scene.addEntity(text1);

        Texture texture2 = new Texture.Builder()
        .setText("""
                #==================================================================================================================================================================================#
                |                                                                                                                                                                                  |
                |                                                                                                                                                                                  |
                |                                                                                                                                                                                  |
                |                                                                                                                                                                                  |
                |                                                                                                                                                                                  |
                |                                                                                                                                                                                  |
                |                                                                                                                                                                                  |
                |                                                                                                                                                                                  |
                |                                                                                                                                                                                  |
                |                                                                                                                                                                                  |
                |                                                                                                                                                                                  |
                |                                                                                                                                                                                  |
                |                                                                                                                                                                                  |
                |                                                                                                                                                                                  |
                |                                                                                                                                                                                  |
                |                                                                                                                                                                                  |
                |                                                                                                                                                                                  |
                |                                                                                                                                                                                  |
                |                                                                                                                                                                                  |
                |                                                                                                                                                                                  |
                |                                                                                                                                                                                  |
                |                                                                                                                                                                                  |
                |                                                                                                                                                                                  |
                |                                                                                                                                                                                  |
                |                                                                                                                                                                                  |
                |                                                                                                                                                                                  |
                |                                                                                                                                                                                  |
                |                                                                                                                                                                                  |
                |                                                                                                                                                                                  |
                |                                                                                                                                                                                  |
                |                                                                                                                                                                                  |
                |                                                                                                                                                                                  |
                |                                                                                                                                                                                  |
                |                                                                                                                                                                                  |
                |                                                                                                                                                                                  |
                |                                                                                                                                                                                  |
                |                                                                                                                                                                                  |
                |                                                                                                                                                                                  |
                |                                                                                                                                                                                  |
                |                                                                                                                                                                                  |
                |                                                                                                                                                                                  |
                |                                                                                                                                                                                  |
                |                                                                                                                                                                                  |
                |                                                                                                                                                                                  |
                |                                                                                                                                                                                  |
                |                                                                                                                                                                                  |
                |                                                                                                                                                                                  |
                |                                                                                                                                                                                  |
                #==================================================================================================================================================================================#
                """)
        .setTextColor(new Color(100,200,200))
        .setBackGroundColor(new Color(255,16,16,16))
        .build();

        Entity text2 = new Entity("Welcome", new Transform(new Vector2(0, 0)), -1);
        text2.addComponent(new TextComponent(texture2));
        scene.addEntity(text2);


        Texture fps = new Texture.Builder()
        .setText("""
                FPS: 0
                """)
        .setTextColor(new Color(255,255,255))
        .build();

        Entity fpsEntity = new Entity("fps", new Transform(new Vector2(0, 10)), 2);
        fpsEntity.addComponent(new TextComponent(fps));
        scene.addEntity(fpsEntity);

        Terminal.update();
        Terminal.print();
    }

    public void run(){
        float beginTime = Time.getTime();
        float endTime;
        float dt = -1.0f;

        Entity textEntity = scene.getEntity("test");


        Entity fps = scene.getEntity("fps");
        TextComponent fpstextcomponent = fps.getComponent("Text");


        while(gameIsRunning){
            Terminal.print();

            textEntity.position.move(new Vector2(.1f,.1f));

            endTime = Time.getTime();
            dt = endTime-beginTime;
            beginTime = endTime;

            fpstextcomponent.setTexture(new Texture.Builder()
                            .setText("FPS: "+1/dt)
                            .build());

            Terminal.update();
        }
        resetConsoleMode();
    }

    private static void resetConsoleMode(){
        try {
            RawConsoleInput.resetConsoleMode(); // Reset console mode to normal
        } catch (Exception e) {
            System.err.println("Failed to reset console mode.");
            e.printStackTrace();
        }
    }
}
