package com.wjf.modulebluetooth.bt.callback

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.wjf.modulebluetooth.bt.BlueUtils
import com.wjf.modulebluetooth.bt.receiver.BlueReceiver
import com.wjf.moduleutils.ExceptionUtils
import java.lang.NullPointerException

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/11 17:29
 *
 */
@SuppressLint("StaticFieldLeak")
object BlueCallbackImpl : LifecycleEventObserver{

    private var mBlueCallback : BlueCallback? = null
    private var mBlueReceiver: BlueReceiver? = null
    private var context: Context? = null

    operator fun invoke(context : Context, blueCallback : BlueCallback) : BlueCallbackImpl {
        mBlueCallback = blueCallback
        this.context = context
        return this
    }

    operator fun invoke() : BlueCallback {
        if (mBlueCallback == null){
            ExceptionUtils.instance.getCashHandler().uncaughtException(Thread.currentThread(),Throwable(NullPointerException()))
        }
        return mBlueCallback!!
    }

    private fun clearBlueCallback(){
        mBlueCallback = null
        mBlueReceiver = null
        context = null
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when(event){
            Lifecycle.Event.ON_CREATE -> {

                //注册蓝牙广播
                mBlueReceiver = BlueReceiver(context!!, BlueCallbackImpl())
                BlueUtils.instance.scan()

            }

            Lifecycle.Event.ON_DESTROY -> {
                context?.unregisterReceiver(mBlueReceiver)
                clearBlueCallback()
                BlueUtils.instance.close()

            }

            else -> {}
        }
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