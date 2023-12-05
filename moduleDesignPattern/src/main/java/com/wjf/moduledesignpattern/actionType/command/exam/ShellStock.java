package com.wjf.moduledesignpattern.actionType.command.exam;

public class ShellStock implements Order {
    private Stock abcStock;
    public ShellStock(Stock abcStock){
        this.abcStock = abcStock;
    }
    @Override
    public void execute() {
        abcStock.shell();
    }
}
