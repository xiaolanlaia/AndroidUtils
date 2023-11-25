package com.wjf.moduledesignpattern.structureType.decoratorPattern.learn;

public abstract class Decorator extends Component {

    private Component component;

    public Decorator(Component component){
        this.component = component;
    }

    @Override
    public void operate() {
        component.operate();
    }
}
