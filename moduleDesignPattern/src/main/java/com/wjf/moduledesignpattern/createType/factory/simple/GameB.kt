package com.wjf.moduledesignpattern.createType.factory.simple

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/28 7:34
 *
 */

class GameB : Game {
    override fun play(): String {
        return this.javaClass.simpleName
    }
}