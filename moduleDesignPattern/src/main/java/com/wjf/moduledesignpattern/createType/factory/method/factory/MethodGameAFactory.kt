package com.wjf.moduledesignpattern.createType.factory.method.factory

import com.wjf.moduledesignpattern.createType.factory.method.product.MethodGame
import com.wjf.moduledesignpattern.createType.factory.method.product.MethodGameA

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/28 8:21
 *
 */

class MethodGameAFactory : MethodGameFactory {
    override fun createGame(): MethodGame {
        return MethodGameA()
    }
}