package com.wjf.moduledesignpattern.actionType.templatePattern;

import android.util.Log;

public class AbstractComputer {

    protected void powerOn(){
        Log.d("__AbstractComputer","powerOn");
    }

    protected void checkHardware(){
        Log.d("__AbstractComputer","checkHardware");
    }

    protected void loadOS(){
        Log.d("__AbstractComputer","loadOS");

    }

    protected void login(){
        Log.d("__AbstractComputer","login");

    }

    public final void startUp(){
        Log.d("__AbstractComputer-1","startUp");
        powerOn();
        checkHardware();
        loadOS();
        login();
        Log.d("__AbstractComputer-2","startUp");

    }
}
