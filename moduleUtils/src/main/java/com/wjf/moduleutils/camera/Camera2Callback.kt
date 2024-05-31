package com.wjf.moduleutils.camera

import android.graphics.Bitmap

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2024/5/23 16:51
 *
 */

interface Camera2Callback {
    fun previewData(byteArray: ByteArray)
    fun takePic(cameraId: String, bitmap: Bitmap?)
}