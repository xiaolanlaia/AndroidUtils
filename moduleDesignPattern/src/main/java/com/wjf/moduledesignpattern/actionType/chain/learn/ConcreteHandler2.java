package com.wjf.moduledesignpattern.actionType.chain.learn;

import android.util.Log;

public class ConcreteHandler2 extends Handler {
    @Override
    public void handleRequest(String condition) {
        if (condition.equals("ConcreteHandler2")){
            Log.d("__handleRequest-3","ConcreteHandler2");
        }else {
            Log.d("__handleRequest-4","ConcreteHandler2");
            successor.handleRequest(condition);
        }
    }
}
