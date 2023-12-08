package com.wjf.moduleutils.handler

import android.os.Handler
import android.os.Looper
import android.os.Message

const val WHAT_CODE = 0
const val TIME_3000 = 3000L
class HandlerUtil private constructor(): Handler(Looper.getMainLooper()) {

    companion object{
        val instance: HandlerUtil by lazy(LazyThreadSafetyMode.NONE) { HandlerUtil() }
    }

    override fun handleMessage(msg: Message) {

        when (msg.what) {

            WHAT_CODE ->{
//                MainActivity.handlerCallback.handlerCallback()
            }
        }
    }

    /**
     * 开启
     */
    fun startHandler(){
        val obtain = Message.obtain()
        obtain.what = WHAT_CODE
        instance.removeMessages(WHAT_CODE)
        instance.sendMessageDelayed(obtain, TIME_3000)
    }

    /**
     * 移除指定消息
     */
    fun removeMsg(){
        instance.removeMessages(WHAT_CODE)
    }

    /**
     * 移除所有消息
     */
    fun clearMsg(){
        instance.removeCallbacksAndMessages(null)
    }
}