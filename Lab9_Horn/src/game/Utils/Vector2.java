package game.Utils;

public class Vector2 {
    public float x;
    public float y;

    public Vector2(){
        this.x=0;
        this.y=0;
    }

    public Vector2(float x, float y){
        this.x=x;
        this.y=y;
    }

    public static Vector2 add(Vector2 vec1, Vector2 vec2){
        return new Vector2(vec1.x+vec2.x,  // x
                           vec1.y+vec2.y); // y
    }

    public static Vector2 subtract(Vector2 vec1, Vector2 vec2){
        return new Vector2(vec1.x-vec2.x,  // x
                           vec1.y-vec2.y); // y
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "["+x+","+y+"]";
    }
}
