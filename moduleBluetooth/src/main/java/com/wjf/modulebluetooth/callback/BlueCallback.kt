package com.wjf.modulebluetooth.callback

import android.bluetooth.BluetoothDevice

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/11 17:29
 *
 */

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
}