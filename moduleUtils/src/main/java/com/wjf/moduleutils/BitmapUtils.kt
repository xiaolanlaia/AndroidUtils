package com.wjf.moduleutils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream
/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/11/14 9:50
 *
 */

object BitmapUtils {

    /**
     * 从resource中获取图片资源转为bitmap
     * @id 资源id
     *
     * 获取失败原因
     * 1、不能获取drawable中的xml格式资源
     * 2、mipmap中是否存在xml的同名资源
     */
    fun resource2Bitmap(id: Int): Bitmap {
        return BitmapFactory.decodeResource(ModuleUtilsConstant.moduleUtilsContext?.resources, id)
    }

    /**
     * bitmap转byteArray
     */
    fun bitmap2ByteArray(bitmap: Bitmap): ByteArray{
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        return stream.toByteArray()

    }

    /**
     * byteArray转bitmap
     */
    fun byteArrayBitmap(byteArray: ByteArray): Bitmap{
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }


}