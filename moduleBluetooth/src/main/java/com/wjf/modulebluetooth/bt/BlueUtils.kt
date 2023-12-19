package com.wjf.modulebluetooth.bt

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothServerSocket
import android.bluetooth.BluetoothSocket
import android.os.Build
import android.text.TextUtils
import com.wjf.modulebluetooth.BLUE_CONNECTED
import com.wjf.modulebluetooth.BlueConstant
import com.wjf.modulebluetooth.BLUE_FLAG_FILE
import com.wjf.modulebluetooth.BLUE_FLAG_MSG
import com.wjf.modulebluetooth.BLUE_MSG
import com.wjf.moduleutils.ExceptionUtils
import com.wjf.moduleutils.PermissionUtil
import com.wjf.moduleutils.ThreadPoolUtils
import com.wjf.modulebluetooth.bt.callback.BlueCallbackImpl
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.File
import java.io.FileInputStream

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/12 17:53
 *
 */

@SuppressLint("MissingPermission")
class BlueUtils {
    companion object{
        val instance by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { BlueUtils() }
    }

    private val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
    private var bluetoothSocket : BluetoothSocket? = null
    private var bluetoothServerSocket : BluetoothServerSocket? = null

    //是否正在发送消息
    private var isSending = false
    private var isRead = false

    private var dataOutputStream: DataOutputStream? = null

