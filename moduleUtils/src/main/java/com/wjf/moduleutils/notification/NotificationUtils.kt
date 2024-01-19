package com.wjf.moduleutils.notification

import android.Manifest
import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.wjf.moduleutils.BitmapUtils
import com.wjf.moduleutils.PermissionUtil
import com.wjf.moduleutils.R
import com.wjf.moduleutils.ToastUtils
import java.util.LinkedList

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2024/1/17 17:27
 *
 */
const val CHANNEL_ID_NORMAL          = "渠道id" // 唯一性
const val CHANNEL_NAME_NORMAL        = "渠道名称"
const val CHANNEL_ID_HIGH            = "重要渠道id"
const val CHANNEL_NAME_HIGH          = "重要通知"
const val CHANNEL_ID_PROGRESS        = "进度条渠道id"
const val CHANNEL_NAME_PROGRESS      = "进度条通知"
const val CHANNEL_ID_BIG_TEXT        = "大文本渠道id"
const val CHANNEL_NAME_BIG_TEXT      = "大文本通知"
const val CHANNEL_ID_BIG_IMAGE       = "大图片渠道id"
const val CHANNEL_NAME_BIG_IMG       = "大图片通知"
const val CHANNEL_ID_CUSTOM          = "自定义渠道id"
const val CHANNEL_NAME_CUSTOM        = "自定义通知"
const val NOTIFICATION_ID_NORMAL     = 9001 // 通知id
const val NOTIFICATION_ID_HIGH       = 9002 // 通知id
const val NOTIFICATION_ID_BIG_TEXT   = 9003 // 通知id
const val NOTIFICATION_ID_PROGRESS   = 9004 // 通知id
const val NOTIFICATION_ID_BIG_IMG    = 9005 // 通知id
const val NOTIFICATION_ID_CUSTOM     = 9006 // 通知id
const val ACTION_STOP                = "com.notification.stop" // 暂停继续action
const val ACTION_DONE                = "com.notification.done" // 完成action
var mIsStop = false // 是否在播放 默认未开始

class NotificationUtils private constructor(val context: Context): LifecycleEventObserver {

    companion object{
        private var instance: NotificationUtils? = null
        fun newInstance(context: Context? = null): NotificationUtils{
            if (instance == null && context != null){
                instance = NotificationUtils(context)
            }
            return instance!!
        }
    }

    private var mFlag = 0


    private var mBuilder: NotificationCompat.Builder? = null
    private var mManager: NotificationManager? = null
    private var mReceiver: NotificationReceiver? = null

    init {
        mManager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        try {
            val permissionList = LinkedList<String>().apply {
                add(Manifest.permission.POST_NOTIFICATIONS)
            }
            PermissionUtil.instance.requestPermissions(context as Activity,permissionList,1)
        } catch (e: Exception) {
            ToastUtils.instance.show("通知权限请求失败")
        }
    }

    /**
     * 创建广播接收器
     */
    private fun createReceiver() {
        val intentFilter = IntentFilter()
        // 添加接收事件监听
        intentFilter.addAction(ACTION_STOP)
        intentFilter.addAction(ACTION_DONE)
        mReceiver = NotificationReceiver()
        // 注册广播
        context.registerReceiver(mReceiver, intentFilter)
    }


    /**
     * 普通通知
     */
    fun notificationNormal(cls: Any? = null) {
        createNotification(
            channelId = CHANNEL_ID_NORMAL,
            channelName = CHANNEL_NAME_NORMAL,
            notificationId = NOTIFICATION_ID_NORMAL,
            title = "普通通知",
            content = "普通通知内容",
            cls = cls)
    }

    /**
     * 重要通知
     */
    fun notificationHigh(cls: Any? = null) {
        createNotification(
            channelId = CHANNEL_ID_HIGH,
            channelName = CHANNEL_NAME_HIGH,
            notificationId = NOTIFICATION_ID_HIGH,
            importanceType = NotificationManager.IMPORTANCE_HIGH,
            cls = cls,
            title = "重要通知",
            content = "重要通知内容")
    }

    /**
     * 进度条通知
     */
    fun notificationProgress() {
        val progressMax = 100
        val progressCurrent = 30
        createNotification(
            channelId = CHANNEL_ID_PROGRESS,
            channelName = CHANNEL_NAME_PROGRESS,
            notificationId = NOTIFICATION_ID_PROGRESS,
            importanceType = NotificationManager.IMPORTANCE_HIGH,
            title = "进度通知",
            content = "下载中：$progressCurrent%",
            progressMax = progressMax,
            progressCurrent = progressCurrent)
    }

