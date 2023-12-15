package com.wjf.modulebluetooth.ble.callback

import com.wjf.moduleutils.ExceptionUtils
import com.wjf.modulebluetooth.ble.BleDev
import java.lang.NullPointerException

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/14 17:42
 *
 */

object BleCallbackImpl{
    private var mBleCallback : BleCallback? = null
    fun setGattCallback(bleCallback : BleCallback) : BleCallback {
        mBleCallback = bleCallback
        return bleCallback
    }

    fun getGattCallback() : BleCallback {
        if (mBleCallback == null){
            ExceptionUtils.instance.getCashHandler().uncaughtException(Thread.currentThread(),Throwable(NullPointerException()))
        }
        return mBleCallback!!
    }
}
interface BleCallback {

    fun connected()
    fun disConnected()
    fun scanning(bleDev: BleDev)
    fun notify(msg : String)
}