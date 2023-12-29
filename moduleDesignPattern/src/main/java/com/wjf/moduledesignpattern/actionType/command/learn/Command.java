package com.wjf.moduledesignpattern.actionType.command.learn;

public interface Command {
    /**
     * 执行
     */
    void execute();

    /**
     * 撤销
     */
    void undo();
}
