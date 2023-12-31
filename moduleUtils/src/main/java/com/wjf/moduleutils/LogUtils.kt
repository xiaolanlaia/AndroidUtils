package com.wjf.moduleutils

import android.util.Log

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/11/22 10:34
 *
 */

object LogUtils {

    private const val isDebug = true
    private val fileName = "${TimeUtils.instance.getTime(pattern = "yyyy-MM-dd")}.txt"
    private const val folderName = "Log"

    fun vw(tag: String, msg: String) {
        if (isDebug) {
            Log.v(tag, msg)
            val content = "\n${TimeUtils.instance.getTime()} \n v $tag $msg"
            FileUtils.instance.writeStr2Txt(content = content, folderName = folderName,fileName = fileName)
        }
    }

    fun iw(tag: String, msg: String) {
        if (isDebug) {
            Log.i(tag, msg)
            val content = "\n${TimeUtils.instance.getTime()} \n i $tag $msg"
            FileUtils.instance.writeStr2Txt(content = content, folderName = folderName,fileName = fileName)
        }
    }

    fun dw(tag: String, msg: String) {
        if (isDebug) {
            Log.d(tag, msg)
            val content = "\n${TimeUtils.instance.getTime()} \n d $tag $msg"
            FileUtils.instance.writeStr2Txt(content = content, folderName = folderName,fileName = fileName)
        }
    }

    fun ww(tag: String, msg: String) {
        if (isDebug) {
            Log.w(tag, msg)
            val content = "\n${TimeUtils.instance.getTime()} \n w $tag $msg"
            FileUtils.instance.writeStr2Txt(content = content, folderName = folderName,fileName = fileName)
        }
    }

    fun ew(tag: String, msg: String) {
        if (isDebug) {
            Log.e(tag, msg)
            val content = "\n${TimeUtils.instance.getTime()} \n e $tag $msg"
            FileUtils.instance.writeStr2Txt(content = content, folderName = folderName,fileName = fileName)
        }
    }

    fun v(tag: String, msg: String) {
        if (isDebug) {
            Log.v(tag, msg)
        }
    }

    fun i(tag: String, msg: String) {
        if (isDebug) {
            Log.i(tag, msg)
        }
    }

    fun d(tag: String, msg: String) {
        if (isDebug) {
            Log.d(tag, msg)
        }
    }

    fun w(tag: String, msg: String) {
        if (isDebug) {
            Log.w(tag, msg)
        }
    }

    fun e(tag: String, msg: String) {
        if (isDebug) {
            Log.e(tag, msg)
        }
    }

    fun getStackTraceString(throwable : Throwable) : String{
        return Log.getStackTraceString(throwable)
    }
}