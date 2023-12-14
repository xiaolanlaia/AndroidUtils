package com.wjf.modulebluetooth

import android.content.Context
import android.os.Environment
import java.util.UUID

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/12 13:42
 *
 */



const val BLUE_DISCONNECTED = 0
const val BLUE_CONNECTED = 1
const val BLUE_MSG = 2

//消息标记
const val BLUE_FLAG_MSG = 0
//文件标记
const val BLUE_FLAG_FILE = 1



object BlueConstant {

    var blueContext : Context? = null

    val SPP_UUID: UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
    val FILE_PATH = Environment.getExternalStorageDirectory().absolutePath + "/bluetooth/"

}