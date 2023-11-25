package com.wjf.moduledesignpattern.structureType.compositePattern;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Composite extends Component{

    private List<Component> components = new ArrayList<>();

    public Composite(String name) {
        super(name);
    }

    @Override
    public void doSomething() {
        Log.d("__doSomething-Composite",name);
        if (null != components){
            for (Component c : components){
                c.doSomething();
            }
        }
    }

    public void addChild(Component child){
        components.add(child);
    }

    public void removeChild(Component child){
        components.remove(child);
    }

    public Component getChildren(int index){
        return components.get(index);
    }
}
