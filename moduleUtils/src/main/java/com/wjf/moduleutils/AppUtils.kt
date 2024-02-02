package com.wjf.moduleutils

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.os.Process

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2024/2/1 18:59
 *
 */

object AppUtils {

    /**
     * 主动退出并重启App
     */
    @SuppressLint("UnspecifiedImmutableFlag")
    fun restartApp() {
        val context = UtilsConstant.utilsContext
        // 获取启动的intent
        val intent = context.packageManager.getLaunchIntentForPackage(context.packageName)
        val restartIntent: PendingIntent =
            PendingIntent.getActivity(context, 0, intent, 0)
        val mgr = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        //2秒后重启应用
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 2000, restartIntent)
        // 重启应用
        Process.killProcess(0)
    }
}