package com.wjf.modulebluetooth.ble.callback

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattCallback
import android.bluetooth.BluetoothGattCharacteristic
import android.bluetooth.BluetoothGattDescriptor
import android.bluetooth.BluetoothGattServerCallback
import android.bluetooth.BluetoothGattService
import android.bluetooth.BluetoothProfile
import android.bluetooth.le.AdvertiseCallback
import android.bluetooth.le.AdvertiseSettings
import android.os.SystemClock
import android.util.Log
import com.wjf.modulebluetooth.ble.BleUtils
import java.util.Arrays

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/14 17:40
 *
 */

@SuppressLint("MissingPermission")
class GattCallbackUtils {

    companion object{
        val instance by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { GattCallbackUtils () }
    }


    // 与服务端连接的Callback
    var mBluetoothGattCallback: BluetoothGattCallback = object : BluetoothGattCallback() {

        override fun onConnectionStateChange(gatt: BluetoothGatt, status: Int, newState: Int) {
            val dev = gatt.device
            Log.i("__BleClient-1", String.format("onConnectionStateChange:%s,%s,%s,%s", dev.name, dev.address, status, newState))
            if (status == BluetoothGatt.GATT_SUCCESS && newState == BluetoothProfile.STATE_CONNECTED) {
                BleCallbackImpl.getGattCallback().connected()
                //启动服务发现
                gatt.discoverServices()
            } else {
                BleCallbackImpl.getGattCallback().disConnected()
            }

            BleCallbackImpl.getGattCallback().notify(String.format(if (status == 0) (if (newState == 2) "与[%s]连接成功" else "与[%s]连接断开") else "与[%s]连接出错,错误码:$status", dev))
        }

        override fun onServicesDiscovered(gatt: BluetoothGatt, status: Int) {
            Log.i("__BleClient-2", String.format("onServicesDiscovered:%s,%s,%s", gatt.device.name, gatt.device.address, status))
            if (status == BluetoothGatt.GATT_SUCCESS) { //BLE服务发现成功
                // 遍历获取BLE服务Services/Characteristics/Descriptors的全部UUID
                for (service in gatt.services) {
                    val allUUIDs = StringBuilder(" UUIDs={S=${service.uuid}".trimIndent())
                    for (characteristic in service.characteristics) {
                        allUUIDs.append(",\nC=").append(characteristic.uuid)
                        for (descriptor in characteristic.descriptors) {
                            allUUIDs.append(",\nD=").append(descriptor.uuid)
                        }
                    }
                    allUUIDs.append("}")
                    Log.i("__BleClient-3", "onServicesDiscovered:$allUUIDs")
                    BleCallbackImpl.getGattCallback().notify("发现服务$allUUIDs")
                }
            }
        }

        override fun onCharacteristicRead(gatt: BluetoothGatt, characteristic: BluetoothGattCharacteristic, status: Int) {
            val uuid = characteristic.uuid
            val valueStr = String(characteristic.value)
            Log.i("__BleClient-4", String.format("onCharacteristicRead:%s,%s,%s,%s,%s", gatt.device.name, gatt.device.address, uuid, valueStr, status))
            BleCallbackImpl.getGattCallback().notify("读取Characteristic[$uuid]:\n$valueStr")
        }

        override fun onCharacteristicWrite(gatt: BluetoothGatt, characteristic: BluetoothGattCharacteristic, status: Int) {
            val uuid = characteristic.uuid
            val valueStr = String(characteristic.value)
            Log.i("__BleClient-5", String.format("onCharacteristicWrite:%s,%s,%s,%s,%s", gatt.device.name, gatt.device.address, uuid, valueStr, status))
            BleCallbackImpl.getGattCallback().notify("写入Characteristic[$uuid]:\n$valueStr")
        }

        override fun onCharacteristicChanged(gatt: BluetoothGatt, characteristic: BluetoothGattCharacteristic) {
            val uuid = characteristic.uuid
            val valueStr = String(characteristic.value)
            Log.i("__BleClient-6", String.format("onCharacteristicChanged:%s,%s,%s,%s", gatt.device.name, gatt.device.address, uuid, valueStr))
            BleCallbackImpl.getGattCallback().notify("通知Characteristic[$uuid]:\n$valueStr")
        }

        override fun onDescriptorRead(gatt: BluetoothGatt, descriptor: BluetoothGattDescriptor, status: Int) {
            val uuid = descriptor.uuid
            val valueStr = Arrays.toString(descriptor.value)
            Log.i("__BleClient-7", String.format("onDescriptorRead:%s,%s,%s,%s,%s", gatt.device.name, gatt.device.address, uuid, valueStr, status))
            BleCallbackImpl.getGattCallback().notify("读取Descriptor[$uuid]:\n$valueStr")
        }

        override fun onDescriptorWrite(gatt: BluetoothGatt, descriptor: BluetoothGattDescriptor, status: Int) {
            val uuid = descriptor.uuid
            val valueStr = Arrays.toString(descriptor.value)
            Log.i("__BleClient-8", String.format("onDescriptorWrite:%s,%s,%s,%s,%s", gatt.device.name, gatt.device.address, uuid, valueStr, status))
            BleCallbackImpl.getGattCallback().notify("写入Descriptor[$uuid]:\n$valueStr")
        }
    }



