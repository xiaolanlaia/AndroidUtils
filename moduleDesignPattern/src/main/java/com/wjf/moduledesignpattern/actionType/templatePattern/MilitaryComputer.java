package com.wjf.moduledesignpattern.actionType.templatePattern;

import android.util.Log;

public class MilitaryComputer extends AbstractComputer {

    @Override
    protected void checkHardware() {
//        super.checkHardware();
        Log.d("__MilitaryComputer","checkHardware");
    }

    @Override
    protected void login() {
//        super.login();
        Log.d("__MilitaryComputer","login");
    }
}
