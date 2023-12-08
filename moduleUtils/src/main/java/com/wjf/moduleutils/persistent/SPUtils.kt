package com.wjf.moduleutils.persistent

import android.content.Context
import com.wjf.moduleutils.ModuleUtilsConstant

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/11/27 18:05
 *
 */

/**
 * commit
 *  1、同步提交到内存和数据库，相当于提交到本地磁盘
 *  2、有返回值
 * apply
 *  1、提交到内存，再异步提交到磁盘
 *  2、没有返回值
 *
 *  不需要考虑提交结果的情况下，优先考虑apply
 */
object SPUtils {

    private var mPreferences = ModuleUtilsConstant.moduleUtilsContext.getSharedPreferences("SPUtils", Context.MODE_PRIVATE)
    private var mEditor = mPreferences.edit()


    // 存入数据
    fun apply(key: String, value: Any) {
        when(value){

            is String ->{
                mEditor.putString(key, value)
            }
            is Int ->{
                mEditor.putInt(key, value)
            }
            is Boolean ->{
                mEditor.putBoolean(key, value)
            }
            is Float ->{
                mEditor.putFloat(key, value)
            }
            is Long ->{
                mEditor.putLong(key, value)
            }
        }
        mEditor.apply()
    }

    // 存入数据
    fun commit(key: String, value: Any) {
        when(value){

            is String ->{
                mEditor.putString(key, value)
            }
            is Int ->{
                mEditor.putInt(key, value)
            }
            is Boolean ->{
                mEditor.putBoolean(key, value)
            }
            is Float ->{
                mEditor.putFloat(key, value)
            }
            is Long ->{
                mEditor.putLong(key, value)
            }
        }
        mEditor.commit()
    }

    fun getString(key: String): String?{
        var result : String? = ""
        try {
            result = mPreferences.getString(key, "")
        } catch (e: Exception) {
            print(e.message)
        }
        return result
    }

    fun getBoolean(key: String): Boolean{
        var result = false
        try {
            result = mPreferences.getBoolean(key, false)
        } catch (e: Exception) {
            print(e.message)
        }
        return result
    }

    fun getInt(key: String): Int{
        var result = -1
        try {
            result = mPreferences.getInt(key, -1)
        } catch (e: Exception) {
            print(e.message)
        }
        return result
    }

    fun getFloat(key: String): Float{
        var result = -1f
        try {
            result = mPreferences.getFloat(key, -1f)
        } catch (e: Exception) {
            print(e.message)
        }
        return result
    }

    fun getLong(key: String): Long{
        var result = -1L
        try {
            result = mPreferences.getLong(key, -1L)
        } catch (e: Exception) {
            print(e.message)
        }
        return result
    }

    // 移除数据
    fun removeSP(key: String) {
        mEditor.remove(key)
        mEditor.commit()
    }

}
