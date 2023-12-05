package com.wjf.moduledesignpattern.createType.Factory.learn;

public class FactoryConcrete extends Factory {
    @Override
    public Product createProduct() {
        return new ProductConcreteA();
    }
}
