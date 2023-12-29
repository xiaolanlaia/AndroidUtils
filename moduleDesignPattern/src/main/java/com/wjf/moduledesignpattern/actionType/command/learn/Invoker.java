package com.wjf.moduledesignpattern.actionType.command.learn;

/**
 * 要求该命令执行这个请求
 */
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
