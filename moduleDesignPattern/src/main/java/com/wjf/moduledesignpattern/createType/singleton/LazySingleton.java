package com.wjf.moduledesignpattern.createType.singleton;

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2024/1/24 10:04
 */

public class LazySingleton {

    private LazySingleton() {
        if (instance != null){
            throw new RuntimeException("不允许反射调用构造器");
        }
    }

    private static LazySingleton instance;

    public static LazySingleton getInstance(){
        if (instance == null){
            instance = new LazySingleton();
        }
        return instance;
    }


    private Object readResolve(){
        return instance;
    }


}
