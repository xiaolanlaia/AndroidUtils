package com.wjf.androidutils.net.interceptor

import com.wjf.moduleutils.LogUtils
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import okhttp3.ResponseBody
import okio.Buffer
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2024/1/7 8:37
 *
 */

class LogInterceptor : Interceptor {

    companion object {
        const val JSON_INDENT = 3
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val startTime = System.currentTimeMillis()
        val response = chain.proceed(chain.request())
        val endTime = System.currentTimeMillis()
        val duration = endTime - startTime
        val mediaType = response.body()?.contentType()
        val content = response.body()?.string()


        var requestParamsStr = ""
        when(request.method()){
            "GET" -> {
                val urlBuilder = request.url().newBuilder()
                val httpUrl = urlBuilder.build()
                //打印所有get参数
                val paramKeys = httpUrl.queryParameterNames()
                val jsonObject = JSONObject()
                for (key in paramKeys){
                    jsonObject.put(key,httpUrl.queryParameter(key))
                }
                requestParamsStr = jsonObject.toString()

            }
            "POST" -> {
                requestParamsStr = printParams(request.body())
            }
        }
        LogUtils.d("__https-请求地址", "method: ${request.method()} url：${request.url()}")
        LogUtils.d("__https-请求参数", requestParamsStr)
        LogUtils.d("__https-请求响应", "$content")
        LogUtils.d("__https-请求耗时", "------${duration}毫秒------")

        return response.newBuilder().body(ResponseBody.create(mediaType, content)).build()
    }

    private fun printParams(body: RequestBody?): String {
        val buffer = Buffer()
        body?.writeTo(buffer)
        val contentType = body?.contentType()
        val charset: Charset = contentType?.charset(StandardCharsets.UTF_8) ?: StandardCharsets.UTF_8
        return getJsonString(buffer.readString(charset))
    }
    fun getJsonString(msg: String): String {
        val message: String = try {
            when {
                msg.startsWith("{") -> {
                    val jsonObject = JSONObject(msg)
                    jsonObject.toString(JSON_INDENT)
                }
                msg.startsWith("[") -> {
                    val jsonArray = JSONArray(msg)
                    jsonArray.toString(JSON_INDENT)
                }
                else -> {
                    msg
                }
            }
        } catch (e: JSONException) {
            msg
        }
        return message
    }

}