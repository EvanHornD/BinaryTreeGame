package game.Printing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import game.Entity;
import game.Components.TextComponent;
import game.Components.Transform;
import game.Utils.Vector2;

public final class Printer {

    public static int width = 7;
    public static int height = 5;
    private static String Window = "";
    private static List<EntityLayer> layers;
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

    private static int[] colorWindow;

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

    public static Entity getEntity(String name){
        for (EntityLayer layer : layers) {
            if (layer.containsEntity(name)) {
                return layer.getEntity(name);
            }
        }
        return null;
    }

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
        if(textWindow==null){
            textWindow = new char[width*height];
            colorWindow = new int[width*height];
            for (int i=0; i<colorWindow.length;i++) {
                colorWindow[i] = 0xffffff;
            }
            for (int i=0; i<textWindow.length;i++) {
                textWindow[i] = ' ';
            }
        }
        createWindows();
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
        String text = textComponent.getString();
        Transform position = textComponent.getPosition();
        int color = textComponent.getColor();
        for (int i = 0; i < text.length(); i++) {
            Vector2 charPos = new Vector2(position.x()+i, position.y());
            if(!(charPos.x>width-1||
                 charPos.y>height-1||
                 charPos.x<0||
                 charPos.y<0))
                {
                int windowPos = (charPos.y*width)+charPos.x;
                textWindow[windowPos] = text.charAt(i);
                colorWindow[windowPos] = color;
            }
        }
    }

    private static String getString(){
        int previousColor = 0xffffff;
        int currentColor;
        String finalString = "";
        for(int i=0; i<textWindow.length;i++){
            currentColor = colorWindow[i];
            if(currentColor != previousColor){
                finalString+=TextColor.getColor(currentColor);
            }
            finalString+=textWindow[i];
            if((i+1)%width==0){
                finalString+="\n";
            }
            previousColor = currentColor;
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

    public static void clearTerminal(){
        try { new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();}
        catch (Exception e) {}
    }
}