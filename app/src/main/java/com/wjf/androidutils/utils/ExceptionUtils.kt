package com.wjf.androidutils.utils

import android.os.Handler
import android.os.Looper

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/6 8:21
 *
 */

class ExceptionUtils private constructor(){

    private lateinit var cashHandler : CrashHandler
    companion object{
        val instance by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { ExceptionUtils() }
    }

    operator fun invoke(crashHandler : CrashHandler){
        setCrashHandler(crashHandler)
    }

    private fun setCrashHandler(crashHandler : CrashHandler){
        this.cashHandler = crashHandler
        Handler(Looper.getMainLooper()).post {
            while (true){
                try {
                    Looper.loop()
                } catch (e: Exception) {
                    //捕获异常处理
                    cashHandler.uncaughtException(Looper.getMainLooper().thread, e)

                }
            }
        }

        Thread.setDefaultUncaughtExceptionHandler { t, e ->
            //捕获异常处理
            cashHandler.uncaughtException(t, e)
        }
    }

    fun getCashHandler() : CrashHandler{
        return cashHandler
    }

    interface CrashHandler {
        fun uncaughtException(t : Thread, e : Throwable)
    }
}