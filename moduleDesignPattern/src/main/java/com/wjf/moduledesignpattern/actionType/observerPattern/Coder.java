package com.wjf.moduledesignpattern.actionType.observerPattern;

import android.util.Log;

import java.util.Observable;
import java.util.Observer;

public class Coder implements Observer {
    public String name;
    public Coder(String name){
        this.name = name;
    }
    @Override
    public void update(Observable o, Object arg) {
        Log.d("__Coder-update","name = "+name+ " arg = " +arg);
    }

    @Override
    public String toString() {
        return "Coder{" +
                "name='" + name + '\'' +
                '}';
    }
}
