package com.wjf.modulesocket.utils

import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import com.wjf.modulesocket.utils.SocketConstant.socketContext

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/15 17:35
 *
 */

object SocketUtils {


    /**
     * 获取Ip地址
     */
    fun getIp() = intToIp((socketContext.getSystemService(AppCompatActivity.WIFI_SERVICE) as WifiManager).connectionInfo.ipAddress)

    /**
     * Ip地址转换
     */
    fun intToIp(ip: Int) = "${(ip and 0xFF)}.${(ip shr 8 and 0xFF)}.${(ip shr 16 and 0xFF)}.${(ip shr 24 and 0xFF)}"
}