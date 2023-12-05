package com.wjf.moduledesignpattern.actionType.strategy.example;

public class BusinessDeal {
    BusinessStrategy businessStrategy;

    public void setBusinessStrategy(BusinessStrategy businessStrategy){
        this.businessStrategy = businessStrategy;
    }

    public void dealBusiness(String infoCode){
        if (businessStrategy != null){

            businessStrategy.dealBusiness(infoCode);

        }
    }
}
