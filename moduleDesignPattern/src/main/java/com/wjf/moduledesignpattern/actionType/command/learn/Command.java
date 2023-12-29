package com.wjf.moduledesignpattern.actionType.command.learn;

/**
 * 声明执行操作的接口
 */
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
