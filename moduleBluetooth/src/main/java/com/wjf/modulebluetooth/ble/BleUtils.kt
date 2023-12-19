package com.wjf.modulebluetooth.ble

import android.annotation.SuppressLint
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattCharacteristic
import android.bluetooth.BluetoothGattDescriptor
import android.bluetooth.BluetoothGattServer
import android.bluetooth.BluetoothGattService
import android.bluetooth.BluetoothManager
import android.bluetooth.le.AdvertiseData
import android.bluetooth.le.AdvertiseSettings
import android.bluetooth.le.BluetoothLeAdvertiser
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.content.Context
import android.os.Handler
import android.os.ParcelUuid
import com.wjf.modulebluetooth.BlueConstant
import com.wjf.modulebluetooth.BlueConstant.blueContext
import com.wjf.modulebluetooth.ble.callback.BleCallbackImpl
import com.wjf.modulebluetooth.ble.callback.GattCallbackUtils
import com.wjf.moduleutils.ToastUtils
import java.util.UUID

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/14 16:02
 *
 */

@SuppressLint("MissingPermission")
class BleUtils private constructor(){

    companion object {
        val instance by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { BleUtils() }
    }

    private var isConnected = false
    private var isScanning = false

    private var mBluetoothGatt: BluetoothGatt? = null


    // BLE广播
    private var mBluetoothLeAdvertiser : BluetoothLeAdvertiser? = null
    // BLE服务端
    private var mBluetoothGattServer : BluetoothGattServer? = null

    fun getBluetoothGattServer() : BluetoothGattServer{
        return mBluetoothGattServer!!
    }
    fun getScanState() : Boolean{
        return isScanning
    }

    // 连接蓝牙设备
    fun connect(context : Context, device : BluetoothDevice){
        closeConnect()

        mBluetoothGatt = device.connectGatt(context, false, GattCallbackUtils.instance.mBluetoothGattCallback)

    }

    // BLE中心设备连接外围设备的数量有限(大概2~7个)，在建立新连接之前必须释放旧连接资源，否则容易出现连接错误133
    fun closeConnect() {
        isConnected = false
        if (mBluetoothGatt != null) {
            mBluetoothGatt?.disconnect()
            mBluetoothGatt?.close()
            mBluetoothGatt = null
        }
    }

    fun read(){
        val service = getGattService(BlueConstant.UUID_SERVICE)
        if (service != null) {
            //通过UUID获取可读的Characteristic
            val characteristic = service.getCharacteristic(BlueConstant.UUID_CHAR_READ_NOTIFY)
            mBluetoothGatt!!.readCharacteristic(characteristic)
        }
    }

    fun write(content : String){
        val service = getGattService(BlueConstant.UUID_SERVICE)
        if (service != null) {
            //通过UUID获取可写的Characteristic
            val characteristic = service.getCharacteristic(BlueConstant.UUID_CHAR_WRITE)
            //单次最多20个字节
            characteristic.value = content.toByteArray()
            mBluetoothGatt!!.writeCharacteristic(characteristic)
        }
    }

    fun setNotify(){

        val service = getGattService(BlueConstant.UUID_SERVICE)
        if (service != null) {
            // 设置Characteristic通知   //通过UUID获取可通知的Characteristic
            val characteristic = service.getCharacteristic(BlueConstant.UUID_CHAR_READ_NOTIFY)
            mBluetoothGatt!!.setCharacteristicNotification(characteristic, true)

            // 向Characteristic的Descriptor属性写入通知开关，使蓝牙设备主动向手机发送数据
            val descriptor = characteristic.getDescriptor(BlueConstant.UUID_DESC_NOTITY)
            // descriptor.setValue(BluetoothGattDescriptor.ENABLE_INDICATION_VALUE);
            // 和通知类似,但服务端不主动发数据,只指示客户端读取数据
            descriptor.value = BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE
            mBluetoothGatt!!.writeDescriptor(descriptor)
        }
    }



    // 获取Gatt服务
    private fun getGattService(uuid: UUID): BluetoothGattService? {
        if (!isConnected) {
            ToastUtils.instance.show("没有连接", 0)
            return null
        }
        val service = mBluetoothGatt!!.getService(uuid)
        if (service == null) {
            ToastUtils.instance.show("没有找到服务UUID=$uuid", 0)
        }
        return service
    }

    fun setConnectState(){
        isConnected = true
    }


    private val mScanCallback: ScanCallback = object : ScanCallback() {
        // 扫描Callback
        override fun onScanResult(callbackType: Int, result: ScanResult) {

            BleCallbackImpl().scanning(BleDev(result.device, result))
        }
    }



