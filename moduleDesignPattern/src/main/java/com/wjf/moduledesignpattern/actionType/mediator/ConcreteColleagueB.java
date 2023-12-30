package com.wjf.moduledesignpattern.actionType.mediator;

import android.util.Log;

/**
 * 具体同事类
 */
public class ConcreteColleagueB implements Colleague {

    public ConcreteColleagueB() {
        SimpleMediator.getMedium().register(this);
    }

    public void receive() {
        Log.d("__Mediator-ConcreteColleagueB","ConcreteColleagueB收到请求。");
    }
    public void send() {
        Log.d("__Mediator-ConcreteColleagueB","ConcreteColleagueB发出请求。");
        //请中介者转发
        SimpleMediator.getMedium().relay(this);
    }
}
