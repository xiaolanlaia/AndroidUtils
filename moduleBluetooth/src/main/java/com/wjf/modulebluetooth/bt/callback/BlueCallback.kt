package com.wjf.modulebluetooth.bt.callback

import android.bluetooth.BluetoothDevice
import com.wjf.moduleutils.ExceptionUtils
import java.lang.NullPointerException

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/11 17:29
 *
 */
object BlueCallbackImpl{
    var mBlueCallback : BlueCallback? = null
    fun setBlueCallback(blueCallback : BlueCallback) : BlueCallback {
        mBlueCallback = blueCallback
        return blueCallback
    }

    fun getBlueCallback() : BlueCallback {
        if (mBlueCallback == null){
            ExceptionUtils.instance.getCashHandler().uncaughtException(Thread.currentThread(),Throwable(NullPointerException()))
        }
        return mBlueCallback!!
    }
}

interface BlueCallback {

    //开启扫描
    fun scanStarted()

    //扫描完成
    fun scanFinished()

    //扫描中
    fun scanning(device : BluetoothDevice)

    //配对请求
    fun bondRequest()

    //取消配对
    fun bondFail()

    //配对中
    fun bonding()

    //配对成功
    fun bondSuccess()

    //连接建立
    fun connected()

    //连接断开
    fun disconnected()

    //收到消息更新UI
    fun socketNotify(state: Int, obj: Any?)

    fun notifyState(state : String)
}