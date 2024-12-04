package game.Printing;

import java.util.HashMap;
import java.util.Map;

public final class TextColor {
    private static final String DEFAULT = "\u001B[0m";

    @SuppressWarnings("FieldMayBeFinal")
    private static Map<Integer,String> textColors = new HashMap<>(Map.of(
            0xffffff,"\u001B[38;2;255;255;255m"
    ));

    @SuppressWarnings("FieldMayBeFinal")
    private static Map<Integer,String> backGroundColors = new HashMap<>(Map.of(
        0,DEFAULT
    ));

    private TextColor() {
        throw new UnsupportedOperationException("Utility class");
    }
    
    public static String getTextColor(int rgb){
        if(textColors.containsKey(rgb)){
            return textColors.get(rgb);
        }
        textColors.put(rgb,createTextColorString(rgb));
        return textColors.get(rgb);
    }
    
    private static String createTextColorString(int rgb){
        String color =  "\u001B[38;2;" +
                        ((rgb>>16)&0xff)+";" +
                        ((rgb>>8)&0xff)+";" +
                        (rgb&0xff)+"m";
        return color;
    }

    public static String getBackGroundColor(int rgb){
        if(backGroundColors.containsKey(rgb)){
            return backGroundColors.get(rgb);
        }
        backGroundColors.put(rgb,createBackGroundColorString(rgb));
        return backGroundColors.get(rgb);
    }
    
    private static String createBackGroundColorString(int rgb){
        if(rgb>>24==0){
            return "";
        }
        String color =  "\u001B[48;2;" +
                        ((rgb>>16)&0xff)+";" +
                        ((rgb>>8)&0xff)+";" +
                        (rgb&0xff)+"m";
        return color;
    }
}
