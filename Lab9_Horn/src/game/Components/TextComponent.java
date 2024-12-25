package game.Components;

import game.Printing.Texture;
import game.Utils.Components;

public class TextComponent extends Component {
    private Texture texture;

    static {
        Components.add("Text",TextComponent.class);
    }

    public TextComponent(Texture texture){
        this.texture = texture;
    }

    public void setTexture(Texture texture){
        this.texture = texture;
    }
    
    public Transform getPosition(){ return this.entity.position; }

    public Texture getTexture(){ return this.texture; }

}
