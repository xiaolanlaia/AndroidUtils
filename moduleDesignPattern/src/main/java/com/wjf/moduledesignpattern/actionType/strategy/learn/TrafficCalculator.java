package com.wjf.moduledesignpattern.actionType.strategy.learn;

public class TrafficCalculator {
    CalculateStrategy calculateStrategy;

    public TrafficCalculator setCalculateStrategy(CalculateStrategy calculateStrategy) {
        this.calculateStrategy = calculateStrategy;
        return this;
    }

    public int calculatePrice(int km){
        return calculateStrategy.calculatePrice(km);
    }
}
