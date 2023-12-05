package com.wjf.moduledesignpattern.structureType.bridge.example;

public abstract class ShapeBridge {
    protected DrawAPI drawAPI;

    protected ShapeBridge(DrawAPI drawAPI){
        this.drawAPI = drawAPI;
    }

    public abstract void draw();
}
