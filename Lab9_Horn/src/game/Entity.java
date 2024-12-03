package game;

import java.util.ArrayList;
import java.util.List;

import game.Components.Component;
import game.Components.TextComponent;
import game.Components.Transform;
import game.Utils.Components;

public class Entity {

    public String name;
    public Transform position;
    private int zIndex;
    private List<Component> components;
    private List<TextComponent> textArray;
    

    public Entity(String name){
        this.zIndex = 0;
        this.name = name;
        this.position = new Transform();
        this.components = new ArrayList<>();
    }

    public Entity(String name, Transform position, int zIndex){
        this.zIndex = zIndex;
        this.name = name;
        this.position = position;
        this.components = new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    public <T extends Component> T getComponent(String componentName){
        Class<? extends Component> componentClass = Components.get(componentName);
        for (Component component : components) {
            if(componentClass.isAssignableFrom(component.getClass())){
                return (T) component;
            }
        }
        System.out.println("Entity '"+name+"' doesn't Contain Component '" + componentName);
        return null;
    }

    public void addComponent(Component component){
        this.components.add(component);
        component.entity = this;
        getText();
    }

    public <T extends Component> void removeComponent(Class<T> componentClass) {
        for(int i = 0; i < components.size(); i++){
            Component component = components.get(i);
            if(componentClass.isAssignableFrom(component.getClass())){
                components.remove(i);
                getText();
                return;
            }
        }
    }

    public List<TextComponent> getText(){
        if(textArray==null){
            textArray = new ArrayList<>();
            updateTextArray();
        }
        return textArray;
    }

    private void updateTextArray(){
        for (Component component : components) {
            TextComponent text = component.getText();
            if(text!=null){
                textArray.add(text);
            }
        }
    }

    public int zIndex(){
        return this.zIndex;
    }

    public void init(){
        for (int i = 0; i < components.size(); i++) {
            components.get(i).init();
        }
    }

    public void run(){
        for (int i = 0; i < components.size(); i++) {
            components.get(i).run();
        }
    }
}
