package com.wjf.moduledesignpattern.actionType.chainPattern.learn;

import android.util.Log;

public class ConcreteHandler1 extends Handler {

    @Override
    public void handleRequest(String condition) {

        if (condition.equals("ConcreteHandler1")){
            Log.d("__handleRequest-1","ConcreteHandler1");
        }else {
            Log.d("__handleRequest-2","ConcreteHandler1");
            successor.handleRequest(condition);
        }

    }
}
