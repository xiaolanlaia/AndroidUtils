package com.wjf.moduledesignpattern.actionType.memotoPattern;

public class Careteker {
    Memoto memoto;

    public void archive(Memoto memoto){
        this.memoto = memoto;
    }

    public Memoto getMemoto(){
        return memoto;
    }
}
