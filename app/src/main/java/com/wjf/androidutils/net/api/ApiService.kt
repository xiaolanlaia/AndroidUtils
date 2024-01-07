package com.wjf.androidutils.net.api

import com.wjf.androidutils.entity.BaseEntity
import com.wjf.androidutils.entity.User
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUsers(): BaseEntity<User>

}