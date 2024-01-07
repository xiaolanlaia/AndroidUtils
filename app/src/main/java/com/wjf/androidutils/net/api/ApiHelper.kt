package com.wjf.androidutils.net.api

object ApiHelper {

    suspend fun getUsers() = RetrofitBuilder.apiService.getUsers()
}