    /**
     * 更新进度条通知
     * 1.更新进度：修改进度值即可
     * 2.下载完成：总进度与当前进度都设置为0即可，同时更新文案
     */
    fun updateNotificationProgress(progressCurrent: Int) {
        if (mBuilder != null) {
            val progressMax = 100

            if (progressCurrent < 100){
                // 1.更新进度
                mBuilder?.setContentText("下载中：$progressCurrent%")?.setProgress(progressMax, progressCurrent, false)
            }else{
                // 2.下载完成
                mBuilder?.setContentText("下载完成！")?.setProgress(0, 0, false)
            }
            mManager?.notify(NOTIFICATION_ID_PROGRESS, mBuilder?.build())
            ToastUtils.instance.show("已更新进度到$progressCurrent%")
        } else {
            ToastUtils.instance.show("请先发一条进度条通知")
        }
    }


    /**
     * 大文本通知
     */
    fun notificationBigText() {
        val bigText =
            "A notification is a message that Android displays outside your app's UI to provide the user with reminders, communication from other people, or other timely information from your app. Users can tap the notification to open your app or take an action directly from the notification."

        createNotification(
            channelId = CHANNEL_ID_BIG_TEXT,
            channelName = CHANNEL_NAME_BIG_TEXT,
            notificationId = NOTIFICATION_ID_BIG_TEXT,
            importanceType = NotificationManager.IMPORTANCE_HIGH,
            title = "大文本通知",
            content = bigText)
    }

    /**
     * 大图片通知
     */
    fun notificationBigImage() {
        createNotification(
            channelId = CHANNEL_ID_BIG_IMAGE,
            channelName = CHANNEL_NAME_BIG_IMG,
            notificationId = NOTIFICATION_ID_BIG_IMG,
            importanceType = NotificationManager.IMPORTANCE_HIGH,
            title = "大图片通知",
            content = "展开看看查看大图",
            bigImg = R.mipmap.ic_launcher)
    }


    /**
     * 自定义通知
     */
    fun notificationCustom() {


        // 适配12.0及以上
        mFlag = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.FLAG_IMMUTABLE
        } else {
            PendingIntent.FLAG_UPDATE_CURRENT
        }

        // 添加自定义通知view
        val viewLarge = RemoteViews(context.packageName, R.layout.layout_notification)

        // 添加暂停继续事件
        val intentStop = Intent(ACTION_STOP)
        val pendingIntentStop = PendingIntent.getBroadcast(context, 0, intentStop, mFlag)
        viewLarge.setOnClickPendingIntent(R.id.btn_stop, pendingIntentStop)

        // 添加完成事件
        val intentDone = Intent(ACTION_DONE)
        val pendingIntentDone = PendingIntent.getBroadcast(context, 0, intentDone, mFlag)
        viewLarge.setOnClickPendingIntent(R.id.btn_done, pendingIntentDone)


