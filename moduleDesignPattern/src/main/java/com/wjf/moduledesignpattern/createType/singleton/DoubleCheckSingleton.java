package com.wjf.moduledesignpattern.createType.singleton;

import java.io.Serializable;

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2024/1/24 10:13
 */

public class DoubleCheckSingleton implements Serializable {

    private DoubleCheckSingleton() {}

    private static volatile DoubleCheckSingleton instance;

    public static DoubleCheckSingleton getInstance(){
        if (instance == null){
            synchronized (DoubleCheckSingleton.class){
                if (instance == null){
                    instance = new DoubleCheckSingleton();
                }
            }
        }
        return instance;
    }


    private Object readResolve(){
        return getInstance();
    }
}
