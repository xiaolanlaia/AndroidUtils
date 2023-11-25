package com.wjf.moduledesignpattern.createType.FactoryPattern.example;

public class FactoryAnimalConcrete extends FactoryAnimal {
    @Override
    public Animal createAnimal() {
        return new AnimalCat();
    }
}
