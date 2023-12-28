package com.wjf.moduledesignpattern.createType.factory.method.product

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/28 8:17
 *
 */

class MethodGameB : MethodGame {
    override fun play(): String {
        return this.javaClass.simpleName
    }
}