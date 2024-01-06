package com.wjf.androidutils.origin.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.wjf.moduleutils.LogUtils
import com.wjf.moduleutils.ToastUtils
import javax.annotation.Nullable

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2024/1/5 14:53
 *
 */

class MyBindService : Service() {

    /**
     * 首次创建服务时调用
     * 在调用 onStartCommand() 或 onBind() 之前
     */
    override fun onCreate() {
        LogUtils.d("__MyBindService-onCreate","1")
        super.onCreate()
    }

    /**
     * 绑定服务时才会调用
     */
    @Nullable
    override fun onBind(intent: Intent?): IBinder? {
        LogUtils.d("__MyBindService-onBind","1")
        ToastUtils.instance.show("MyBindService onBind")
        return MyBinder()
    }

    override fun onUnbind(intent: Intent?): Boolean {
        LogUtils.d("__MyBindService-onUnbind","1")
        ToastUtils.instance.show("MyBindService onUnbind")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        ToastUtils.instance.show("MyBindService onDestroy")
        LogUtils.d("__MyBindService-onDestroy","1")
    }

    fun getContent(): String{
        return " → MyBindService"
    }

    inner class MyBinder: Binder(){
        fun getService(): MyBindService{
            return this@MyBindService
        }
    }
}