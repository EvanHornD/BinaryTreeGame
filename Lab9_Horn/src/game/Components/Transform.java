package game.Components;

import game.Printing.Printer;
import game.Utils.Vector2;

public class Transform {
    public Vector2 position;
    private boolean isClamped;

    public Transform(){
        super();
        this.position = new Vector2();
    }

    public Transform(Vector2 position){
        this.isClamped = false;
        this.position = position;
    }

    public Transform(Vector2 position, boolean isClamped){
        this.isClamped = isClamped;
        if(isClamped) clampPosition(position);
        this.position = position;
    }

    public int x(){
        return position.x;
    }

    public int y(){
        return position.y;
    }

    public void move(Vector2 movement){
        this.position = Vector2.add(this.position, movement);
        if(isClamped) clampPosition(position);
    }

    public void setPosition(Vector2 newPosition){
        this.position = newPosition;
        if(isClamped) clampPosition(position);
    }

    private static void clampPosition(Vector2 position){
        position.x = Integer.max(0, position.x);
        position.x = Integer.min(Printer.camera.width(), position.x);
        position.y = Integer.max(0, position.y);
        position.y = Integer.min(Printer.camera.height(), position.y);
    }
}
