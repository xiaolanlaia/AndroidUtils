package com.wjf.moduledesignpattern.createType.Builder.example;

import android.text.TextUtils;
import android.util.Log;

public class TodayFool {
    private String meat;
    private String vegetable;
    private String egg;

    public TodayFool setMeat(String meat) {
        this.meat = meat;
        return this;
    }

    public TodayFool setVegetable(String vegetable) {
        this.vegetable = vegetable;
        return this;
    }

    public TodayFool setEgg(String egg) {
        this.egg = egg;
        return this;
    }

    public void whatDayEat(){
        StringBuilder stringBuilder = new StringBuilder();
        if (!TextUtils.isEmpty(meat)){
            stringBuilder.append(meat).append(",");
        }
        if (!TextUtils.isEmpty(vegetable)){
            stringBuilder.append(vegetable).append(",");
        }
        if (!TextUtils.isEmpty(egg)){
            stringBuilder.append(egg).append(",");
        }
        Log.d("__whatTodayEat",stringBuilder.toString());
    }
}
