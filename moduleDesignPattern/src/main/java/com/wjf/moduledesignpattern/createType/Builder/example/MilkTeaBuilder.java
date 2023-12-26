package com.wjf.moduledesignpattern.createType.Builder.example;

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/26 8:11
 */

public class MilkTeaBuilder {
    private String type;
    private String size = "中杯";
    private boolean pearl = true;
    private boolean ice = false;

    public MilkTeaBuilder type(String type) {
        this.type = type;
        return this;
    }

    public String getType(){
        return type;
    }

    public MilkTeaBuilder size(String size) {
        this.size = size;
        return this;
    }

    public String getSize(){
        return size;
    }

    public MilkTea build() {
        return new MilkTea(this);
    }
}
