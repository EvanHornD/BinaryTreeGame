package game.Components;

import game.Entity;

public abstract class Component {

    public Entity entity;

    Component(){
        
    }
    public void init(){}
    public void run(){};
}
