package com.wjf.moduledesignpattern.createType.Builder.example;

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/26 8:04
 */

public class MilkTea {
    private final String type;
    private final String size;

    MilkTea(MilkTeaBuilder builder) {
        this.type = builder.getType();
        this.size = builder.getSize();
    }

    public String getType() {
        return type;
    }

    public String getSize() {
        return size;
    }
}

