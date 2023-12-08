package com.wjf.moduleutils

import java.util.concurrent.BlockingQueue
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/11/24 13:41
 *
 */

object ThreadPoolUtils {

    /**
     * 固定大小线程池：可控制并发的线程数，超出的线程会在队列中等待
     * 1、设置核心线程数
     * 2、最大线程数等于核心线程数
     * 3、等待队列无界
     */
    fun fixedThreadPool(size: Int = 10): ExecutorService{
        val fixedThreadPool by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { Executors.newFixedThreadPool(size) }
        return fixedThreadPool

    }

    /**
     * 可执行延时和周期性任务的线程池：可设置核心线程数，即使空闲也不会被回收
     * 1、设置核心线程数
     * 2、最大线程数无限大
     * 3、等待队列优先队列 -> 有优先级
     */
    fun scheduledThreadPool(size: Int = 10): ExecutorService{
        val fixedThreadPool by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { Executors.newScheduledThreadPool(size) }
        return fixedThreadPool

    }

    /**
     * 可缓存线程池：空闲线程60秒后回收
     * 1、核心线程数：0
     * 2、最大线程数：无限大
     * 3、等待队列 SynchronousQueue
     */
    val cachedThreadPool by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { Executors.newCachedThreadPool() }

    /**
     * 抢占式执行的线程池（执行顺序不确定）
     */
    val workStealingPool by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { Executors.newWorkStealingPool() }

    /**
     * 单线程线程池：保证先进先出的执行顺序
     * 1、核心线程数：1
     * 2、最大线程数：1
     * 3、等待队列：无界
     */
    val singleThreadExecutor by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { Executors.newSingleThreadExecutor() }

    /**
     * 单线程的可执行延迟任务的线程池
     * 1、核心线程数：1
     * 2、最大线程数：无限
     * 3、等待队列：优先队列
     *
     * scheduleAtFixedRate: 若上一个任务执行时间大于 period ，则任务执行完毕后会立即执行下一个任务
     * scheduleWithFixedDelay: 无论上一个任务执行时间是否大于period，任务执行完毕后都会等待 period 再去执行下一个任务
     */
    val singleThreadScheduledExecutor by lazy(LazyThreadSafetyMode.NONE) { Executors.newSingleThreadScheduledExecutor() }

    /**
     * 自定义线程池
     * corePoolSize：核心线程数
     * maximumPoolSize：最大线程数
     * keepAliveTime：非核心线程空闲保留时间
     * timeUnit：时间单位
     * workQueue：阻塞队列
     */
    fun threadPoolExecutor(
        corePoolSize: Int = 1,
        maximumPoolSize: Int = 1,
        keepAliveTime: Long = 60,
        timeUnit : TimeUnit = TimeUnit.SECONDS,
        workQueue: BlockingQueue<Runnable> = LinkedBlockingQueue(1)
    ){
        val threadPoolExecutor by lazy(LazyThreadSafetyMode.NONE) {
            ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAliveTime,timeUnit,workQueue)
        }
    }



}