package com.wjf.modulebluetooth

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.location.LocationManager
import android.provider.Settings
import androidx.core.app.ActivityCompat
import com.wjf.androidutils.R
import com.wjf.modulebluetooth.callback.BlueCallback
import com.wjf.modulebluetooth.receiver.BlueReceiver
import com.wjf.modulebluetooth.utils.BlueConstant.blueContext
import com.wjf.moduleutils.ExceptionUtils
import com.wjf.moduleutils.ToastUtils

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/11 16:38
 *
 * 经典蓝牙
 *
 */

class BlueUtils {

    /**
     * 获取BluetoothAdapter对象
     */
    private var mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter()

    /**
     * 判断是否支持蓝牙
     */
    fun isSupportBlue() : Boolean{
        return mBluetoothAdapter != null
    }

    /**
     * 判断蓝牙是否开启
     */
    fun isBlueEnable() : Boolean{
        return isSupportBlue() && mBluetoothAdapter!!.isEnabled
    }

    /**
     * 开启蓝牙 - 异步
     * 返回结果是同步的，但开启蓝牙是异步的
     */
    @SuppressLint("MissingPermission")
    fun openBlue(){
        if (isSupportBlue()){
            if (!permissionCheck()) return
            mBluetoothAdapter?.enable()

        }
    }

    @SuppressLint("MissingPermission")
    fun openBlue(activity : Activity, requestCode : Int){
        if (!permissionCheck()) return
        val intent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
        activity.startActivityForResult(intent,requestCode)
    }

    /**
     * 检查是否有权限
     */
    fun permissionCheck() : Boolean{
        if (ActivityCompat.checkSelfPermission(blueContext, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
            ToastUtils.instance.show(blueContext.getString(R.string.permission_tip))
            return false
        }
        return true
    }

    /**
     * 申请权限
     */
    fun requestPermission(activity : Activity, requestCode : Int){
        val permissionList = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.BLUETOOTH_SCAN,
        )
        ActivityCompat.requestPermissions(activity, permissionList, requestCode)

    }

    /**
     * 打开定位
     */
    fun openGPS(activity : Activity, requestCode : Int){
        val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        activity.startActivityForResult(intent, requestCode)
    }

    /**
     * 检查GPS是否开启
     */
    fun checkGPS() : Boolean{
        val locationManager = blueContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    /**
     * 扫描蓝牙
     */
    @SuppressLint("MissingPermission")
    fun scanBlue() : Boolean{
        if (!isBlueEnable()) return false

        if (mBluetoothAdapter.isDiscovering){
            mBluetoothAdapter.cancelDiscovery()
        }
        //此方法是个异步操作，一般搜索12秒
        return mBluetoothAdapter.startDiscovery()
    }

    /**
     * 取消扫描蓝牙
     * true 为取消成功
     */
    @SuppressLint("MissingPermission")
    fun cancelScan() : Boolean{
        if (isSupportBlue()){
            return mBluetoothAdapter.cancelDiscovery()
        }
        return true
    }

    /**
     * 注册广播
     */
    fun registerReceiver(activity: Activity, blueCallback : BlueCallback){
        val blueReceiver = BlueReceiver(activity,blueCallback)
        val filter1 = IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_STARTED)
        val filter2 = IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)
        val filter3 = IntentFilter(BluetoothDevice.ACTION_FOUND)
        val filter4 = IntentFilter(BluetoothDevice.ACTION_PAIRING_REQUEST)
        val filter5 = IntentFilter(BluetoothDevice.ACTION_BOND_STATE_CHANGED)
        activity.registerReceiver(blueReceiver,filter1)
        activity.registerReceiver(blueReceiver,filter2)
        activity.registerReceiver(blueReceiver,filter3)
        activity.registerReceiver(blueReceiver,filter4)
        activity.registerReceiver(blueReceiver,filter5)
    }

    /**
     * 配对蓝牙
     */
    @SuppressLint("MissingPermission")
    fun pin(device : BluetoothDevice){

        if (!isBlueEnable()){
            ToastUtils.instance.show(blueContext.getString(R.string.blue_closed_tip))
            return
        }

        //配对之前关闭扫描
        if (mBluetoothAdapter.isDiscovering){
            mBluetoothAdapter.cancelDiscovery()
        }

        //判断设备是否配对
        if (device.bondState == BluetoothDevice.BOND_NONE){

            try {

                val createBondMethod = device.javaClass.getMethod("createBond")
                createBondMethod.invoke(device) as Boolean

            } catch (e: Exception) {
                ExceptionUtils.instance.getCashHandler().uncaughtException(Thread.currentThread(),e.fillInStackTrace())
            }
        }

    }

    /**
     * 取消配对
     */
    @SuppressLint("MissingPermission")
    fun cancelPin(device : BluetoothDevice){

        if (!isBlueEnable()){
            return
        }

        if (device.bondState != BluetoothDevice.BOND_NONE){

            try {
                val removeBond = device.javaClass.getMethod("removeBond")
                removeBond.invoke(device)
            } catch (e: Exception) {
                ExceptionUtils.instance.getCashHandler().uncaughtException(Thread.currentThread(),e.fillInStackTrace())
            }
        }

    }

    //todo wjf 获取已配对的设备
}





















