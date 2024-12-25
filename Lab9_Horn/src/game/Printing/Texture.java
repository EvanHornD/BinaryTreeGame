package game.Printing;

import java.util.Arrays;

import game.Utils.AssetPool;
import game.Utils.Color;
import game.Utils.Vector2;

public class Texture {
    private String resourceName;
    private boolean isStatic;
    private Pixel[] pixels;
    private Vector2 dimensions;

    public void setPixels(Pixel[] pixels){
        if(isStatic){ 
            System.out.println("Static Textures Cannot be overriden"); 
            return;
        }
        this.pixels = pixels;
    }

    public Pixel[] getPixels(){ return this.pixels; }

    public Vector2 getDimensions(){ return this.dimensions; }

    public boolean isStatic(){ return this.isStatic; }

    public String getResourceName(){ return this.resourceName; }

    public static class Builder {
    
        public Pixel[] pixels = {Pixel.NULL_PIXEL};
        private Vector2 dimensions = new Vector2(1,1);
        private String resourceName = "";
        private boolean isStatic = false;

        public Builder setText(String text){
            this.dimensions = getDimensions(text);
            this.pixels = createPixelArray(text);
            return this;
        }

        private Pixel[] createPixelArray(String text){
            Pixel[] pixels = new Pixel[(int)dimensions.x*(int)dimensions.y];
            Arrays.fill(pixels,Pixel.NULL_PIXEL);
            int x = 0;
            int y = 0;
            for (int i = 0; i < text.length(); i++) {
                char currentChar = text.charAt(i);
                if(currentChar=='\n'){
                    x=0;
                    y++;
                    continue;
                }
                int pixelIndex = (int)(y*dimensions.x)+x;
                pixels[pixelIndex] = pixels[pixelIndex].setCharacter(currentChar);
                x++;
            }
            return pixels;
        }

        private Vector2 getDimensions(String text){
            int currentWidth = 0;
            int maxWidth = 0;
            int height = 1;
            for (int i = 0; i < text.length(); i++) {
                char currentChar = text.charAt(i);
                if(currentChar!='\n'){
                    currentWidth++;
                } else {
                    if(currentWidth>maxWidth) maxWidth=currentWidth;
                    if(i!=text.length()-1) height++; 
                    currentWidth=0;
                }
            }
            if(currentWidth>maxWidth) maxWidth=currentWidth;
            return new Vector2(maxWidth, height);
        }

        public Builder setTextColor(Color color){
            for (Pixel pixel : pixels) {
                pixel = pixel.setColor(color);
            }
            return this;
        }

        public Builder setBackGroundColor(Color backGroundColor){
            for (int i = 0; i < pixels.length; i++) {
                pixels[i] = pixels[i].setBackGroundColor(backGroundColor);
            }
            return this;
        }

        public Builder setStatic(String resourceName){
            this.resourceName = resourceName;
            this.isStatic = true;
            return this;
        }

        public Texture build(){
            Texture texture = new Texture();
            texture.resourceName = this.resourceName;
            texture.pixels = this.pixels;
            texture.dimensions = this.dimensions;  
            texture.isStatic = this.isStatic;

            if(texture.isStatic){
                AssetPool.addTexture(resourceName, texture);
            }
            return texture;
        }
    }
}
