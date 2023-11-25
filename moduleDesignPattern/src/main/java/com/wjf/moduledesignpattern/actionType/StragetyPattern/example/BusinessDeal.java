package com.wjf.moduledesignpattern.actionType.StragetyPattern.example;

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
