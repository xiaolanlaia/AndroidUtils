package com.wjf.moduledesignpattern.structureType.bridgePattern.learn;

public class RefineAbstraction extends Abstraction {

    public RefineAbstraction(Implementor implementor) {
        super(implementor);
    }

    public void refinedOperation(){
        //对Abstraction中的方法进行拓展
    }
}
