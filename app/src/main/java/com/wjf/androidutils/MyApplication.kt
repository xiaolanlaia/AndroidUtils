package com.wjf.androidutils

import android.app.Application

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/11/14 11:01
 *
 * 记得在 AndroidManifest 中注册
 */

class MyApplication : Application() {

    companion object{
        lateinit var instance: MyApplication
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}