package com.wjf.moduledesignpattern.actionType.state;

import android.util.Log;

public class TvController implements PowerController {

    TvState tvState;

    public void setTvState(TvState tvState) {
        this.tvState = tvState;
    }

    @Override
    public void powerOn() {
        setTvState(new PowerOnState());
        Log.d("__TvController","开机");
    }

    @Override
    public void powerOff() {
        setTvState(new PowerOffState());
        Log.d("__TvController","关机");
    }

    public void nextChannel(){
        tvState.nextChannel();
    }
    public void preChannel(){
        tvState.preChannel();
    }
    public void turnUp(){
        tvState.turnUp();
    }
    public void turnDown(){
        tvState.turnDown();
    }
}