    // 扫描BLE蓝牙(不会扫描经典蓝牙)
    fun scanBle() {
        try {
            isScanning = true
            //        BluetoothAdapter bluetoothAdapter = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE).getDefaultAdapter();
            val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
            val bluetoothLeScanner = bluetoothAdapter.bluetoothLeScanner
            // Android5.0新增的扫描API，扫描返回的结果更友好，比如BLE广播数据以前是byte[] scanRecord，而新API帮我们解析成ScanRecord类
            bluetoothLeScanner.startScan(mScanCallback)
            Handler().postDelayed({
                bluetoothLeScanner.stopScan(mScanCallback) //停止扫描
                isScanning = false
            }, 3000)
        } catch (e: Exception) {
        }
    }
    fun bleServiceSetting(){
        val bluetoothManager = blueContext?.getSystemService(Activity.BLUETOOTH_SERVICE) as BluetoothManager
        //        BluetoothAdapter bluetoothAdapter = bluetoothManager.getAdapter();
        val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()

        // ============启动BLE蓝牙广播(广告) =================================================================================
        //广播设置(必须)
        val settings = AdvertiseSettings.Builder()
            .setAdvertiseMode(AdvertiseSettings.ADVERTISE_MODE_LOW_LATENCY) //广播模式: 低功耗,平衡,低延迟
            .setTxPowerLevel(AdvertiseSettings.ADVERTISE_TX_POWER_HIGH) //发射功率级别: 极低,低,中,高
            .setConnectable(true) //能否连接,广播分为可连接广播和不可连接广播
            .build()
        //广播数据(必须，广播启动就会发送)
        val advertiseData = AdvertiseData.Builder()
            .setIncludeDeviceName(true) //包含蓝牙名称
            .setIncludeTxPowerLevel(true) //包含发射功率级别
            .addManufacturerData(1, byteArrayOf(23, 33)) //设备厂商数据，自定义
            .build()
        //扫描响应数据(可选，当客户端扫描时才发送)
        val scanResponse = AdvertiseData.Builder()
            .addManufacturerData(2, byteArrayOf(66, 66)) //设备厂商数据，自定义
            .addServiceUuid(ParcelUuid(BlueConstant.UUID_SERVICE)) //服务UUID
            //                .addServiceData(new ParcelUuid(UUID_SERVICE), new byte[]{2}) //服务数据，自定义
            .build()
        mBluetoothLeAdvertiser = bluetoothAdapter.bluetoothLeAdvertiser
        mBluetoothLeAdvertiser?.startAdvertising(settings, advertiseData, scanResponse, GattCallbackUtils.instance.mAdvertiseCallback)

        // 注意：必须要开启可连接的BLE广播，其它设备才能发现并连接BLE服务端!
        // =============启动BLE蓝牙服务端=====================================================================================
        val service = BluetoothGattService(BlueConstant.UUID_SERVICE, BluetoothGattService.SERVICE_TYPE_PRIMARY)
        //添加可读+通知characteristic
        val characteristicRead = BluetoothGattCharacteristic(
            BlueConstant.UUID_CHAR_READ_NOTIFY,
            BluetoothGattCharacteristic.PROPERTY_READ or BluetoothGattCharacteristic.PROPERTY_NOTIFY,
            BluetoothGattCharacteristic.PERMISSION_READ
        )
        characteristicRead.addDescriptor(
            BluetoothGattDescriptor(BlueConstant.UUID_DESC_NOTITY, BluetoothGattCharacteristic.PERMISSION_WRITE)
        )
        service.addCharacteristic(characteristicRead)
        //添加可写characteristic
        val characteristicWrite = BluetoothGattCharacteristic(
            BlueConstant.UUID_CHAR_WRITE,
            BluetoothGattCharacteristic.PROPERTY_WRITE, BluetoothGattCharacteristic.PERMISSION_WRITE
        )
        service.addCharacteristic(characteristicWrite)
        if (bluetoothManager != null) {
            mBluetoothGattServer = bluetoothManager.openGattServer(blueContext, GattCallbackUtils.instance.mBluetoothGattServerCallback)
        }
        mBluetoothGattServer!!.addService(service)
    }

    fun bleServiceClose(){

        if (mBluetoothLeAdvertiser != null) {
            mBluetoothLeAdvertiser?.stopAdvertising(GattCallbackUtils.instance.mAdvertiseCallback)
            mBluetoothLeAdvertiser = null
        }
        if (mBluetoothGattServer != null) {
            mBluetoothGattServer?.close()
            mBluetoothGattServer = null
        }
    }

}