        createNotification(
            channelId = CHANNEL_ID_CUSTOM,
            channelName = CHANNEL_NAME_CUSTOM,
            notificationId = NOTIFICATION_ID_CUSTOM,
            importanceType = NotificationManager.IMPORTANCE_HIGH,
            title = "大图片通知",
            content = "展开看看查看大图",
            bigImg = R.mipmap.ic_launcher,
            remoteViews = viewLarge
        )
    }

    private fun createNotification(
        channelId: String,
        channelName: String,
        notificationId: Int,
        title: String,
        content: String,
        cls: Any? = null,
        smallIcon: Int = R.mipmap.ic_launcher,
        largeIcon: Int = R.mipmap.ic_launcher,
        bigImg: Int = 0,
        tipNum: Int = 0,
        progressMax: Int = 0,
        progressCurrent: Int = 0,
        remoteViews: RemoteViews? = null,
        category: String = NotificationCompat.CATEGORY_MESSAGE,
        priority: Int = NotificationCompat.PRIORITY_DEFAULT,
        visibility: Int = NotificationCompat.VISIBILITY_PRIVATE,
        description: String = "描述",
        showBadge: Boolean = false,
        autoCancel: Boolean = true,
        importanceType: Int = NotificationManager.IMPORTANCE_DEFAULT
    ){
        // 适配8.0及以上 创建渠道
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, channelName, importanceType).apply {
                this.description = description
                // 是否在桌面显示角标
                this.setShowBadge(showBadge)
            }
            mManager?.createNotificationChannel(channel)
        }

        // 构建配置
        mBuilder = NotificationCompat.Builder(context, channelId)
            .setContentTitle(title) // 标题
            .setSmallIcon(smallIcon) // 小图标
            .setNumber(tipNum)
            .setLargeIcon(BitmapUtils.instance.resource2Bitmap(largeIcon))
            .setPriority(priority) // 7.0 设置优先级
            .setCategory(category) // 通知类别，"勿扰模式"时系统会决定要不要显示你的通知
            .setVisibility(visibility) // 屏幕可见性，锁屏时，显示icon和标题，内容隐藏
            .setAutoCancel(autoCancel) // 是否自动消失（点击）or mManager.cancel(mNormalNotificationId)、cancelAll、setTimeoutAfter()

        when(channelId){
            CHANNEL_ID_BIG_TEXT -> {
                mBuilder?.setStyle(NotificationCompat.BigTextStyle().bigText(content))
            }

            CHANNEL_ID_BIG_IMAGE -> {
                mBuilder?.setContentText(content) // 文本
                mBuilder?.setStyle(NotificationCompat.BigPictureStyle().bigPicture(BitmapUtils.instance.resource2Bitmap(bigImg)))
            }

            CHANNEL_ID_PROGRESS -> {
                mBuilder?.setContentText(content)
                // 第3个参数indeterminate，false表示确定的进度，比如100，true表示不确定的进度，会一直显示进度动画，直到更新状态下载完成，或删除通知
                mBuilder?.setProgress(progressMax, progressCurrent, false)
            }

            CHANNEL_ID_CUSTOM -> {
                mBuilder?.setContentText(content)
                mBuilder?.setCustomBigContentView(remoteViews)// 设置自定义通知view
                mBuilder?.setCustomContentView(remoteViews)
            }

            else -> {
                mBuilder?.setContentText(content)
            }
        }

        if (cls != null){
            // 点击意图 // setDeleteIntent 移除意图
            val intent = Intent(context, cls::class.java)
            val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
            // 跳转配置
            mBuilder?.setContentIntent(pendingIntent)
        }

        // 发起通知
        mManager?.notify(notificationId, mBuilder?.build())
    }

    /**
     * 更新自定义通知
     */
    fun updateNotificationCustom() {

        // 发送通知 更新状态及UI
        context.sendBroadcast(Intent(ACTION_STOP))
    }

    /**
     * 取消自定义通知
     */
    fun cancelNotificationCustom() {
        mManager?.cancel(NOTIFICATION_ID_CUSTOM)
    }

    /**
     * 完成自定义通知
     */
    fun doneNotificationCustom() {
        ToastUtils.instance.show("完成")
        context.sendBroadcast(Intent(ACTION_DONE))
    }


    /**
     * 更新自定义通知View
     */
    fun updateCustomView() {
        if (mBuilder != null) {
            val views = RemoteViews(context.packageName, R.layout.layout_notification)
            val intentUpdate = Intent(ACTION_STOP)
            val pendingIntentUpdate = PendingIntent.getBroadcast(context, 0, intentUpdate, mFlag)
            views.setOnClickPendingIntent(R.id.btn_stop, pendingIntentUpdate)
            // 根据状态更新UI
            if (mIsStop) {
                views.setTextViewText(R.id.tv_status, "那些你很冒险的梦-停止播放")
                views.setTextViewText(R.id.btn_stop, "继续")
                ToastUtils.instance.show("继续")

            } else {
                views.setTextViewText(R.id.tv_status, "那些你很冒险的梦-正在播放")
                views.setTextViewText(R.id.btn_stop, "暂停")
                ToastUtils.instance.show("暂停")
            }

            mBuilder?.setCustomContentView(views)?.setCustomBigContentView(views)
            // 重新发起通知更新UI，注意：必须得是同一个通知id，即mCustomNotificationId
            mManager?.notify(NOTIFICATION_ID_CUSTOM, mBuilder?.build())
        }
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when(event){

            Lifecycle.Event.ON_CREATE -> {
                createReceiver()
            }

            Lifecycle.Event.ON_DESTROY -> {
                mReceiver?.let { context.unregisterReceiver(it) }
                mReceiver = null
                mBuilder = null
                mManager = null
                instance = null
            }
        }
    }

}