package com.wjf.moduledesignpattern.actionType.memoto;

import android.util.Log;

public class CallOfDuty {
    private int checkPoint = 1;
    private int lifeValue = 100;
    private String weapon = "沙漠之鹰";

    public void play(){
        Log.d("__CallOfDuty-play-1","checkPoint = "+checkPoint);
        lifeValue -= 10;
        Log.d("__CallOfDuty-play-2","进度升级啦");
        checkPoint++;
        Log.d("__CallOfDuty-play-3","checkPoint = "+checkPoint);
    }

    public void quit(){
        Log.d("__CallOfDuty-quit-1","--------------");
        Log.d("__CallOfDuty-quit-2",""+this.toString());
        Log.d("__CallOfDuty-quit-3","推出游戏");
    }

    public Memoto createMemoto(){
        Memoto memoto = new Memoto();
        memoto.checkPoint = checkPoint;
        memoto.lifeValue = lifeValue;
        memoto.weapon = weapon;
        return memoto;
    }

    public void restore(Memoto memoto){
        this.checkPoint = memoto.checkPoint;
        this.lifeValue = memoto.lifeValue;
        this.weapon = memoto.weapon;
        Log.d("__CallOfDuty-restore",this.toString());
    }

    @Override
    public String toString() {
        return "CallOfDuty{" +
                "checkPoint=" + checkPoint +
                ", lifeValue=" + lifeValue +
                ", weapon='" + weapon + '\'' +
                '}';
    }
}
