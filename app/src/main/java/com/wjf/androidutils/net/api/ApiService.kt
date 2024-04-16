package com.wjf.androidutils.net.api

import com.wjf.androidutils.entity.BaseEntity
import com.wjf.androidutils.entity.User
import okhttp3.MultipartBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {

    @GET("users")
    suspend fun getUsers(): BaseEntity<User>

    @Multipart
    @POST("check/uploadCheckPicture")
    suspend fun uploadCheckPicture(
        @Part imageBodyPart: MultipartBody.Part,
        @Part bucketBodyPart: MultipartBody.Part,
        @Part typeBodyPart: MultipartBody.Part,
        @Part sortBodyPart: MultipartBody.Part,
        @Part nameBodyPart: MultipartBody.Part,
        @Part checkIdBodyPart: MultipartBody.Part
    ): BaseEntity<String>

}