    /**
     * 请求权限
     */
    fun requestPermission(activity: Activity, requestCode : Int){
        val permissionList = ArrayList<String>()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            permissionList.add(Manifest.permission.BLUETOOTH)
            permissionList.add(Manifest.permission.BLUETOOTH_ADMIN)
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION)
            permissionList.add(Manifest.permission.ACCESS_COARSE_LOCATION)

        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
            permissionList.add(Manifest.permission.BLUETOOTH_CONNECT)
            permissionList.add(Manifest.permission.BLUETOOTH_SCAN)
            permissionList.add(Manifest.permission.BLUETOOTH_ADVERTISE)
        }
        PermissionUtil.instance.requestPermissions(activity,permissionList,requestCode)
    }

    //设备是否支持蓝牙
    fun isSupport() : Boolean{
        return bluetoothAdapter != null
    }

    //是否开启
    fun isEnabled() : Boolean{
       return bluetoothAdapter.isEnabled
    }

    //开启蓝牙
    fun enable(){

        if (isEnabled()) return
        try {
            bluetoothAdapter.enable()
        } catch (e: Exception) {
            ExceptionUtils.instance.getCashHandler().uncaughtException(Thread.currentThread(),e.fillInStackTrace())
        }
    }

    //扫描蓝牙
    fun scan() : Boolean{
        if (!isEnabled()) return false

        if (bluetoothAdapter.isDiscovering){
            bluetoothAdapter.cancelDiscovery()
        }
        //此方法是个异步操作，一般搜索12秒
        return bluetoothAdapter.startDiscovery()
    }

    //当前设备与指定设备是否连接
    fun isConnected(dev: BluetoothDevice?): Boolean {
        val connected = (getBluetoothSocket() != null && getBluetoothSocket()!!.isConnected)
        return if (dev == null) {
            connected
        } else {
            connected && (getBluetoothSocket()!!.remoteDevice == dev)
        }
    }

    //创建BluetoothSocket，建立连接
    fun bluetoothSocket(device: BluetoothDevice) {
        if (isConnected(device)) {
            BlueCallbackImpl().notifyState("已经连接了")
            return
        }

        // 开启子线程
        ThreadPoolUtils.instance.getCachedThreadPool().execute {
            //device.createRfcommSocketToServiceRecord(SPP_UUID) //加密传输，Android系统强制配对，弹窗显示配对码

            //明文传输(不安全)，无需配对
            bluetoothSocket = device.createInsecureRfcommSocketToServiceRecord(BlueConstant.SPP_UUID)
            if (!bluetoothSocket!!.isConnected) {
                bluetoothSocket!!.connect()
            }
            dataOutputStream = DataOutputStream(bluetoothSocket?.outputStream)
            loopRead() //循环读取
        }
    }

    //创建 bluetoothServerSocket，建立连接
    fun bluetoothServerSocket() : BluetoothServerSocket{
        //            mSSocket = adapter.listenUsingRfcommWithServiceRecord(TAG, SPP_UUID); //加密传输，Android强制执行配对，弹窗显示配对码
        //明文传输(不安全)，无需配对
        bluetoothServerSocket = bluetoothAdapter.listenUsingInsecureRfcommWithServiceRecord("TAG",
            BlueConstant.SPP_UUID
        )

        Thread(Runnable {
            bluetoothSocket = bluetoothServerSocket?.accept()
            if (!bluetoothSocket!!.isConnected) {
                bluetoothSocket!!.connect()
            }
            dataOutputStream = DataOutputStream(bluetoothSocket?.outputStream)

            loopRead()
        }).start()
        return bluetoothServerSocket!!
    }

    fun getBluetoothSocket() : BluetoothSocket?{
        return bluetoothSocket
    }

    fun getConnectedDevice() : BluetoothDevice?{
        return bluetoothSocket?.remoteDevice
    }

    fun getBondDevice() : Set<BluetoothDevice>{
        return bluetoothAdapter.bondedDevices
    }



    /**
     * 发送短消息
     */
    fun sendMsg(msg: String) {
        if (!isConnected(null)) {
            BlueCallbackImpl().notifyState("没有连接")
            return
        }


        if (TextUtils.isEmpty(msg)) {
            BlueCallbackImpl().notifyState("消息不能空")
            return
        }

        if (checkSend()) {
            return
        }
        isSending = true
        try {
            //消息标记
            dataOutputStream!!.writeInt(BLUE_FLAG_MSG)
            dataOutputStream!!.writeUTF(msg)
            dataOutputStream!!.flush()
            BlueCallbackImpl().socketNotify(BLUE_MSG, "发送短消息：$msg")
        } catch (e: Throwable) {
            close()
        }
        isSending = false
    }

    /**
     * 发送文件
     */
    fun sendFile(filePath: String) {

        if (!isConnected(null)) {
            BlueCallbackImpl().notifyState("没有连接")
            return
        }

        if (TextUtils.isEmpty(filePath) || !File(filePath).isFile) {
            BlueCallbackImpl().notifyState("文件无效")
            return
        }

        if (checkSend()) {
            return
        }
        isSending = true
        Thread(Runnable {
            try {
                val fileInputStream = FileInputStream(filePath)
                val file = File(filePath)
                //文件标记
                dataOutputStream!!.writeInt(BLUE_FLAG_FILE)
                //文件名
                dataOutputStream!!.writeUTF(file.name)
                //文件长度
                dataOutputStream!!.writeLong(file.length())
                var r: Int
                val b = ByteArray(4 * 1024)
                BlueCallbackImpl().socketNotify(BLUE_MSG, "正在发送文件($filePath),请稍后...")
                while ((fileInputStream.read(b).also { r = it }) != -1) {
                    dataOutputStream!!.write(b, 0, r)
                }
                dataOutputStream!!.flush()
                BlueCallbackImpl().socketNotify(BLUE_MSG, "文件发送完成.")
            } catch (e: Throwable) {
                close()
            }
            isSending = false
        })
    }

    private fun checkSend(): Boolean {
        if (isSending) {
            BlueCallbackImpl().notifyState("正在发送其它数据,请稍后再发...")
            return true
        }
        return false
    }



    /**
     * 关闭Socket连接
     */
    fun close() {
        try {
            isRead = false
            isSending = false
            dataOutputStream?.close()
            dataOutputStream = null
            bluetoothSocket?.close()
            bluetoothSocket = null
            bluetoothServerSocket?.close()
            bluetoothServerSocket = null

        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }



    /**
     * 循环读取对方数据(若没有数据，则阻塞等待)
     */
    fun loopRead() {
        val mSocket = getBluetoothSocket()
        try {
            BlueCallbackImpl().socketNotify(BLUE_CONNECTED, mSocket?.remoteDevice)
            val dataInputStream = DataInputStream(mSocket?.inputStream)
            isRead = true
            //死循环读取
            while (isRead) {
                when (dataInputStream.readInt()) {
                    BLUE_FLAG_MSG -> {
                        val msg = dataInputStream.readUTF()
                        BlueCallbackImpl().socketNotify(BLUE_MSG, "接收短消息：$msg")
                    }

                    BLUE_FLAG_FILE -> {
//                        Util.mkdirs(Constant.FILE_PATH)
//                        //文件名
//                        val fileName = dataInputStream.readUTF()
//                        //文件长度
//                        val fileLen = dataInputStream.readLong()
//                        // 读取文件内容
//                        var len: Long = 0
//                        var r: Int
//                        val b = ByteArray(4 * 1024)
//                        val out = FileOutputStream(Constant.FILE_PATH + fileName)
//
//                        CallbackImpl.getBlueCallback().notifyState(BLUE_MSG, "正在接收文件($fileName),请稍后...")
//
//                        while ((dataInputStream.read(b).also { r = it }) != -1) {
//                            out.write(b, 0, r)
//                            len += r.toLong()
//                            if (len >= fileLen) {
//                                break
//                            }
//                        }
//                        CallbackImpl.getBlueCallback().notifyState(BLUE_MSG, "文件接收完成(存放在:" + Constant.FILE_PATH + ")")
                    }
                }
            }
        } catch (e: Throwable) {
            close()
        }
    }


}




















