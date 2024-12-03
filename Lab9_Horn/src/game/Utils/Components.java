package game.Utils;

import java.util.HashMap;
import java.util.Map;

import game.Components.*;

public class Components {
    private static final Map<String, Class<? extends Component>> componentClasses = new HashMap<>();

    public static void add(String name, Class<? extends Component> componentClass){
        componentClasses.put(name, componentClass);
    }

    public static Class<? extends Component> get(String componentName){
        if (componentClasses.containsKey(componentName)) {
            return componentClasses.get(componentName);
        } else {
            System.out.println("The Class '"+componentName+"' doesnt Exist");
            return null;
        }
    }
}