    // BLE服务端Callback
    val mBluetoothGattServerCallback: BluetoothGattServerCallback = object : BluetoothGattServerCallback() {

        override fun onConnectionStateChange(device: BluetoothDevice, status: Int, newState: Int) {
            Log.i("__BleServer-1", String.format("onConnectionStateChange:%s,%s,%s,%s", device.name, device.address, status, newState))
            BleCallbackImpl.getGattCallback().notify(String.format(if (status == 0) (if (newState == 2) "与[%s]连接成功" else "与[%s]连接断开") else "与[%s]连接出错,错误码:$status", device))
        }

        override fun onServiceAdded(status: Int, service: BluetoothGattService) {
            Log.i("__BleServer-2", String.format("onServiceAdded:%s,%s", status, service.uuid))
            BleCallbackImpl.getGattCallback().notify(String.format(if (status == 0) "添加服务[%s]成功" else "添加服务[%s]失败,错误码:$status", service.uuid))
        }

        override fun onCharacteristicReadRequest(device: BluetoothDevice, requestId: Int, offset: Int, characteristic: BluetoothGattCharacteristic) {
            Log.i("__BleServer-3", String.format("onCharacteristicReadRequest:%s,%s,%s,%s,%s", device.name, device.address, requestId, offset, characteristic.uuid))
            val response = "CHAR_" + (Math.random() * 100).toInt() //模拟数据
            BleUtils.instance.getBluetoothGattServer().sendResponse(device, requestId, BluetoothGatt.GATT_SUCCESS, offset, response.toByteArray()) // 响应客户端
            BleCallbackImpl.getGattCallback().notify("客户端读取Characteristic[${characteristic.uuid}]:$response".trimIndent())
        }

        override fun onCharacteristicWriteRequest(device: BluetoothDevice, requestId: Int, characteristic: BluetoothGattCharacteristic, preparedWrite: Boolean, responseNeeded: Boolean, offset: Int, requestBytes: ByteArray) {
            // 获取客户端发过来的数据
            val requestStr = String(requestBytes)
            Log.i("__BleServer-4", String.format("onCharacteristicWriteRequest:%s,%s,%s,%s,%s,%s,%s,%s", device.name, device.address, requestId, characteristic.uuid, preparedWrite, responseNeeded, offset, requestStr))
            BleUtils.instance.getBluetoothGattServer().sendResponse(device, requestId, BluetoothGatt.GATT_SUCCESS, offset, requestBytes) // 响应客户端
            BleCallbackImpl.getGattCallback().notify("客户端写入Characteristic[${characteristic.uuid}]:$requestStr".trimIndent())
        }

        override fun onDescriptorReadRequest(device: BluetoothDevice, requestId: Int, offset: Int, descriptor: BluetoothGattDescriptor) {
            Log.i("__BleServer-5", String.format("onDescriptorReadRequest:%s,%s,%s,%s,%s", device.name, device.address, requestId, offset, descriptor.uuid))
            val response = "DESC_" + (Math.random() * 100).toInt() //模拟数据
            BleUtils.instance.getBluetoothGattServer().sendResponse(device, requestId, BluetoothGatt.GATT_SUCCESS, offset, response.toByteArray()) // 响应客户端
            BleCallbackImpl.getGattCallback().notify("客户端读取Descriptor[${descriptor.uuid}]:$response".trimIndent())
        }

        override fun onDescriptorWriteRequest(device: BluetoothDevice, requestId: Int, descriptor: BluetoothGattDescriptor, preparedWrite: Boolean, responseNeeded: Boolean, offset: Int, value: ByteArray) {
            // 获取客户端发过来的数据
            val valueStr = Arrays.toString(value)
            Log.i("__BleServer-6", String.format("onDescriptorWriteRequest:%s,%s,%s,%s,%s,%s,%s,%s", device.name, device.address, requestId, descriptor.uuid, preparedWrite, responseNeeded, offset, valueStr))
            BleUtils.instance.getBluetoothGattServer().sendResponse(device, requestId, BluetoothGatt.GATT_SUCCESS, offset, value) // 响应客户端
            BleCallbackImpl.getGattCallback().notify("客户端写入Descriptor[${descriptor.uuid}]:$valueStr".trimIndent())

            // 简单模拟通知客户端Characteristic变化
            if (Arrays.toString(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE) == valueStr) { //是否开启通知
                val characteristic = descriptor.characteristic
                Thread {
                    for (i in 0..4) {
                        SystemClock.sleep(3000)
                        val response = "CHAR_" + (Math.random() * 100).toInt() //模拟数据
                        characteristic.setValue(response)
                        BleUtils.instance.getBluetoothGattServer().notifyCharacteristicChanged(device, characteristic, false)
                        BleCallbackImpl.getGattCallback().notify(" 通知客户端改变Characteristic[${characteristic.uuid}]:$response".trimIndent())
                    }
                }.start()
            }
        }

        override fun onExecuteWrite(device: BluetoothDevice, requestId: Int, execute: Boolean) {
            Log.i("__BleServer-7", String.format("onExecuteWrite:%s,%s,%s,%s", device.name, device.address, requestId, execute))
        }

        override fun onNotificationSent(device: BluetoothDevice, status: Int) {
            Log.i("__BleServer-8", String.format("onNotificationSent:%s,%s,%s", device.name, device.address, status))
        }

        override fun onMtuChanged(device: BluetoothDevice, mtu: Int) {
            Log.i("__BleServer-9", String.format("onMtuChanged:%s,%s,%s", device.name, device.address, mtu))
        }
    }


    // BLE广播Callback
    val mAdvertiseCallback: AdvertiseCallback = object : AdvertiseCallback() {
        override fun onStartSuccess(settingsInEffect: AdvertiseSettings) {
            BleCallbackImpl.getGattCallback().notify("BLE广播开启成功")
        }

        override fun onStartFailure(errorCode: Int) {
            BleCallbackImpl.getGattCallback().notify("BLE广播开启失败,错误码:$errorCode")
        }
    }

}