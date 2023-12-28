package com.wjf.moduledesignpattern.createType.factory.abs.product

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/28 8:59
 *
 */

class LargeInsole : Insole {
    override fun createInsole(): String {
        return this.javaClass.simpleName
    }
}