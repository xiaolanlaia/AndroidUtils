package com.wjf.moduledesignpattern.actionType.command.learn;


/**
 * 知道如何实施与执行一个与请求相关的操作，任何类都可能作为一个接受者
 */
public class Receiver {

    public void turnOn() {
        System.out.println("Light is on");
    }

    public void turnOff() {
        System.out.println("Light is off");
    }
}
