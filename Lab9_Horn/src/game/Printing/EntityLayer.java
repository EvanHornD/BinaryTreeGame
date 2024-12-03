package game.Printing;

import java.util.ArrayList;
import java.util.List;

import game.Entity;
import game.Components.TextComponent;

public class EntityLayer implements Comparable<EntityLayer> {
    private int zIndex;
    private List<Entity> entityArray;
    private TextComponent[] textArray;

    public EntityLayer(int zIndex){
        this.zIndex = zIndex;
        this.entityArray = new ArrayList<>();
    }

    public boolean containsEntity(String name){
        for (Entity entity : entityArray) {
            if(entity.name==name){
                return true;
            }
        }
        return false;
    }

    public Entity getEntity(String name){
        for (Entity entity : entityArray) {
            if(entity.name==name){
                return entity;
            }
        }
        return null;
    }

    public void addEntity(Entity entity){
        entityArray.add(entity);
        updateTextArray();
    }

    public void removeEntity(Entity entity){
        entityArray.remove(entity);
        updateTextArray();
    }

    public int zIndex() {
        return zIndex;
    }

    public TextComponent[] getTextArray() {
        if(textArray==null){
            textArray = updateTextArray();
        }
        return textArray;
    }

    private TextComponent[] updateTextArray(){
        List<TextComponent> textArray = new ArrayList<>();
        for (Entity entity : entityArray) {
            textArray.addAll(entity.getText());
        }
        return textArray.toArray(new TextComponent[0]);
    }

    @Override
    public int compareTo(EntityLayer o) {
        return Integer.compare(this.zIndex, o.zIndex());
    }
    
}
