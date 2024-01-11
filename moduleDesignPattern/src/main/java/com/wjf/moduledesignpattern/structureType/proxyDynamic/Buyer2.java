package com.wjf.moduledesignpattern.structureType.proxyDynamic;

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2024/1/11 9:33
 */

// 小何，真正的想买iPhone的对象 = 目标对象 = 被代理的对象
// 实现抽象目标对象的接口
public class Buyer2 implements Subject {

    @Override
    public void buybuybuy() {
        System.out.println("小何要买iPhone");

    }

}
