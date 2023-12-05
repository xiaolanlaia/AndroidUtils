package com.wjf.moduledesignpattern.createType.Factory.example;

public class FactoryAnimalConcrete extends FactoryAnimal {
    @Override
    public Animal createAnimal() {
        return new AnimalCat();
    }
}
