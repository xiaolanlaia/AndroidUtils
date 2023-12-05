package com.wjf.moduledesignpattern.structureType.bridge.example;

public class CircleBridge extends ShapeBridge {
    private int x,y,radius;
    public CircleBridge(int x, int y, int radius, DrawAPI drawAPI) {
        super(drawAPI);
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    @Override
    public void draw() {
        drawAPI.drawCircle(radius,x,y);
    }
}
