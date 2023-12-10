package com.wjf.androidutils

import android.app.Application
import com.wjf.moduleimgloader.utils.ImgLoaderConstant
import com.wjf.moduleutils.ExceptionUtils
import com.wjf.moduleutils.LogUtils
import com.wjf.moduleutils.ModuleUtilsConstant
import com.wjf.moduleutils.ToastUtils
import com.wjf.moduleutils.persistent.MMKVUtils

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
        ModuleUtilsConstant.moduleUtilsContext = this
        ImgLoaderConstant.imgLoaderContext = this
        ExceptionUtils.instance(object : ExceptionUtils.CrashHandler {
            override fun uncaughtException(t: Thread, e: Throwable) {
                ToastUtils.show("报错了")
                LogUtils.d("__unCatchException-1", LogUtils.getStackTraceString(e))
            }
        })

        MMKVUtils.init()
        ToastUtils.setToastTextSize()
    }
}