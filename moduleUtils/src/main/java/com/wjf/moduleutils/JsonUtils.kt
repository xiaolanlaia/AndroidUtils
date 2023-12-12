package com.wjf.moduleutils

import com.google.gson.Gson
import com.google.gson.JsonParser
import com.wjf.moduleutils.entity.User


class JsonUtils {

    companion object{
        val instance by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { JsonUtils() }
    }
    /**
     * 将字符串解析成实体类
     */
    fun parseString(content: String){
        val user = Gson().fromJson(content, User::class.java)
        LogUtils.d("__JsonUtils",user.toString())
    }

    /**
     * 将assets中的json文件解析成实体类
     */
    fun parseAssets(fileName: String){
        val json = ModuleUtilsConstant.moduleUtilsContext.assets.open("${fileName}.json").bufferedReader().use { it.readText() }
        val user = Gson().fromJson(json, User::class.java)
    }

    /**
     * 检测字符串中是否存在子字符串为json数据
     */
    fun containsJson(str: String): Boolean {
        val jsonStart = str.indexOf("{")
        if (jsonStart == -1) {
            return false
        }
        val jsonEnd = str.indexOf("}", jsonStart)
        if (jsonEnd == -1) {
            return false
        }
        return try {
            JsonParser().parse(str.substring(jsonStart, jsonEnd + 1))
            LogUtils.d("__jsonStart",str.substring(jsonStart, jsonEnd + 1))
            true
        } catch (e: Exception) {
            false
        }
    }
}