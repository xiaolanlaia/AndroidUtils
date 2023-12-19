package com.wjf.moduleutils.receiver.usb

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.wjf.moduleutils.LogUtils

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/19 16:43
 *
 */
/**
 * 有锁屏的设备收不到
 */
class UsbStatusReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Intent.ACTION_MEDIA_MOUNTED){
            LogUtils.d("__UsbStatusReceiver","1")
//            val it = Intent(context, TitleBarActivity::class.java)
//            it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//            context?.startActivity(it)
        }
    }
}