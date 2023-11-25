package com.wjf.moduledesignpattern.actionType.mediatorPattern.learn;

import android.util.Log;

public class ConcreteColleagueA extends Colleague {
    public ConcreteColleagueA(Mediator mediator){
        super(mediator);
    }
    @Override
    public void action() {
        Log.d("__action","ConcreteColleagueA");

    }
}
