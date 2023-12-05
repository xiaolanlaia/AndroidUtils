package com.wjf.moduledesignpattern.actionType.memoto;

public class Careteker {
    Memoto memoto;

    public void archive(Memoto memoto){
        this.memoto = memoto;
    }

    public Memoto getMemoto(){
        return memoto;
    }
}
