package com.wjf.moduleutils

import android.app.Activity
import android.app.Application
import android.app.Application.ActivityLifecycleCallbacks
import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.WindowManager

/**
 * 使用 ScreenAdaptUtils(this, 1920f, ORIENT_WIDTH).register()
 *
 */
const val ORIENT_WIDTH = 0
const val ORIENT_HEIGHT = 1
class ScreenAdaptUtils(
    private val mApplication: Application,
    private val adapterSize: Float = 1080f,
    private val adapterOrient: Int = ORIENT_WIDTH
) {
    private val activityLifecycleCallbacks: ActivityLifecycleCallbacks

    init {
        activityLifecycleCallbacks = object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                //开启Activity才执行
                resetDensity(activity, adapterSize, adapterOrient)
            }

            override fun onActivityStarted(activity: Activity) {}
            override fun onActivityResumed(activity: Activity) {}
            override fun onActivityPaused(activity: Activity) {}
            override fun onActivityStopped(activity: Activity) {}
            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
            override fun onActivityDestroyed(activity: Activity) {}
        }
    }

    /**
     * 注册
     */
    fun register() {
        resetDensity(mApplication, adapterSize, adapterOrient)
        mApplication.registerActivityLifecycleCallbacks(activityLifecycleCallbacks)
    }

    /**
     * 注销
     */
    fun unregister() {
        //设置为默认
        mApplication.resources.displayMetrics.setToDefaults()
        mApplication.unregisterActivityLifecycleCallbacks(activityLifecycleCallbacks)
    }

    /**
     * dp适配 getResources().getDisplayMetrics().density
     * sp适配 getResources().getDisplayMetrics().scaledDensity
     * pt适配 getResources().getDisplayMetrics().xdpi
     * @param context
     * @param adapterSize ui设计图的宽度
     */
    private fun resetDensity(context: Context, adapterSize: Float, adapterOrient: Int) {
        val point = Point()
        //获取屏幕的数值
        val dm = context.resources.displayMetrics
        (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.getSize(
            point
        )
        val newDensity = if (adapterOrient == 0) {
            dm.widthPixels / adapterSize
        } else {
            dm.heightPixels / adapterSize
        }
        val newScaledDensity = newDensity * (dm.density / dm.scaledDensity)
        val newDensityDpi = (newDensity * DisplayMetrics.DENSITY_DEFAULT).toInt()
        dm.density = newDensity
        dm.scaledDensity = newScaledDensity
        dm.densityDpi = newDensityDpi
    }
}