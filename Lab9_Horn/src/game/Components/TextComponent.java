package game.Components;

import game.Utils.Components;
import game.Utils.Vector2;

public class TextComponent extends Component {
    private int color;
    private Transform position;
    private String text;

    static {
        Components.add("Text",TextComponent.class);
    }

    public TextComponent(String text, Transform position){
        this.color = 0xffffff;
        this.text = text;
        this.position = position;
    }

    public TextComponent(String text, int color, Transform position){
        this.text = text;
        this.color = color;
        this.position = position;
    }

    public void move(Vector2 vector){
        position.movePosition(vector);
    }

    public int getColor(){
        return this.color;
    }

    public Transform getPosition(){
        return this.position;
    }

    public String getString(){
        return this.text;
    }

    @Override
    public TextComponent getText() {
        return this;
    }
}
