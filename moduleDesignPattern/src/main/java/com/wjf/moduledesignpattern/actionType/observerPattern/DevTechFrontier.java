package com.wjf.moduledesignpattern.actionType.observerPattern;

import java.util.Observable;

public class DevTechFrontier extends Observable {

    public void postNewPublication(String content){
        setChanged();
        notifyObservers(content);
    }
}
