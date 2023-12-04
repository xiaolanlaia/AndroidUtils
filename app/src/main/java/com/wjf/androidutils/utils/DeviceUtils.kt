package com.wjf.androidutils.utils


import android.app.ActivityManager
import android.content.Context
import android.os.Build
import android.text.format.Formatter
import com.wjf.androidutils.MyApplication
import java.util.Locale

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/11/29 10:09
 *
 */

object DeviceUtils {

    /**
     * 获取厂商名
     */
    fun getManufacturer(): String? {
        return Build.MANUFACTURER
    }

    /**
     * 获取产品名
     */
    fun getProduct(): String? {
        return Build.PRODUCT
    }

    /**
     * 获取手机品牌
     */
    fun getBrand(): String? {
        return Build.BRAND
    }

    /**
     * 获取手机型号
     */
    fun getModel(): String? {
        return Build.MODEL
    }

    /**
     * 获取手机主板名
     */
    fun getBoard(): String? {
        return Build.BOARD
    }

    /**
     * 设备名
     */
    fun getDevice(): String? {
        return Build.DEVICE
    }

    /**
     * fingerprint 信息
     */
    fun getFingerprint(): String? {
        return Build.FINGERPRINT
    }

    /**
     * 硬件名
     */
    fun getHardware(): String? {
        return Build.HARDWARE
    }

    /**
     * 主机
     */
    fun getHost(): String? {
        return Build.HOST
    }

    /**
     * 显示ID
     */
    fun getDisplay(): String? {
        return Build.DISPLAY
    }

    /**
     * ID
     */
    fun getId(): String? {
        return Build.ID
    }

    /**
     * 获取手机用户名
     */
    fun getUser(): String? {
        return Build.USER
    }

    /**
     * 获取手机 硬件序列号
     */
    fun getSerial(): String? {
        return Build.SERIAL
    }

    /**
     * 获取手机Android 系统SDK
     */
    fun getSDK(): Int {
        return Build.VERSION.SDK_INT
    }

    /**
     * 获取手机Android 版本
     */
    fun getAndroidVersion(): String? {
        return Build.VERSION.RELEASE
    }

    /**
     * 获取当前手机系统语言
     */
    fun getDefaultLanguage(): String? {
        return Locale.getDefault().language
    }

    /**
     * 获取当前系统上的语言列表(Locale列表)
     */
    fun getSupportLanguage(): String {
        LogUtils.d("wangjie", "Local:" + Locale.GERMAN)
        LogUtils.d("wangjie", "Local:" + Locale.ENGLISH)
        LogUtils.d("wangjie", "Local:" + Locale.US)
        LogUtils.d("wangjie", "Local:" + Locale.CHINESE)
        LogUtils.d("wangjie", "Local:" + Locale.TAIWAN)
        LogUtils.d("wangjie", "Local:" + Locale.FRANCE)
        LogUtils.d("wangjie", "Local:" + Locale.FRENCH)
        LogUtils.d("wangjie", "Local:" + Locale.GERMANY)
        LogUtils.d("wangjie", "Local:" + Locale.ITALIAN)
        LogUtils.d("wangjie", "Local:" + Locale.JAPAN)
        LogUtils.d("wangjie", "Local:" + Locale.JAPANESE)
        return Locale.getAvailableLocales().toString()
    }


    /**
     * 获取 手机 RAM 信息
     * 运行时内存，此大小直接决定手机运行的流畅度，相当于电脑内存
     */
    fun getRAMInfo(): String {
        var totalSize: Long = 0
        var availableSize: Long = 0
        val activityManager = MyApplication.instance.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val memoryInfo = ActivityManager.MemoryInfo()
        activityManager.getMemoryInfo(memoryInfo)
        totalSize = memoryInfo.totalMem
        availableSize = memoryInfo.availMem
        return "可用/总共：" + Formatter.formatFileSize(MyApplication.instance, availableSize) + "/" + Formatter.formatFileSize(MyApplication.instance, totalSize)
    }
}