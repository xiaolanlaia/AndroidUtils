package com.wjf.androidutils.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


object CoroutineUtils {

    /**
     * 返回job
     */
    fun launch(block: suspend () -> Unit) = CoroutineScope(Dispatchers.Main).launch {
        try {
            block()
        } catch (error: Throwable) {
            print(error.message)
        }
    }

    /**
     * 返回job
     */
    fun launch(block: suspend () -> Unit, error: suspend (Throwable) -> Unit) = CoroutineScope(Dispatchers.Main).launch {
        try {
            block()
        } catch (error: Throwable) {
            print(error.message)
            error(error)
        }
    }

    /**
     * 协程等待
     */
    fun joinLaunch(vararg setFun:() -> Unit){
        CoroutineScope(Dispatchers.Main).launch {
            setFun.forEach {

                launch { it() }.join()
            }
        }
    }

}