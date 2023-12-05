package com.wjf.moduledesignpattern.actionType.visitor.learn;

import java.util.Random;

public abstract class Staff {
    public String name;
    public int kpi;
    public Staff(String name){
        this.name = name;
        kpi = new Random().nextInt(10);
    }

    public abstract void accept(Visitor visitor);
}
