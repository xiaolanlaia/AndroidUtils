package com.wjf.moduledesignpattern.createType.factory.method.factory

import com.wjf.moduledesignpattern.createType.factory.method.product.MethodGame
import com.wjf.moduledesignpattern.createType.factory.method.product.MethodGameB

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/28 8:22
 *
 */

class MethodGameBFactory : MethodGameFactory {
    override fun createGame(): MethodGame {
        return MethodGameB()
    }
}