package com.wjf.moduledesignpattern.createType.singleton;

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2024/1/24 9:46
 */

public class HungrySingleton {
    private HungrySingleton() {
        if (instance != null){
            throw new RuntimeException("不允许反射调用构造器");
        }
    }
    private static HungrySingleton instance = new HungrySingleton();
    public static HungrySingleton getInstance(){
        return instance;
    }


    private Object readResolve(){
        return instance;
    }
}
