package com.wjf.androidutils.origin.service

import android.app.Service
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import com.wjf.androidutils.origin.utils.COMMON_FLAG
import com.wjf.moduleutils.LogUtils
import com.wjf.moduleutils.ThreadPoolUtils
import com.wjf.moduleutils.ToastUtils
import java.util.concurrent.TimeUnit
import javax.annotation.Nullable

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2024/1/5 15:43
 *
 */

class MyMessageService : Service() {

    companion object {
        const val MSG_SAY_HELLO = 1
    }

    /**
     * 首次创建服务时调用
     * 在调用 onStartCommand() 或 onBind() 之前
     */
    override fun onCreate() {
        LogUtils.d("__MyMessageService-onCreate", "1")
        super.onCreate()
    }

    /**
     * 绑定服务时才会调用
     */
    @Nullable
    override fun onBind(intent: Intent?): IBinder? {
        LogUtils.d("__MyMessageService-onBind", "1")
        ToastUtils.instance.show("MyMessageService onBind")
        return Messenger(IncomingHandler()).binder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        LogUtils.d("__MyMessageService-onUnbind", "1")
        ToastUtils.instance.show("MyMessageService onUnbind")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        ToastUtils.instance.show("MyMessageService onDestroy")
        LogUtils.d("__MyMessageService-onDestroy", "1")
    }

    class IncomingHandler : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                MSG_SAY_HELLO -> {
                    ToastUtils.instance.show("MyMessageService Hello")
                    //向客户端发送消息
                    val client = msg.replyTo
                    val replayMsg = Message.obtain(null, MSG_SAY_HELLO)
                    val bundle = Bundle()
                    bundle.putString(COMMON_FLAG,"replayMsg")
                    replayMsg.data = bundle
                    ThreadPoolUtils.instance.scheduledThreadPool().schedule({
                        try {
                            client.send(replayMsg)
                        } catch (e: Exception) {
                            LogUtils.d("__err-IncomingHandler", "${e.message}")
                        }
                    }, 3, TimeUnit.SECONDS)

                }
            }
        }
    }
}