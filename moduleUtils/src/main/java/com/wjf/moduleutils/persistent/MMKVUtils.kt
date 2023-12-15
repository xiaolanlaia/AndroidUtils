package com.wjf.moduleutils.persistent

import com.tencent.mmkv.MMKV
import com.wjf.moduleutils.UtilsConstant

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/11/30 7:55
 *
 */

/**
 * val MMKVInstance = MMKV.defaultMMKV()
 * 根据设置 id 来自定义 MMKV 对象。比如根据业务来区分的存取实例
 * val mmkvWithID = MMKV.mmkvWithID("ID")
 * 开启多进程访问。默认是单线程
 * val mmkv = MMKV.mmkvWithID("ID",MMKV.MULTI_PROCESS_MODE)
 */
class MMKVUtils{

    companion object{
        val instance by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { MMKVUtils() }
    }

    lateinit var MMKVInstance : MMKV

    // 初始化
    fun init(){

        MMKV.initialize(UtilsConstant.moduleUtilsContext)
        MMKVInstance = MMKV.defaultMMKV()
    }
    /**
     * 写入数据
     */
    fun putValue(key: String, value: Any) {

        when (value) {

            is Int -> {
                MMKVInstance.encode(key, value)
            }

            is String -> {
                MMKVInstance.encode(key, value)
            }

            is Boolean -> {
                MMKVInstance.encode(key, value)
            }

            is Long -> {
                MMKVInstance.encode(key, value)
            }

            is Float -> {
                MMKVInstance.encode(key, value)
            }

            is Double -> {
                MMKVInstance.encode(key, value)
            }
        }
    }

    /**
     * 取出数据
     */

    fun getInt(key: String): Int {
        return MMKVInstance.decodeInt(key)
    }

    fun getBoolean(key: String): Boolean {
        return MMKVInstance.decodeBool(key)
    }

    fun getLong(key: String): Long {
        return MMKVInstance.decodeLong(key)
    }

    fun getFloat(key: String): Float {
        return MMKVInstance.decodeFloat(key)
    }

    fun getDouble(key: String): Double {
        return MMKVInstance.decodeDouble(key)
    }

    fun getString(key: String): String? {
        return MMKVInstance.decodeString(key)
    }

    /**
     * 删除数据 -> 单个
     */
    fun deleteKey(key: String) {
        MMKVInstance.removeValueForKey(key)
    }

    /**
     * 删除数据 -> 多个
     */
    fun deleteKeys(key: Array<String>) {
        MMKVInstance.removeValuesForKeys(key)
    }

    /**
     * 清空数据
     */
    fun clear() {
        MMKVInstance.clearAll()
    }

}