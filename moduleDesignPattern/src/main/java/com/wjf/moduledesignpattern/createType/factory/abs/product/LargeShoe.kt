package com.wjf.moduledesignpattern.createType.factory.abs.product

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/28 9:00
 *
 */

class LargeShoe : Shoe {
    override fun createShop(): String {
        return this.javaClass.simpleName
    }
}