package com.wjf.moduledesignpattern.structureType.facedPattern;

public class PhoneImpl implements Phone {
    @Override
    public void dial() {
        System.out.println("打电话");
    }

    @Override
    public void hangup() {
        System.out.println("挂断");
    }
}
