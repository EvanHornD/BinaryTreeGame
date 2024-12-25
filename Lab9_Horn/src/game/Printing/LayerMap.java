package game.Printing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import game.Entity;

public class LayerMap {


    private List<EntityLayer> layers;

    public LayerMap(){
        this.layers = new ArrayList<>();
    }

    public void addToLayers(Entity entity){
        if(layers==null){
            layers = new ArrayList<>();
        }
        boolean added = false;
        for (EntityLayer layer : layers) {
            added = addToLayer(layer, entity);
            if(added){
                break;
            }
        }
        if(!added){
            EntityLayer newLayer = new EntityLayer(entity.zIndex());
            newLayer.addEntity(entity);
            layers.add(newLayer);
            Collections.sort(layers);
        }
    }

    private boolean addToLayer(EntityLayer layer, Entity entity){
        if(entity.zIndex()!=layer.zIndex()){
            return false;
        }
        layer.addEntity(entity);
        return true;
    }

    public List<EntityLayer> getLayers(){
        return this.layers;
    }
}
