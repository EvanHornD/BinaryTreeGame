package game.Printing;

import game.Components.Transform;
import game.Utils.Vector2;

public class Camera {

    public Transform position;
    public Vector2 dimensions;

    public Camera(){
        this.position = new Transform();
        this.dimensions = new Vector2();
    }

    public Camera(Transform position){
        this.position = position;
        this.dimensions = new Vector2();
    }

    public Camera(Transform position, Vector2 dimensions){
        this.position = position;
        this.dimensions = dimensions;
    }

    public int x(){
        return position.x();
    }

    public int y(){
        return position.y();
    }

    public int width(){
        return (int)dimensions.x;
    }
    
    public int height(){
        return (int)dimensions.y;
    }
    
    public void setDimensions(Vector2 dimensions){
        this.dimensions = dimensions;
    }
    
    public boolean checkPixelCoords(Vector2 coords){
        //camera coordinates
        int      camX = x();
        int      camY = y();
        int  camWidth = width();
        int camHeight = height();

        //camera boundaries
        int camRight = camX+camWidth;
        int camLeft = camX;
        int camTop = camY;
        int camBottom = camY+camHeight;

        return  (coords.x<camRight&&
                 coords.x>=camLeft&&
                 coords.y>=camTop &&
                 coords.y<camBottom);
    }

    public boolean checkTextureCoords(Transform position, Vector2 dimensions){
        // text coordinates
        int       textX = position.x();
        int       textY = position.y();
        int   textWidth = (int)dimensions.x;
        int  textHeight = (int)dimensions.y;

        //camera coordinates
        int      camX = x();
        int      camY = y();
        int  camWidth = width();
        int camHeight = height();

        //text boundaries
        int textRight = textX+textWidth;
        int textLeft = textX;
        int textTop = textY;
        int textBottom = textY+textHeight;

        //camera boundaries
        int camRight = camX+camWidth;
        int camLeft = camX;
        int camTop = camY;
        int camBottom = camY+camHeight;

        return  (textRight>=camLeft) &&
                (textLeft<camRight) &&
                (textTop<=camBottom) &&
                (textBottom>camTop) ;
    }
}
