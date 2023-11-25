package com.wjf.moduledesignpattern.actionType.interpreterPattern;

public class InterpreterPattern {
    public static Expression getMaleExpression(){
        Expression robert = new TerminalExpression("Robert");
        Expression john = new TerminalExpression("john");
        return new OrExpression(robert,john);
    }

    public static Expression getMarriedWomanExpression(){
        Expression julie = new TerminalExpression("Julie");
        Expression married = new TerminalExpression("married");
        return new AndExpression(julie,married);
    }
}
