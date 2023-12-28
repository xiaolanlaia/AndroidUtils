package com.wjf.moduledesignpattern.createType.factory.simple

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/28 7:35
 *
 */

class GameFactory {

    companion object{
        var gameType = 1
    }

    fun createGame(type: Int): Game {
        return when(type){

            1 ->{
                GameA()
            }

            else ->{
                GameB()
            }
        }
    }
}