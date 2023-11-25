package com.wjf.moduledesignpattern.actionType.visitorPattern.learn;

import android.util.Log;

public class CEOVisitor implements Visitor {
    @Override
    public void visit(Engineer engineer) {
        Log.d("__CEOVisitor-engineer","name = "+engineer.name+" kpi = "+engineer.kpi);
    }

    @Override
    public void visit(Manager manager) {
        Log.d("__CEOVisitor-manager","name = "+manager.name+" kpi = "+manager.kpi+ " product = "+manager.getProducts());
    }
}
