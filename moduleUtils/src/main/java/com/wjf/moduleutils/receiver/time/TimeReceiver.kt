package com.wjf.moduleutils.receiver.time

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.icu.util.Calendar
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.wjf.moduleutils.TimeUtils

/**
 * 使用：lifecycle.addObserver(TimeReceiver(this))
 */
class TimeReceiver(val context: Context) : BroadcastReceiver(), LifecycleEventObserver {

    override fun onReceive(context: Context, intent: Intent) {
        if (Intent.ACTION_TIME_TICK == intent.action) {
            //更新时间
            TimeCallback.timeInterface?.updateTime(TimeUtils.instance.getTime(pattern = "yyyy-MM-dd HH:mm"))

            val cal = Calendar.getInstance()
            val hour = cal[Calendar.HOUR_OF_DAY]
            val min = cal[Calendar.MINUTE]

        }
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when(event){

            Lifecycle.Event.ON_CREATE -> {
                TimeCallback.timeInterface = context as TimeCallback.TimeInterface
                val timeReceiverFilter = IntentFilter()
                timeReceiverFilter.addAction(Intent.ACTION_TIME_TICK)
                context.registerReceiver(this, timeReceiverFilter)

            }
            Lifecycle.Event.ON_DESTROY -> {
                TimeCallback.timeInterface = null
                context.unregisterReceiver(this)
            }
            else -> {}
        }
    }
}