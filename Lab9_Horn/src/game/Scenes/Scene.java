package game.Scenes;
import java.util.ArrayList;
import java.util.List;

import game.Printing.Renderer;
import game.Entity;
import game.Printing.Camera;

public class Scene {

    private Camera camera;
    private Renderer renderer;
    private List<Entity> entities;

    public Scene(){
        this.camera = new Camera();
        this.renderer = new Renderer(camera);
        this.entities = new ArrayList<>();
    }

    public Camera camera(){
        return this.camera;
    }

    public Renderer renderer(){
        return this.renderer;
    }

    public void addEntity(Entity entity){
        entities.add(entity);
        renderer.add(entity);
    }

    public Entity getEntity(String name){
        for (Entity entity : entities) {
            if(entity.name==name){
                return entity;
            }
        }
        System.out.println("Entity with name'"+name+"' does not exist");
        return new Entity(name);
    }
}
