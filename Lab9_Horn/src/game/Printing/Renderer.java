package game.Printing;

import java.util.Arrays;
import java.util.List;

import game.Entity;
import game.Components.TextComponent;
import game.Components.Transform;
import game.Utils.Vector2;

public class Renderer {

    private Pixel[] pixels;
    public LayerMap layerMap;
    public Camera camera;
    
    public Renderer(Camera camera){
        this.layerMap = new LayerMap();
        this.camera = camera;
        setDimensions(new Vector2(180, 50));
        resetWindows();
    }

    public void add(Entity entity){
        layerMap.addToLayers(entity);
        updateWindows();
    }

    public void setDimensions(Vector2 dimensions){
        camera.setDimensions(dimensions);
    }

    private void resetWindows(){
        if(pixels==null){
            int windowDimensions = camera.width()*camera.height();
            pixels = new Pixel[windowDimensions];
        }
        Arrays.fill(pixels,Pixel.NULL_PIXEL);
    }

    public void updateWindows(){
        resetWindows();
        List<EntityLayer> layers = layerMap.getLayers();
        for (EntityLayer layer : layers) {
            TextComponent[] textEntities = layer.getTextArray();
            for (TextComponent textComponent : textEntities) {
                addTextComponentToWindow(textComponent);
            }
        }
    }

    private void addTextComponentToWindow(TextComponent textComponent){
        Texture texture = textComponent.getTexture();
        Transform position = textComponent.getPosition();
        Vector2 dimensions = texture.getDimensions();
        Pixel[] texturePixels = texture.getPixels();


        // check if text is within the cameras view window;
        if(!camera.checkTextureCoords(position,dimensions)){
            return;
        }

        Vector2 pixelPos = new Vector2(position.x(), position.y());

        for (int i = 0; i < texturePixels.length; i++) {
            addPixel(position, pixelPos, texturePixels[i]);
            if(pixelPos.x+1>=dimensions.x+position.x()){
                pixelPos.x=position.x();
                pixelPos.y++;
                continue;
            }
            pixelPos.x++;
        }
    }

    private void addPixel(Transform textPos, Vector2 pixelPos, Pixel pixel){
        
        if(!camera.checkPixelCoords(pixelPos)){
            return; 
        }

        Vector2 charPosInCamera = Vector2.subtract(pixelPos, camera.position.position);
        // gets the index in the window arrays from the the current position
        int windowPos = (int) ((charPosInCamera.y*camera.width())+charPosInCamera.x);

        pixels[windowPos] = Pixel.overlayPixels(pixel, pixels[windowPos]);
    }

    @Override
    public String toString(){
        Pixel previousPixel = Pixel.NULL_PIXEL;
        Pixel currentPixel = pixels[0];

        int previousColor = previousPixel.getColor();
        int currentColor = currentPixel.getColor();

        int previousBackGroundColor = previousPixel.getBackGroundColor();
        int currentbackGroundColor = currentPixel.getBackGroundColor();

        String finalString = "\033[1;0H";

        int currentHeight = 1;

        for(int i=0; i<pixels.length;i++){
            currentPixel = pixels[i];
            currentColor = currentPixel.getColor();
            currentbackGroundColor = currentPixel.getBackGroundColor();

            if((i)%camera.width()==0&&i!=0){
                currentHeight++;
                finalString+="\033["+currentHeight+';'+0+'H';
            } 

            // if(currentPixel==Pixel.NULL_PIXEL){
            //     previousPixel = Pixel.NULL_PIXEL;
            //     previousColor = previousPixel.getColor();
            //     previousBackGroundColor = previousPixel.getBackGroundColor();
            //     continue;
            // }
            
            // if((previousPixel==Pixel.NULL_PIXEL)){
            //     finalString+="\033["+currentHeight+';'+(i%camera.width())+'H';
            // }

            if(currentColor!=previousColor){
                finalString+=TextColor.getTextColor(currentColor);
            }

            if(currentbackGroundColor!=previousBackGroundColor){
                finalString+=TextColor.getBackGroundColor(currentbackGroundColor);
            }

            finalString+=currentPixel.getCharacter();

            previousPixel = currentPixel;
            previousColor = previousPixel.getColor();
            previousBackGroundColor = previousPixel.getBackGroundColor();

        }
        return finalString;
    }

    public Pixel[] getPixels(){
        return this.pixels;
    }
}
