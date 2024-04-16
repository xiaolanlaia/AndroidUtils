package com.wjf.androidutils.net.api

import okhttp3.MultipartBody

object ApiHelper {

    suspend fun getUsers() = RetrofitBuilder.apiService.getUsers()

    /**
     * 上传图片文件及文字参数
     */
    suspend fun uploadCheckPicture(
        imageBodyPart: MultipartBody.Part,
        bucketBodyPart: MultipartBody.Part,
        typeBodyPart: MultipartBody.Part,
        sortBodyPart: MultipartBody.Part,
        nameBodyPart: MultipartBody.Part,
        checkIdBodyPart: MultipartBody.Part
    ) = RetrofitBuilder.apiService.uploadCheckPicture(
        imageBodyPart,
        bucketBodyPart,
        typeBodyPart,
        sortBodyPart,
        nameBodyPart,
        checkIdBodyPart
    )

}