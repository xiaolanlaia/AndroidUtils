package com.wjf.moduledesignpattern.actionType.strategy.learn;

import android.util.Log;

public class BusStrategy implements CalculateStrategy {
    @Override
    public int calculatePrice(int km) {
        int extraTotal = km - 10;
        Log.d("__price-bus-1","extraTotal = "+extraTotal);
        int extraFactor = extraTotal / 5;
        Log.d("__price-bus-2","extraFactor = "+extraFactor);

        int fraction = extraTotal % 5;
        Log.d("__price-bus-3","fraction = "+fraction);

        int price = 1 + extraFactor * 1;
        Log.d("__price-bus-4","price = "+price);
        Log.d("__price-bus-12","1");

        return fraction > 0 ? ++price : price;
    }
}
