package com.wjf.androidutils.utils

import android.content.ContentValues
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.text.TextUtils
import android.util.Log
import com.wjf.androidutils.MyApplication
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.FileWriter
import java.io.InputStreamReader
import java.io.OutputStream

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/11/14 9:52
 *
 */
/**
 * -1: 其他情况
 * 0: 内部存储
 * 1: 外部存储私有目录
 * 2: 外部存储共享空间
 */
const val FILE_TYPE_1_N = -1
const val FILE_TYPE_0 = 0
const val FILE_TYPE_1 = 1
const val FILE_TYPE_2 = 2
object FileUtils {


    /**
     * txt -> 写入
     * Android 11及以上不能在根目录下创建文件夹
     */
    fun writeTxtFile(content: String, folderName: String? = "Test", fileName:String? = "fileName.txt", fileType: Int? = FILE_TYPE_1){

        val folderPath = when(fileType){
            FILE_TYPE_0 -> {
                "${MyApplication.instance.filesDir}/${folderName}"
            }
            FILE_TYPE_1 -> {
                "${MyApplication.instance.getExternalFilesDir(null)}/${folderName}"

            }
            FILE_TYPE_2 -> {
                "${Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)}/${folderName}"
            }

            else ->{
                folderName
            }
        }
        Log.d("__saveImgToRoot",folderPath!!)
        if (fileIsExist(folderPath)){
            ThreadPoolUtils.cachedThreadPool.execute {
                val file = File(folderPath,fileName!!)

                if (!file.exists()) { file.createNewFile() }

                //追加模式
                val filerWriter = FileWriter(file, true)
                val bufWriter = BufferedWriter(filerWriter)
                bufWriter.write(content)
                bufWriter.newLine()
                bufWriter.close()
                filerWriter.close()
            }
        }

    }


    /**
     * txt -> 读取
     */
    fun readTxtFile(folderName: String? = "Test", fileName:String? = "fileName.txt", fileType: Int? = FILE_TYPE_1, result:(String) -> Unit){
        val stringBuilder = StringBuilder()
        val folderPath = when(fileType){
            FILE_TYPE_0 -> {
                "${MyApplication.instance.filesDir}/${folderName}"
            }
            FILE_TYPE_1 -> {
                "${MyApplication.instance.getExternalFilesDir(null)}/${folderName}"

            }
            FILE_TYPE_2 -> {
                "${Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)}/${folderName}"
            }

            else ->{
                folderName
            }
        }

        if (fileIsExist(folderPath)){
            ThreadPoolUtils.cachedThreadPool.execute {
                val file = File(folderPath,fileName!!)

                if (file.exists()) {
                    val inputStream = FileInputStream(file)
                    val reader = BufferedReader(InputStreamReader(inputStream))

                    var line: String? = reader.readLine()
                    while (line!= null) {
                        stringBuilder.append(line)
                        line = reader.readLine()
                    }

                    reader.close()
                    inputStream.close()

                    result(stringBuilder.toString())
                }
            }
        }
    }

    /**
     * 保存图片为png格式
     * 路径：
     *   FILE_TYPE_0 -> 内部存储：/data/user/0/[包名]/files/[文件名]
     *   FILE_TYPE_1 -> 外部存储私有目录：/storage/emulated/0/Android/data/[包名]/files/[文件名]
     *   FILE_TYPE_2 -> 外部存储共享空间：/storage/emulated/0/Pictures/[文件名]
     *
     */
    fun saveImg(bitmap: Bitmap?, folderName: String? = "", fileName: String? = "MyImg", fileType: Int? = FILE_TYPE_1){
        if (bitmap == null){ return }

        val folderPath = when(fileType){
            FILE_TYPE_0 -> {
                "${MyApplication.instance.filesDir}/${folderName}"
            }
            FILE_TYPE_1 -> {
                "${MyApplication.instance.getExternalFilesDir(null)}/${folderName}"
            }
            FILE_TYPE_2 -> {
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q){
                    saveImgQ(bitmap)
                    return
                }else{
                    "${Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)}/${folderName}"
                }
            }

            else ->{
                "${MyApplication.instance.getExternalFilesDir(null)}/${folderName}"

            }
        }

