package com.wjf.androidutils.utils

import com.wjf.androidutils.MyApplication

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/11/29 10:09
 *
 */

object DeviceUtils {
    /**
     * 获取状态栏高度
     */
    fun getStatusBarHeight(): Int {
        var result = 0
        var resourceId = MyApplication.instance.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = MyApplication.instance.resources.getDimensionPixelSize(resourceId)
        }
        return result
    }
}