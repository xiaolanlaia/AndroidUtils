package com.wjf.androidutils.net.interceptor

import android.os.Build
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2024/1/31 13:51
 *
 */

class HeadInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val requestBuilder = original.newBuilder()
            .addHeader("model", Build.MANUFACTURER + " " + Build.MODEL)
            .addHeader("localsizeModel", "Android")
            .addHeader("systemName", "Android " + Build.VERSION.RELEASE)
            .addHeader("systemVersion", "${Build.VERSION.SDK_INT}")
            .addHeader("Authorization", "")
            .addHeader("mobileName", Build.MANUFACTURER)
            .addHeader("appVersion", "1.0") // Application.getAppVersionName()
        return chain.proceed(requestBuilder.build())
    }
}