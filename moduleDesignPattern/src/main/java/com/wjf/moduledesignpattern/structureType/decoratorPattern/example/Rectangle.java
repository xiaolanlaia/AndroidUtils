package com.wjf.moduledesignpattern.structureType.decoratorPattern.example;

public class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Shape: Rectangle");
    }
}
