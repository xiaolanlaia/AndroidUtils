package com.wjf.moduledesignpattern.createType.factory.abs.factory

import com.wjf.moduledesignpattern.createType.factory.abs.product.Insole
import com.wjf.moduledesignpattern.createType.factory.abs.product.LargeInsole
import com.wjf.moduledesignpattern.createType.factory.abs.product.LargeShoe
import com.wjf.moduledesignpattern.createType.factory.abs.product.Shoe

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/28 9:03
 *
 */

class CompleteLargeShoeFactory : CompleteShopFactory {
    override fun createShoe(): Shoe {
        return LargeShoe()
    }

    override fun createInsole(): Insole {
        return LargeInsole()
    }
}