package com.wjf.modulebluetooth.ble.callback

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.wjf.modulebluetooth.ble.BleDev
import com.wjf.moduleutils.ExceptionUtils

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/14 17:42
 *
 */

object BleCallbackImpl : LifecycleEventObserver{

    private var mBleCallback : BleCallback? = null

    operator fun invoke(mBleCallback : BleCallback?) : BleCallbackImpl{
        this.mBleCallback = mBleCallback
        return this
    }
    operator fun invoke() : BleCallback {
        if (mBleCallback == null){
            ExceptionUtils.instance.getCashHandler().uncaughtException(Thread.currentThread(),Throwable(NullPointerException()))
        }
        return mBleCallback!!
    }

    private fun clearGattCallback(){
        mBleCallback = null
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        if(event == Lifecycle.Event.ON_DESTROY){
            clearGattCallback()
        }
    }
}
interface BleCallback {

    fun connected()
    fun disConnected()
    fun scanning(bleDev: BleDev)
    fun notify(msg : String)
}