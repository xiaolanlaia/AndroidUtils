package com.wjf.moduledesignpattern.createType.factory.method.factory

import com.wjf.moduledesignpattern.createType.factory.method.product.MethodGame

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/28 8:20
 *
 */

interface MethodGameFactory {
    fun createGame(): MethodGame
}