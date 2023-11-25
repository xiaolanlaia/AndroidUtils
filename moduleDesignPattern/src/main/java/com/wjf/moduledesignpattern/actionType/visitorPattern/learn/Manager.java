package com.wjf.moduledesignpattern.actionType.visitorPattern.learn;

import java.util.Random;

public class Manager extends Staff {
    private int products;
    public Manager(String name){
        super(name);
        products = new Random().nextInt(10);
    }
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public int getProducts(){
        return products;
    }
}
