package com.wjf.moduledesignpattern.actionType.command.learn;

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
