package com.wjf.moduledesignpattern.actionType.command.learn;

public class Invoker {
    private Command command;

    public Invoker(Command command){
        this.command = command;
    }

    public void action(){
        command.execute();
    }
    public void undo(){
        command.undo();
    }
}
