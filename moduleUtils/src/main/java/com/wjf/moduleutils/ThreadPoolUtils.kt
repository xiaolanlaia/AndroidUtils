package com.wjf.moduleutils

import android.os.Build
import java.util.concurrent.BlockingQueue
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/11/24 13:41
 *
 */

class ThreadPoolUtils {

    companion object {
        val instance by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { ThreadPoolUtils() }
    }

    /**
     * 固定大小线程池：可控制并发的线程数，超出的线程会在队列中等待
     * 1、设置核心线程数
     * 2、最大线程数等于核心线程数
     * 3、等待队列无界
     */
    var mFixedThreadPool: ExecutorService? = null
    fun getFixedThreadPool(size: Int = 10): ExecutorService {
        if (mFixedThreadPool == null) {
            mFixedThreadPool = Executors.newFixedThreadPool(size)
        }
        return mFixedThreadPool!!
    }

    fun shutdownFixedThreadPool() {
        mFixedThreadPool?.shutdownNow()
        mFixedThreadPool = null
    }

    /**
     * 可执行延时和周期性任务的线程池：可设置核心线程数，即使空闲也不会被回收
     * 1、设置核心线程数
     * 2、最大线程数无限大
     * 3、等待队列优先队列 -> 有优先级
     */
    private var mScheduledThreadPool: ExecutorService? = null
    fun scheduledThreadPool(size: Int = 10): ExecutorService {
        if (mScheduledThreadPool == null) {
            mScheduledThreadPool = Executors.newScheduledThreadPool(size)
        }
        return mScheduledThreadPool!!
    }

    fun shutdownScheduledThreadPool() {
        mScheduledThreadPool?.shutdownNow()
        mScheduledThreadPool = null
    }

    /**
     * 可缓存线程池：空闲线程60秒后回收
     * 1、核心线程数：0
     * 2、最大线程数：无限大
     * 3、等待队列 SynchronousQueue
     */
    private var mCachedThreadPool: ExecutorService? = null
    fun getCachedThreadPool(): ExecutorService {
        if (mCachedThreadPool == null) {
            mCachedThreadPool = Executors.newCachedThreadPool()
        }
        return mCachedThreadPool!!
    }

    fun shutdownCachedThreadPool() {
        mCachedThreadPool?.shutdownNow()
        mCachedThreadPool = null
    }

    /**
     * 抢占式执行的线程池（执行顺序不确定）
     */
    private var mWorkStealingPool: ExecutorService? = null
    fun getWorkStealingPool(): ExecutorService? {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            return null
        }
        if (mWorkStealingPool == null) {

            mWorkStealingPool = Executors.newWorkStealingPool()
        }
        return mWorkStealingPool!!
    }

    fun shutdownWorkStealingPool() {
        mWorkStealingPool?.shutdownNow()
        mWorkStealingPool = null
    }


    /**
     * 单线程线程池：保证先进先出的执行顺序
     * 1、核心线程数：1
     * 2、最大线程数：1
     * 3、等待队列：无界
     */
    private var mSingleThreadExecutor: ExecutorService? = null
    fun getSingleThreadExecutor(): ExecutorService {
        if (mSingleThreadExecutor == null) {
            mSingleThreadExecutor = Executors.newSingleThreadExecutor()
        }
        return mSingleThreadExecutor!!
    }

    fun shutdownSingleThreadExecutor() {
        mSingleThreadExecutor?.shutdownNow()
        mSingleThreadExecutor = null
    }

    /**
     * 单线程的可执行延迟任务的线程池
     * 1、核心线程数：1
     * 2、最大线程数：无限
     * 3、等待队列：优先队列
     *
     * scheduleAtFixedRate: 若上一个任务执行时间大于 period ，则任务执行完毕后会立即执行下一个任务
     * scheduleWithFixedDelay: 无论上一个任务执行时间是否大于period，任务执行完毕后都会等待 period 再去执行下一个任务
     */
    private var mSingleThreadScheduledExecutor: ScheduledExecutorService? = null
    fun getSingleThreadScheduledExecutor(): ScheduledExecutorService {
        if (mSingleThreadScheduledExecutor == null) {
            mSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor()
        }
        return mSingleThreadScheduledExecutor!!
    }

    fun shutdownSingleThreadScheduledExecutor() {
        mSingleThreadScheduledExecutor?.shutdownNow()
        mSingleThreadScheduledExecutor = null
    }

    /**
     * 自定义线程池
     * corePoolSize：核心线程数
     * maximumPoolSize：最大线程数
     * keepAliveTime：非核心线程空闲保留时间
     * timeUnit：时间单位
     * workQueue：阻塞队列
     */
    private var mThreadPoolExecutor: ThreadPoolExecutor? = null
    fun threadPoolExecutor(
        corePoolSize: Int = 1,
        maximumPoolSize: Int = 1,
        keepAliveTime: Long = 60,
        timeUnit: TimeUnit = TimeUnit.SECONDS,
        workQueue: BlockingQueue<Runnable> = LinkedBlockingQueue(1)
    ): ThreadPoolExecutor {
        if (mThreadPoolExecutor == null) {
            mThreadPoolExecutor = ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                timeUnit,
                workQueue
            )
        }
        return mThreadPoolExecutor!!
    }

    fun shutdownThreadPoolExecutor() {
        mThreadPoolExecutor?.shutdownNow()
        mThreadPoolExecutor = null
    }
}