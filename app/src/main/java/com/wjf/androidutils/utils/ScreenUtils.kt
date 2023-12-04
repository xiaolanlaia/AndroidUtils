package com.wjf.androidutils.utils

import android.content.Context
import android.os.Build
import android.telephony.TelephonyManager
import com.wjf.androidutils.MyApplication

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/4 17:21
 *
 */

object ScreenUtils {

    /**
     * 获取状态栏高度
     */
    fun getStatusBarHeight(): Int {
        var result = 0
        val resourceId =
            MyApplication.instance.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = MyApplication.instance.resources.getDimensionPixelSize(resourceId)
        }
        return result
    }


    /**
     * 获取设备宽度（px）
     */
    fun getDeviceWidth(): Int {
        return MyApplication.instance.resources.displayMetrics.widthPixels
    }

    /**
     * 获取设备高度（px）
     */
    fun getDeviceHeight(): Int {
        return MyApplication.instance.resources.displayMetrics.heightPixels
    }

    /**
     * 获取设备的唯一标识， 需要 “android.permission.READ_Phone_STATE”权限
     */
    fun getIMEI(): String {
        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            val tm =
                MyApplication.instance.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            val deviceId = tm.deviceId
            deviceId
        } else {
            "UNKNOWN"
        }
    }
}