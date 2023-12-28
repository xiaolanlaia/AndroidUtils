package com.wjf.moduledesignpattern.createType.factory.simple

import android.util.Log

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/28 7:32
 *
 */

class GameA : Game {
    override fun play(): String {
        return this.javaClass.simpleName
    }
}