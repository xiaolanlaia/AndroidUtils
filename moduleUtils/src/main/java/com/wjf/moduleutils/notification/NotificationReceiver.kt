package com.wjf.moduleutils.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
/**
 * @Description
 * @Author WuJianFeng
 * @Date 2024/1/18 15:56
 *
 */

class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        // 拦截接收事件
        if (intent.action == ACTION_STOP) {
            // 改变状态
            mIsStop = !mIsStop
            NotificationUtils.newInstance().updateCustomView()
        } else if (intent.action == ACTION_DONE) {
            NotificationUtils.newInstance().cancelNotificationCustom()
        }
    }
}