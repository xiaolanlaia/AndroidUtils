package com.wjf.moduledesignpattern.actionType.chain.learn;

public abstract class Handler {
    public Handler successor;
    public abstract void handleRequest(String condition);
}
