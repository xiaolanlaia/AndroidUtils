package com.wjf.moduledesignpattern.actionType.visitor.learn;

public interface Visitor {
    void visit(Engineer engineer);
    void visit(Manager manager);
}
