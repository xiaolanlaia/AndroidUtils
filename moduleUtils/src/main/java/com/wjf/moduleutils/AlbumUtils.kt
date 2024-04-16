package com.wjf.moduleutils

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.os.Environment
import android.provider.MediaStore
import java.io.File

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2024/4/16 17:34
 *
 */

class AlbumUtils {

    companion object {
        val instance by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { AlbumUtils() }
    }


    /**
     * 从相册中获取图片并转为bitmap
     */
    fun getBitmapFromAlbum(context: Context): Bitmap? {

        val cameraDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).absolutePath + "/Camera/left.png"
        //刷新相册
        MediaScannerConnection.scanFile(context, arrayOf(cameraDir), null, null)
        val cr: ContentResolver = context.contentResolver
        val uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val cursor = cr.query(uri, null, MediaStore.Images.Media.DATA + "=?", arrayOf(cameraDir), null)
        if (cursor != null && cursor.moveToFirst()) {
            val columnIndex = cursor.getColumnIndex(MediaStore.Images.Media._ID)
            val id = cursor.getLong(columnIndex)
            val bitmap = MediaStore.Images.Thumbnails.getThumbnail(cr, id, MediaStore.Images.Thumbnails.MINI_KIND, null)
            cursor.close()
            return bitmap
        }
        return null

    }

    /**
     * 从相册中获取图片并转为file
     */
    @SuppressLint("Range")
    fun getFileFromAlbum(context: Context): File? {
        val cameraDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).absolutePath + "/Camera/left.png"
        //刷新相册
        MediaScannerConnection.scanFile(context, arrayOf(cameraDir), null, null)
        val cr: ContentResolver = context.contentResolver
        val uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val cursor = cr.query(uri, null, MediaStore.Images.Media.DATA + "=?", arrayOf(cameraDir),null)
        if (cursor != null && cursor.moveToFirst()) {
            val columnIndex = cursor.getColumnIndex(MediaStore.Images.Media._ID)
            val file = File(cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA)))
            cursor.close()
            return file
        }
        return null
    }
}