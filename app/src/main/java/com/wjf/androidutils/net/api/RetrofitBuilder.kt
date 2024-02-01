package com.wjf.androidutils.net.api

import com.wjf.androidutils.net.interceptor.HeadInterceptor
import com.wjf.androidutils.net.interceptor.LogInterceptor
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitBuilder {

//    private const val BASE_URL = "http://192.168.50.6:8888/api-business/"
    private const val BASE_URL = "https://5e510330f2c0d300147c034c.mockapi.io/"

    /**
     * 日志拦截器
     * 全局请求头
     */
    private val mOkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(LogInterceptor())
        .addInterceptor(HeadInterceptor())
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .client(mOkHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: ApiService = getRetrofit().create(ApiService::class.java)

    fun getRequestBody(str: String?): RequestBody {
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), str)
    }
}