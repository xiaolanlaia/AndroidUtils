package com.wjf.androidutils.origin.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.wjf.androidutils.origin.utils.COMMON_FLAG
import com.wjf.moduleutils.LogUtils
import com.wjf.moduleutils.ThreadPoolUtils
import com.wjf.moduleutils.ToastUtils
import java.util.concurrent.TimeUnit
import javax.annotation.Nullable

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2024/1/5 7:19
 *
 */

class MyStartService : Service() {

    /**
     * 首次创建服务时调用
     * 在调用 onStartCommand() 或 onBind() 之前
     */
    override fun onCreate() {
        super.onCreate()
        LogUtils.d("__MyStartService-onCreate","1")
    }

    /**
     * 每次通过startService()方法启动Service时都会被回调。
     */
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        LogUtils.d("__MyStartService-onStartCommand","1")
        ToastUtils.instance.show("${intent?.getStringExtra(COMMON_FLAG)}")

        ThreadPoolUtils.instance.scheduledThreadPool().scheduleAtFixedRate({
            LogUtils.d("__MyStartService-schedule","1")
        },0,2,TimeUnit.SECONDS)
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtils.d("__MyStartService-onDestroy","1")
        ToastUtils.instance.show("MyStartService onDestroy")
        ThreadPoolUtils.instance.shutdownScheduledThreadPool()
    }

    /**
     * 绑定服务时才会调用
     */
    @Nullable
    override fun onBind(intent: Intent?): IBinder? { return null }
}