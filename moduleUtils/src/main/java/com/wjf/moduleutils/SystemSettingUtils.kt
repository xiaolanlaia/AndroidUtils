package com.wjf.moduleutils

import android.app.Activity
import android.content.Intent
import android.database.ContentObserver
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.provider.Settings

class SystemSettingUtils {
    companion object{
        val instance by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { SystemSettingUtils() }
    }


    /**
     * 设置当前页面屏幕亮度
     */
    fun setCurrentBrightness(brightness: Int, activity: Activity?) {
        val window = activity?.window
        val lp = window?.attributes
        lp?.screenBrightness = brightness / 255.0f
        window?.attributes = lp
    }

    /**
     * 设置系统屏幕亮度
     * 需要添加权限 android.permission.WRITE_SETTINGS
     */
    fun setAllBrightness(brightness: Int, activity: Activity?, requestCode : Int) {
        if (Settings.System.canWrite(activity)) {
            try {
                //先检测调节模式
                setScreenManualMode(activity)
                //再设置
                Settings.System.putInt(activity?.contentResolver, Settings.System.SCREEN_BRIGHTNESS, brightness)
            } catch (e: Settings.SettingNotFoundException) {
                e.printStackTrace()
            }
        } else {
            ToastUtils.instance.show("没有修改权限")
            // 打开允许修改系统设置权限的页面
            val intent = Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS, Uri.parse("package:${activity?.packageName}"))
            activity?.startActivityForResult(intent, requestCode)
        }
    }

    /**
     * 设置系统亮度调节模式(SCREEN_BRIGHTNESS_MODE)
     * SCREEN_BRIGHTNESS_MODE_MANUAL 手动调节
     * SCREEN_BRIGHTNESS_MODE_AUTOMATIC 自动调节
     */
    private fun setScreenManualMode(activity: Activity?) {
        try {
            //获取当前系统亮度调节模式
            val mode = Settings.System.getInt(activity?.contentResolver, Settings.System.SCREEN_BRIGHTNESS_MODE)
            //如果是自动，则改为手动
            if (mode == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC) {
                Settings.System.putInt(
                    activity?.contentResolver,
                    Settings.System.SCREEN_BRIGHTNESS_MODE,
                    Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL
                )
            }
        } catch (e: Settings.SettingNotFoundException) {
            e.printStackTrace()
        }
    }

    /**
     * 注册监听 系统屏幕亮度变化
     */
    fun registerContentObserver(activity: Activity?) {
        activity?.contentResolver?.registerContentObserver(
            Settings.System.getUriFor(Settings.System.SCREEN_BRIGHTNESS),
            true,
            mBrightnessObserver
        )
    }

    /**
     * 注销监听
     */
    fun unregisterContentObserver(activity: Activity?) {
        activity?.contentResolver?.unregisterContentObserver(mBrightnessObserver)
    }


    /**
     * 监听系统亮度变化
     */
    private lateinit var mBrightnessObserver : ContentObserver
    fun listenerBright(activity: Activity?, brightness: Int){
        mBrightnessObserver = object : ContentObserver(Handler(Looper.getMainLooper())) {
            override fun onChange(selfChange: Boolean) {
                super.onChange(selfChange)

            }
        }
    }
}