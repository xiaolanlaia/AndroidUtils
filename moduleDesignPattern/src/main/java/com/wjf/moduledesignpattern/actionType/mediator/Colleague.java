package com.wjf.moduledesignpattern.actionType.mediator;

/**
 * 抽象同事类
 */
public interface Colleague {
    void receive();
    void send();
}
