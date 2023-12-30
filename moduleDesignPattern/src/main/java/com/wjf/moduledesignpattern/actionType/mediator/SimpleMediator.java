package com.wjf.moduledesignpattern.actionType.mediator;

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/30 8:55
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 简单单例中介者
 */
class SimpleMediator {
    private static SimpleMediator smd = new SimpleMediator();
    private List<Colleague> colleagues = new ArrayList<Colleague>();

    private SimpleMediator() {
    }

    public static SimpleMediator getMedium() {
        return (smd);
    }

    public void register(Colleague colleague) {
        if (!colleagues.contains(colleague)) {
            colleagues.add(colleague);
        }
    }

    public void relay(Colleague scl) {
        for (Colleague ob : colleagues) {
            if (!ob.equals(scl)) {
                ((Colleague) ob).receive();
            }
        }
    }
}
