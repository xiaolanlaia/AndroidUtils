package com.wjf.androidutils

import android.app.Application
import android.content.Context
import com.vision.moduleroom.RoomApplication
import com.wjf.modulebluetooth.BlueConstant
import com.wjf.moduleimgloader.utils.ImgLoaderConstant
import com.wjf.modulesocket.utils.SocketConstant
import com.wjf.moduleutils.ExceptionUtils
import com.wjf.moduleutils.LogUtils
import com.wjf.moduleutils.UtilsConstant
import com.wjf.moduleutils.ToastUtils
import com.wjf.moduleutils.persistent.MMKVUtils
import dagger.hilt.android.HiltAndroidApp

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/11/14 11:01
 *
 * 记得在 AndroidManifest 中注册
 */
/**
 * 1、告知Hilt该应用程序将使用Hilt进行依赖注入
 * 2、使用 Hilt 的应用都必须包含一个带有 @HiltAndroidApp 注解的 Application 类
 */
@HiltAndroidApp
class MyApplication : Application() {

    companion object{
        lateinit var instance: MyApplication
    }

    var roomApplication: RoomApplication? = null

    override fun onCreate() {
        super.onCreate()
        instance = this
        UtilsConstant.utilsContext = this
        ImgLoaderConstant.imgLoaderContext = this
        BlueConstant.blueContext = this
        SocketConstant.socketContext = this

        if (roomApplication != null) {
            roomApplication?.onCreate()
        }

        ExceptionUtils.instance(object : ExceptionUtils.CrashHandler {
            override fun uncaughtException(t: Thread, e: Throwable) {
                ToastUtils.instance.show("报错了")
                LogUtils.d("__unCatchException-1", "${e.message}")
            }
        })

        MMKVUtils.instance.init()
        ToastUtils.instance.setToastTextSize()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        roomApplication = getRoomApplicationInstance(this)

        try {

            val method = this.javaClass.getDeclaredMethod("attach", Context::class.java)
            if (method != null) {
                method.isAccessible = true
                method.invoke(roomApplication, baseContext)
            }
        } catch (e: Exception) {
        }
    }

    private fun getRoomApplicationInstance(context: Context) : RoomApplication?{
        try {

            if (roomApplication == null){
                val classLoader = context.classLoader
                if (classLoader != null){
                    val mClass = classLoader.loadClass(RoomApplication::javaClass.name)
                    if (mClass != null){
                        roomApplication = mClass.newInstance() as RoomApplication
                    }
                }
            }
        } catch (e: Exception) {
        }
        return roomApplication
    }
}