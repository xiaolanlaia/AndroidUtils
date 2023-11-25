package com.wjf.moduledesignpattern.createType.FactoryPatternAbstract;

import android.util.Log;

public class ProductAConcrete extends ProductAAbstract{
    @Override
    public void method() {
        Log.d("__method","ProductAConcrete");
    }
}
