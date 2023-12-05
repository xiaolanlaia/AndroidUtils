package com.wjf.moduledesignpattern.structureType.composite;

import android.util.Log;

public class Leaf extends Component {
    public Leaf(String name) {
        super(name);
    }

    @Override
    public void doSomething() {
        Log.d("__doSomething-Leaf",name);
    }
}
