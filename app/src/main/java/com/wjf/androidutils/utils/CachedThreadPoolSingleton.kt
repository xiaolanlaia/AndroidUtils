package com.wjf.androidutils.utils

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

object CachedThreadPoolSingleton {
    @Volatile
    private var cachedThreadPool: ExecutorService? = null
    val instance: ExecutorService?
        get() {
            if (cachedThreadPool == null) {
                synchronized(CachedThreadPoolSingleton::class.java) {
                    if (cachedThreadPool == null) {
                        cachedThreadPool = Executors.newCachedThreadPool()
                    }
                }
            }
            return cachedThreadPool
        }

    fun shutdown() {
        if (cachedThreadPool != null) {
            cachedThreadPool!!.shutdown()
            cachedThreadPool = null
        }
    }
}