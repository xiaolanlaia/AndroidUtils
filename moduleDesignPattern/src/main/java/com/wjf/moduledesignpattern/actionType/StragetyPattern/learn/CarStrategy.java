package com.wjf.moduledesignpattern.actionType.StragetyPattern.learn;

import android.util.Log;

public class CarStrategy implements CalculateStrategy{
    @Override
    public int calculatePrice(int km) {
        int extraTotal = km - 5;
        Log.d("__price-car-1","extraTotal = "+extraTotal);

        int extraFactor = extraTotal / 2;
        Log.d("__price-car-2","extraFactor = "+extraFactor);

        int fraction = extraTotal % 2;
        Log.d("__price-car-3","fraction = "+fraction);

        int price = 2 + extraFactor * 2;
        Log.d("__price-car-4","price = "+price);

        return fraction > 0 ? ++price : price;
    }
}
