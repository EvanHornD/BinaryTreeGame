package game.Printing;

import game.Components.TextComponent;
import game.Components.Transform;
import game.Entity;
import game.Utils.Vector2;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class Printer {

    public static Camera camera = new Camera();
 
    // move variable into a renderer
    private static List<EntityLayer> layers;


    private static String Window = "";

    private static char[] textWindow;

    /* text window format
    {char,char,char,... width times
	 char,char,char,...
	 char,char,char,...
	 .
	 .
 	 .
	 height times
	}
    */

    private static int[] textColorWindow;

    /* color window format
	{int,int,int,..., width times
	 int,int,int,...,
	 int,int,int,...,
	 .,
	 .,
 	 .,
	 height times
	}
     */

    private static int[] backGroundColorWindow;

    /* color window format
    {int,int,int,..., width times
     int,int,int,...,
     int,int,int,...,
     .,
     .,
     .,
     height times
    }
     */
    
    //#region Renderer shoulve hold the layers
    // gets an entity based on its name
    public static Entity getEntity(String name){
        for (EntityLayer layer : layers) {
            if (layer.containsEntity(name)) {
                return layer.getEntity(name);
            }
        }
        return null;
    }

    // adds 
    public static void addToLayers(Entity entity){
        if(layers==null){
            layers = new ArrayList<>();
        }
        boolean added = false;
        for (EntityLayer layer : layers) {
            added = addToLayer(layer, entity);
            if(added){
                break;
            }
        }
        if(!added){
            EntityLayer newLayer = new EntityLayer(entity.zIndex());
            newLayer.addEntity(entity);
            layers.add(newLayer);
            Collections.sort(layers);
        }
        update();
    }

    private static boolean addToLayer(EntityLayer layer, Entity entity){
        if(entity.zIndex()!=layer.zIndex()){
            return false;
        }
        layer.addEntity(entity);
        return true;
    }

    
    public static void loadLayers(){
        if(layers==null){
            layers = new ArrayList<>();
        }
        clearWindows();
        createWindows();
    }

    private static void clearWindows(){
        if(textWindow==null){
            int windowDimensions = camera.width()*camera.height();
            textWindow =            new char[windowDimensions];
            textColorWindow =       new  int[windowDimensions];
            backGroundColorWindow = new  int[windowDimensions];
        }
        for (int i=0; i<backGroundColorWindow.length;i++) {
            textColorWindow[i] = 0xffffff;
        }
        for (int i=0; i<backGroundColorWindow.length;i++) {
            backGroundColorWindow[i] = 0;
        }
        for (int i=0; i<textWindow.length;i++) {
            textWindow[i] = ' ';
        }
    }

    private static void createWindows(){
        for (EntityLayer layer : layers) {
            TextComponent[] textEntities = layer.getTextArray();
            for (TextComponent textComponent : textEntities) {
                addTextComponentToWindow(textComponent);
            }
        }
    }

    private static void addTextComponentToWindow(TextComponent textComponent){

        String         text = textComponent.getString();
        Transform  position = textComponent.getPosition();
        int           color = textComponent.getColor();
        int backGroundColor = textComponent.getBackGroundColor();
        boolean transparent = textComponent.getTransparency();

        Vector2 charPos = new Vector2(position.x(), position.y());

        for (int i = 0; i < text.length(); i++) {
            addCharToWindow(position, charPos, text, i, color, backGroundColor, transparent);
        }
    }

    private static void addCharToWindow(Transform textPos, Vector2 charPos, String text, int textIndex, int color, int backGroundColor, boolean transparent){
        char character = text.charAt(textIndex);
        charPos.x++;

        // checks if the character is the next line character
        if(character=='\n'){
            charPos.x = textPos.x();
            charPos.y++;
            return;
        }

        // checks if the character is out of bounds
        if(!camera.checkCoords(charPos)){
            return;
        }

        // gets the index in the window arrays from the the current position
        int windowPos = (charPos.y*camera.width())+charPos.x;

        // check transparency
        if(!transparent){
            backGroundColorWindow[windowPos] = backGroundColor;
            textColorWindow[windowPos] = color;
            textWindow[windowPos] = character;
            return;
        }

        // override the background color if it has one
        if(backGroundColor>>24!=0){
            backGroundColorWindow[windowPos] = backGroundColor;
        }

        // check if the character is a space
        if(character!=' '){
            textColorWindow[windowPos] = color;
            textWindow[windowPos] = character;
        }
    }

    private static String getString(){
        int previousColor = 0xffffff;
        int currentColor;
        int previousBackGroundColor = 0;
        int currentBackGroundColor;
        String finalString = "";

        for(int i=0; i<textWindow.length;i++){

            currentBackGroundColor = backGroundColorWindow[i];
            if(currentBackGroundColor != previousBackGroundColor){
                finalString+=TextColor.getBackGroundColor(currentBackGroundColor);
            }

            currentColor = textColorWindow[i];
            if(currentColor != previousColor){
                finalString+=TextColor.getTextColor(currentColor);
            }

            finalString+=textWindow[i];
            if((i+1)%camera.width()==0){
                finalString+="\n";
            }

            previousColor = currentColor;
            previousBackGroundColor = currentBackGroundColor;
        }
        return finalString;
    }

    public static void update(){
        loadLayers();
        Window = getString();
    }

    public static void print(){
        System.out.print(Window);
    }

    public static void printWindows(){
        System.out.println(Arrays.toString(textWindow));
        System.out.println(Arrays.toString(textColorWindow));
        System.out.println(Arrays.toString(backGroundColorWindow));
    }

    public static void clearTerminal(){
        try { new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();}
        catch (IOException | InterruptedException e) {}
    }
}