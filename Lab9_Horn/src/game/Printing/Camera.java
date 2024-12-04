package game.Printing;

import game.Utils.Vector2;

public class Camera {

    public Vector2 position;
    public Vector2 dimensions;

    public Camera(){
        this.position = new Vector2();
        this.dimensions = new Vector2(128,30);
    }

    public Camera(Vector2 position){
        this.position = position;
        this.dimensions = new Vector2(128,30);
    }

    public Camera(Vector2 position, Vector2 dimensions){
        this.position = position;
        this.dimensions = dimensions;
    }

    public int width(){
        return dimensions.x;
    }
    
    public int height(){
        return dimensions.y;
    }

    public boolean checkCoords(Vector2 coords){
        return  (coords.x<position.y+dimensions.y&&
                 coords.y<position.y+dimensions.y&&
                 coords.x>=position.x            &&
                 coords.y>=position.y            );
    }
}
