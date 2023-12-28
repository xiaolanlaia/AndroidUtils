package com.wjf.moduledesignpattern.createType.factory.abs.factory

import com.wjf.moduledesignpattern.createType.factory.abs.product.Insole
import com.wjf.moduledesignpattern.createType.factory.abs.product.Shoe

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/28 9:01
 *
 */

interface CompleteShopFactory {
    fun createShoe(): Shoe
    fun createInsole(): Insole
}