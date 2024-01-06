package com.wjf.androidutils.origin.service

import android.app.Notification
import android.app.Service
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.wjf.androidutils.R
import com.wjf.androidutils.origin.utils.COMMON_FLAG
/**
 * @Description
 * @Author WuJianFeng
 * @Date 2024/1/6 8:00
 *
 */

class MyForegroundService : Service() {
    companion object{
        const val NOTIFICATION_DOWNLOAD_PROGRESS_ID = 0x0001
    }

    var isRemove = false

    fun createNotification() {
        //使用兼容版本
        val builder: NotificationCompat.Builder = NotificationCompat.Builder(this)
        //设置状态栏的通知图标
        builder.setSmallIcon(R.mipmap.ic_launcher)
        //设置通知栏横条的图标
        builder.setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_feixing))
        //禁止用户点击删除按钮删除
        builder.setAutoCancel(false)
        //禁止滑动删除
        builder.setOngoing(true)
        //右上角的时间显示
        builder.setShowWhen(true)
        //设置通知栏的标题内容
        builder.setContentTitle("I am Foreground Service!!!")
        //创建通知
        val notification: Notification = builder.build()
        //设置为前台服务
        startForeground(NOTIFICATION_DOWNLOAD_PROGRESS_ID, notification)
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        when(intent.extras!!.getInt(COMMON_FLAG)){
            0 -> {
                if (!isRemove) {
                    createNotification()
                }
                isRemove = true
            }
            else -> {
                //移除前台服务
                if (isRemove) {
                    stopForeground(true)
                }
                isRemove = false
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        //移除前台服务
        if (isRemove) {
            stopForeground(true)
        }
        isRemove = false
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}