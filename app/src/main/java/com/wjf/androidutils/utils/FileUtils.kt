package com.wjf.androidutils.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import com.wjf.androidutils.MyApplication
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/11/14 9:52
 *
 */
/**
 * 0: 内部存储
 * 1: 外部存储私有目录
 * 2: 外部存储共享空间
 */
const val FILE_TYPE_0 = 0
const val FILE_TYPE_1 = 1
const val FILE_TYPE_2 = 2
object FileUtils {

    /**
     * 保存图片为png格式
     * 路径：
     *   FILE_TYPE_0 -> 内部存储：/data/user/0/[包名]/files/[文件名]
     *   FILE_TYPE_1 -> 外部存储私有目录：/storage/emulated/0/Android/data/[包名]/files/[文件名]
     *   FILE_TYPE_2 -> 外部存储共享空间：/storage/emulated/0/Pictures/[文件名]
     *
     */
    fun saveImg(bitmap: Bitmap?,fileName: String? = "MyImg", fieType: Int? = FILE_TYPE_1){
        if (bitmap == null){ return }

        val file = when(fieType){
            FILE_TYPE_0 -> {
                File(MyApplication.instance.filesDir, "${fileName}.png")
            }
            FILE_TYPE_1 -> {
                File(MyApplication.instance.getExternalFilesDir(null), "${fileName}.png")
            }
            FILE_TYPE_2 -> {
                File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "${fileName}.png")
            }

            else ->{
                File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "${fileName}.png")

            }
        }
        Log.d("__img-saveImg",file.path)
        val outputStream = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        outputStream.flush()
        outputStream.close()
    }

    /**
     * 内部存储 ->获取png格式图片
     * 路径：
     *   FILE_TYPE_0 -> 内部存储：/data/user/0/[包名]/files/[文件名]
     *   FILE_TYPE_1 -> 外部存储私有目录：/storage/emulated/0/Android/data/[包名]/files/[文件名]
     *   FILE_TYPE_2 -> 外部存储共享空间：/storage/emulated/0/Pictures/[文件名]
     *
     * 为什么读取到的bitmap为空: 文件不存在
     */
    fun getImg(fileName: String? = "MyImg",fieType: Int? = FILE_TYPE_1): Bitmap{
        val file = when(fieType){
            FILE_TYPE_0 -> {
                File(MyApplication.instance.filesDir, "${fileName}.png")
            }
            FILE_TYPE_1 -> {
                File(MyApplication.instance.getExternalFilesDir(null), "${fileName}.png")
            }
            FILE_TYPE_2 -> {
                File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "${fileName}.png")
            }

            else ->{
                File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "${fileName}.png")

            }
        }
        Log.d("__img-getImg",file.path)
        val inputStream = FileInputStream(file)
        val bitmap = BitmapFactory.decodeStream(inputStream)
        inputStream.close()
        return bitmap

    }


    /**
     * 检测文件是否存在
     */
    private fun fileIsExist(fileName: String): Boolean {
        //传入指定的路径，然后判断路径是否存在
        val file = File(fileName)
        return if (file.exists()) {
            true
        } else {
            //创建文件夹
            file.mkdirs()
        }
    }

    fun getImg2(){
        val uri = Uri.parse("content://com.android.externalstorage.documents/document/primary:Android/data/com.wjf.androidutils/files/MyImg2.png")
        val cursor = MyApplication.instance.contentResolver.query(uri, null, null, null, null)
        if (cursor!= null && cursor.moveToFirst()) {
            val columnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATA)
            val filePath = cursor.getString(columnIndex)
            cursor.close()
            val bitmap = BitmapFactory.decodeFile(filePath)
            if (bitmap == null){
                Log.d("__getImg2","1")
            }else{
                Log.d("__getImg2","2")

            }
            // Do something with the bitmap
        } else {
            // Handle the error
        }
    }


}