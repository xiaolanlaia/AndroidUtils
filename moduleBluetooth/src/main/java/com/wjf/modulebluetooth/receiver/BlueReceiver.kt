package com.wjf.modulebluetooth.receiver

import android.annotation.SuppressLint
import android.bluetooth.BluetoothA2dp
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothHeadset
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import com.wjf.modulebluetooth.callback.BlueCallback
import com.wjf.moduleutils.ExceptionUtils


/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/11 17:27
 *
 */

@SuppressLint("MissingPermission")
class BlueReceiver(context: Context, private val blueCallback : BlueCallback) : BroadcastReceiver() {

    private val pin = "1234"

    init {
        val filter = IntentFilter()
        //蓝牙开关状态
        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED)
        //蓝牙开始搜索
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED)
        //蓝牙搜索结束
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)

        //蓝牙发现新设备(未配对的设备)
        filter.addAction(BluetoothDevice.ACTION_FOUND)
        //在系统弹出配对框之前(确认/输入配对码)
        filter.addAction(BluetoothDevice.ACTION_PAIRING_REQUEST)
        //设备配对状态改变
        filter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED)
        //最底层连接建立:监听注册广播后的蓝牙连接，注册前的连接获取不到
        filter.addAction(BluetoothDevice.ACTION_ACL_CONNECTED)
        //最底层连接断开
        filter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED)

        //BluetoothAdapter连接状态
        filter.addAction(BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED)
        //BluetoothHeadset连接状态
        filter.addAction(BluetoothHeadset.ACTION_CONNECTION_STATE_CHANGED)
        //BluetoothA2dp连接状态
        filter.addAction(BluetoothA2dp.ACTION_CONNECTION_STATE_CHANGED)
        context.registerReceiver(this, filter)
    }

    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action ?: return
        val device = intent.getParcelableExtra<BluetoothDevice>(BluetoothDevice.EXTRA_DEVICE)

        when (action) {
            //蓝牙开关状态
            BluetoothAdapter.ACTION_STATE_CHANGED -> {
                val state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, 0)
            }

            //蓝牙开始搜索
            BluetoothAdapter.ACTION_DISCOVERY_STARTED -> { blueCallback.scanStarted() }
            //蓝牙搜索结束
            BluetoothAdapter.ACTION_DISCOVERY_FINISHED -> { blueCallback.scanFinished() }
            //蓝牙发现新设备(未配对的设备)
            BluetoothDevice.ACTION_FOUND -> {
                val rssi = intent.getShortExtra(BluetoothDevice.EXTRA_RSSI, Short.MAX_VALUE)

                if (device != null){
                    blueCallback.scanning(device)
                }
            }

            //在系统弹出配对框之前(确认/输入配对码)
            BluetoothDevice.ACTION_PAIRING_REQUEST -> {

                if (device != null){
                    try {

                        blueCallback.bondRequest()
                        //确认配对
                        val setPairingConfirmation = device.javaClass.getDeclaredMethod("setPairingConfirmation", Boolean::class.javaPrimitiveType)
                        setPairingConfirmation.invoke(device,true)

                        //终止有序广播:如果没有将广播终止，则会出现一个一闪而过的配对框
                        abortBroadcast()
                        //进行配对
                        val pin = BluetoothDevice::class.java.getMethod("convertPinToBytes", String::class.java).invoke(BluetoothDevice::class.java, pin) as ByteArray
                        val removeBondMethod = device.javaClass.getMethod("setPin", ByteArray::class.java)
                        removeBondMethod.invoke(device, pin)
                    } catch (e: Exception) {
                        ExceptionUtils.instance.getCashHandler().uncaughtException(Thread.currentThread(),e.fillInStackTrace())
                    }

                }
            }
            //设备配对状态改变
            BluetoothDevice.ACTION_BOND_STATE_CHANGED -> {

                when(device?.bondState){
                    //取消配对
                    BluetoothDevice.BOND_NONE -> { blueCallback.bondFail() }
                    //配对中
                    BluetoothDevice.BOND_BONDING -> { blueCallback.bonding() }
                    //配对成功
                    BluetoothDevice.BOND_BONDED -> { blueCallback.bondSuccess() }
                }
            }

            //最底层连接建立:监听注册广播后的蓝牙连接，注册前的连接获取不到
            BluetoothDevice.ACTION_ACL_CONNECTED -> { blueCallback.connected() }
            //最底层连接断开
            BluetoothDevice.ACTION_ACL_DISCONNECTED -> { blueCallback.disconnected() }
            //BluetoothA2dp连接状态
            BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED -> {}

            BluetoothHeadset.ACTION_CONNECTION_STATE_CHANGED -> {}

            BluetoothA2dp.ACTION_CONNECTION_STATE_CHANGED -> {}
        }
    }
}