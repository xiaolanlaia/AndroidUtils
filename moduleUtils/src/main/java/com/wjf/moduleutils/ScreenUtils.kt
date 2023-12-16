package com.wjf.moduleutils

import android.provider.Settings
import com.wjf.moduleutils.UtilsConstant.utilsContext

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/4 17:21
 *
 */

class ScreenUtils {

    companion object{
        val instance by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { ScreenUtils() }
    }

    /**
     * 获取状态栏高度
     */
    fun getStatusBarHeight(): Int {
        var result = 0
        val resourceId =
            utilsContext.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = utilsContext.resources.getDimensionPixelSize(resourceId)
        }
        return result
    }


    /**
     * 获取设备宽度（px）
     */
    fun getDeviceWidth(): Int {
        return utilsContext.resources.displayMetrics.widthPixels
    }

    /**
     * 获取设备高度（px）
     */
    fun getDeviceHeight(): Int {
        return utilsContext.resources.displayMetrics.heightPixels
    }


    /**
     * 获取系统屏幕亮度(0-255)
     */
    fun getScreenBrightness(): Int {
        try {
            return Settings.System.getInt(utilsContext.contentResolver, Settings.System.SCREEN_BRIGHTNESS)
        } catch (e: Settings.SettingNotFoundException) {
            e.printStackTrace()
        }
        return 0
    }




}