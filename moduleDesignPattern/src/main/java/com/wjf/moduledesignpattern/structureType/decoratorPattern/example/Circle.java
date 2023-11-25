package com.wjf.moduledesignpattern.structureType.decoratorPattern.example;

public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Shape: Circle");
    }
}
