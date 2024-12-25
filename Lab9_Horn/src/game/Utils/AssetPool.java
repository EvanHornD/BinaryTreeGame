package game.Utils;

import java.util.HashMap;
import java.util.Map;

import game.Printing.Texture;

public class AssetPool {
    private static Map<String, Texture> textures = new HashMap<>();

    public static void addTexture(String resourceName, Texture texture){
        if(!AssetPool.textures.containsKey(resourceName)){
            AssetPool.textures.put(resourceName, texture);
        }
    }

    public static Texture getTexture(String resourceName){
        if(!AssetPool.textures.containsKey(resourceName)){
            System.err.println("The texture named'"+resourceName+"', doesn't exist");
            return new Texture();
        }
        return AssetPool.textures.get(resourceName);
    }



}
