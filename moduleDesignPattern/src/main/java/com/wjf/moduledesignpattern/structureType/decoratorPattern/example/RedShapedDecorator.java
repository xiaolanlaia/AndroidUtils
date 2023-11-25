package com.wjf.moduledesignpattern.structureType.decoratorPattern.example;

public class RedShapedDecorator extends ShapeDecorator {
    public RedShapedDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }

    @Override
    public void draw() {
        decoratedShape.draw();
        setRedBorder(decoratedShape);
    }

    private void setRedBorder(Shape decorateShape){
        System.out.println("Border Color: Red");
    }
}
