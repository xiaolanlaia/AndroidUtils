package com.wjf.moduledesignpattern.structureType.adapter.learn;

public class VoltAdapter extends Volt220 implements FiveVolt{
    @Override
    public int getVolt5() {
        return 5;
    }
}
