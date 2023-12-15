package com.wjf.modulebluetooth.ble

import android.bluetooth.BluetoothDevice
import android.bluetooth.le.ScanResult
import java.util.Objects

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/14 18:26
 *
 */

class BleDev constructor(var dev: BluetoothDevice, var scanResult: ScanResult) {
    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val bleDev = o as BleDev
        return dev == bleDev.dev
    }

    override fun hashCode(): Int {
        return Objects.hash(dev)
    }
}