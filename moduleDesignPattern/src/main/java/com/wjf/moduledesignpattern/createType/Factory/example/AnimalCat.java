package com.wjf.moduledesignpattern.createType.Factory.example;

import android.util.Log;

public class AnimalCat extends Animal{
    @Override
    public void animalType() {
        Log.d("__createAnimal","AnimalCat");
    }
}
