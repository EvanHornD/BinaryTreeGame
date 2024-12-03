package game.Printing;

import java.util.HashMap;
import java.util.Map;

public final class TextColor {
    private static final String DEFAULT_COLOR = "\u001B[0m";

    private static Map<Integer,String> colors = new HashMap<>(Map.of(
            0xffffff,DEFAULT_COLOR
    ));

    private TextColor() {
        throw new UnsupportedOperationException("Utility class");
    }
    
    public static String getColor(int rgb){
        if(colors.containsKey(rgb)){
            return colors.get(rgb);
        }
        colors.put(rgb,createColorString(rgb));
        return colors.get(rgb);
    }
    
    private static String createColorString(int rgb){
        String color =  "\u001B[38;2;" +
                        ((rgb>>16)&0xff)+";" +
                        ((rgb>>8)&0xff)+";" +
                        (rgb&0xff)+"m";
        return color;
    }
}
