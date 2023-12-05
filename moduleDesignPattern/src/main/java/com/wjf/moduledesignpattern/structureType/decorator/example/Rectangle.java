package com.wjf.moduledesignpattern.structureType.decorator.example;

public class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Shape: Rectangle");
    }
}
