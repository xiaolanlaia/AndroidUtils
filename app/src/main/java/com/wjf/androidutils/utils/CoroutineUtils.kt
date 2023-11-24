package com.wjf.androidutils.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


object CoroutineUtils {

    /**
     * 返回job
     */
    fun launch(block: suspend () -> Unit) = CoroutineScope(Dispatchers.Main).launch {
        block()
    }

    /**
     * 返回job
     */
    fun launch(block: suspend () -> Unit, error: suspend (Throwable) -> Unit) = CoroutineScope(Dispatchers.Main).launch {
        try {
            block()
        } catch (error: Throwable) {
            error(error)
        }
    }
}