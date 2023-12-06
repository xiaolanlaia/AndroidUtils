package com.wjf.androidutils

import android.app.Application
import com.tencent.mmkv.MMKV
import com.wjf.androidutils.utils.ExceptionUtils
import com.wjf.androidutils.utils.LogUtils
import com.wjf.androidutils.utils.ToastUtils

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/11/14 11:01
 *
 * 记得在 AndroidManifest 中注册
 */

class MyApplication : Application() {

    companion object{
        lateinit var instance: MyApplication
    }
    override fun onCreate() {
        super.onCreate()
        instance = this

        ExceptionUtils.instance(object : ExceptionUtils.CrashHandler {
            override fun uncaughtException(t: Thread, e: Throwable) {
                LogUtils.d("__unCatchException-1", LogUtils.getStackTraceString(e))
            }
        })

        // 初始化
        MMKV.initialize(this)
        ToastUtils.setToastTextSize()
    }
}