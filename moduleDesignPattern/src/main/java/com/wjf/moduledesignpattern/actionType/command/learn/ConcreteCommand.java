package com.wjf.moduledesignpattern.actionType.command.learn;

/**
 * 将一个接受者对象绑定于一个动作，调用接受者相应的操作，以实现Execute
 */
public class ConcreteCommand implements Command{
    private final Receiver light;

    public ConcreteCommand(Receiver light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }

    @Override
    public void undo() {
        light.turnOff();

    }
}