        if (fileIsExist(folderPath)){
            ThreadPoolUtils.cachedThreadPool.execute {
                val file = File(folderPath,"${fileName}.png")
                if (file.exists()){
                    file.delete()
                    Log.d("__img-delete",file.path)
                }
                Log.d("__img-saveImg",file.path)
                val outputStream = FileOutputStream(file)
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                outputStream.flush()
                outputStream.close()
            }
        }
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
    fun getImg(fileName: String? = "MyImg",fieType: Int? = FILE_TYPE_1, result:(Bitmap?)->Unit){
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
        ThreadPoolUtils.cachedThreadPool.execute {
            var bitmap: Bitmap? = null
            try {
                val inputStream = FileInputStream(file)
                bitmap = BitmapFactory.decodeStream(inputStream)
                inputStream.close()
            } catch (e: Exception) {
                Log.d("__img-getImg-e","${e.message}")
            }
            result(bitmap)
        }

    }


    /**
     * 检测文件夹，不存在则创建文件夹
     */
    private fun fileIsExist(fileName: String?): Boolean {
        if (TextUtils.isEmpty(fileName)) return false
        //传入指定的路径，然后判断路径是否存在
        val file = File(fileName)
        return if (file.exists()) {
            true
        } else {
            //创建文件夹
            file.mkdirs()
        }
    }

    /**
     * Android10(Q)公共目录写入方法
     * 通过 MediaStore 在图片目录新建一个图片文件
     * uri.path -> content://media/external/images/media/
     * 真实目录 -> /storage/emulated/0/Pictures/
     */
    fun saveImgQ(bitmap: Bitmap,folderName: String? = "folderName", fileName: String? = "MyImg") {

        val file = File("${Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).absolutePath}/${folderName}","${fileName}.png")
        if (file.exists()){
            file.delete()
        }
        val contentValues = ContentValues()
        // 指定文件保存的文件夹名称
        // 如果想获取上述文件夹的真实地址可以通过这样的方式 Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath() 获取，他返回的值类似 /storage/emulated/0/Pictures
        if (!TextUtils.isEmpty(folderName)){
            contentValues.put(
                MediaStore.Images.ImageColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/${folderName}")
        }
        // 指定文件名
        contentValues.put(MediaStore.Images.ImageColumns.DISPLAY_NAME, fileName)
        // 指定文件的 mime（比如 image/jpeg, application/vnd.android.package-archive 之类的）
        contentValues.put(MediaStore.Images.ImageColumns.MIME_TYPE, "image/png")
        contentValues.put(MediaStore.Images.ImageColumns.WIDTH, bitmap.width)
        contentValues.put(MediaStore.Images.ImageColumns.HEIGHT, bitmap.height)
        val contentResolver = MyApplication.instance.contentResolver
        // 通过 ContentResolver 在指定的公共目录下按照指定的 ContentValues 创建文件，会返回文件的 content uri（类似这样的地址 content://media/external/images/media/102）
        // 可以通过 MediaStore 保存文件的公共目录有：Images、Audio、Video、Downloads
        val uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues) ?: return

        ThreadPoolUtils.cachedThreadPool.execute {
            // 写入图片数据
            var outputStream: OutputStream? = null
            try {
                outputStream = contentResolver.openOutputStream(uri)
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            } catch (ex: Exception) {
                Log.d("__saveImgQ-e", "写入数据失败：$ex")
            } finally {
                try {
                    if (outputStream != null) {
                        outputStream.flush()
                        outputStream.close()
                    }
                } catch (ex: Exception) {}
            }
        }

    }

    /**
     * uri转path
     */
    fun getRealPathFromUri(contentUri: Uri): String? {
        var cursor: Cursor? = null
        try {
            val proj = arrayOf(MediaStore.Images.Media.DATA)
            cursor = MyApplication.instance.contentResolver.query(contentUri, proj, null, null, null)
            if (cursor != null && cursor.columnCount > 0) {
                cursor.moveToFirst()
                val columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                return cursor.getString(columnIndex)
            }
        } catch (e: java.lang.Exception) {

        } finally {
            cursor?.close()
        }
        return ""
    }


}