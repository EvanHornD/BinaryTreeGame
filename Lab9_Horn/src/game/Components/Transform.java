package game.Components;

import game.Utils.Vector2;

public class Transform {
    public Vector2 position;

    public Transform(){
        super();
        this.position = new Vector2();
    }

    public Transform(Vector2 position){
        this.position = position;
    }

    public int x(){
        return (int)position.x;
    }

    public int y(){
        return (int)position.y;
    }

    public void move(Vector2 movement){
        this.position = Vector2.add(this.position, movement);
    }

    public void setPosition(Vector2 newPosition){
        this.position = newPosition;
    }
}
