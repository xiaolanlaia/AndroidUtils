package com.wjf.moduledesignpattern.structureType.bridgePattern.learn;

public abstract class Abstraction {
    private Implementor implementor;

    public Abstraction(Implementor implementor){
        this.implementor = implementor;
    }

    public void operation(){
        implementor.operationImpl();
    }
}
