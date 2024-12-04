package game.Components;

import game.Utils.Components;

public class TextComponent extends Component {
    private int color;
    private int backGroundColor;
    private String text;
    private boolean transparent;

    static {
        Components.add("Text",TextComponent.class);
    }

    public TextComponent(String text){
        this.color = 0xffffff;
        this.backGroundColor = 0;
        this.text = text;
        this.transparent = false;
    }

    public TextComponent(String text, int color){
        this.text = text;
        this.color = color;
        this.backGroundColor = 0;
        this.transparent = false;
    }

    public TextComponent(String text, int color, boolean transparent){
        this.text = text;
        this.color = color;
        this.backGroundColor = 0;
        this.transparent = transparent;
    }

    public TextComponent(String text, boolean transparent){
        this.text = text;
        this.color = 0xffffff;
        this.backGroundColor = 0;
        this.transparent = transparent;
    }

    public TextComponent(String text, int color, int backGroundColor){
        this.text = text;
        this.color = color;
        this.backGroundColor = backGroundColor;
        this.transparent = false;
    }

    public TextComponent(String text, int color, int backGroundColor, boolean transparent){
        this.text = text;
        this.color = color;
        this.backGroundColor = backGroundColor;
        this.transparent = transparent;
    }

    public int getColor(){
        return this.color;
    }

    public Transform getPosition(){
        return this.entity.position;
    }

    public boolean getTransparency(){
        return this.transparent;
    }

    public int getBackGroundColor(){
        return this.backGroundColor;
    }

    public String getString(){
        return this.text;
    }
}
