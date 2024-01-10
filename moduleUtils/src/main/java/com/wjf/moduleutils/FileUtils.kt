package com.wjf.moduleutils

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.provider.Settings
import android.text.TextUtils
import android.util.Log
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.ByteArrayOutputStream
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

/**
 * delete ： 删除文件和空文件夹，文件夹不为空时，删除失败
 * deleteRecursively：递归删除，即使返回失败，也可能已经删除部分文件
 */
class FileUtils {


    companion object{
        val instance by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { FileUtils() }
    }
    /**
     * txt -> 写入字符串
     * Android 11及以上不能在根目录下创建文件夹
     */
    fun writeStr2Txt(content: String, folderName: String = "Test", fileName:String = "fileName.txt", fileType: Int = FILE_TYPE_1){

        val folderPath = getFolderPath(folderName = folderName,fileType = fileType, EnvironmentType = Environment.DIRECTORY_DOCUMENTS)
        if (folderExistOrCreate(folderPath)){
            ThreadPoolUtils.instance.cachedThreadPool().execute {
                val file = File(folderPath,fileName)
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
     * txt -> 读取字符串
     */
    fun readStrFromTxt(folderName: String = "Test", fileName:String = "fileName.txt", fileType: Int = FILE_TYPE_1, result:(String) -> Unit){
        val stringBuilder = StringBuilder()
        val folderPath = getFolderPath(folderName = folderName,fileType = fileType, EnvironmentType = Environment.DIRECTORY_DOCUMENTS)

        if (folderExistOrCreate(folderPath)){
            ThreadPoolUtils.instance.cachedThreadPool().execute {
                val file = File(folderPath,fileName)

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
     * txt -> 写入ByteArray
     */
    fun writeByteArray2Txt(byteArray: ByteArray, folderName: String = "Test", fileName:String = "fileName.txt", fileType: Int = FILE_TYPE_1) {
        val folderPath = getFolderPath(folderName = folderName, fileType = fileType, EnvironmentType = Environment.DIRECTORY_DOCUMENTS)
        LogUtils.d("__FileUtils-writeByteArray2Txt",folderPath)
        if (folderExistOrCreate(folderPath)){
            val file = File(folderPath,fileName)
            val outputStream = ByteArrayOutputStream()
            outputStream.write(byteArray)
            val fileOutputStream = FileOutputStream(file,true)
            fileOutputStream.write(outputStream.toByteArray())
            fileOutputStream.close()

        }
    }

    /**
     * txt -> 读取ByteArray
     */
    fun readByteArrayFromTxt(folderName: String = "Test", fileName:String = "fileName.txt", fileType: Int = FILE_TYPE_1): ByteArray {
        val folderPath = getFolderPath(folderName = folderName, fileType = fileType, EnvironmentType = Environment.DIRECTORY_DOCUMENTS)
        if (!folderExist(folderPath)) return byteArrayOf()

        val file = File(folderPath,fileName)
        val inputStream = FileInputStream(file)
        val outputStream = ByteArrayOutputStream()
        //每次读取的最大缓存
        val buffer = ByteArray(64 * 1024)
        var length: Int
        while (inputStream.read(buffer).also { length = it } > 0) {
            outputStream.write(buffer, 0, length)
        }
        val byteArray = outputStream.toByteArray()
        inputStream.close()
        outputStream.close()
        LogUtils.d("__FileUtils-readByteArrayFromTxt",byteArray.contentToString())
        return byteArray
    }


    /**
     * 保存图片为png格式
     * 路径：
     *   FILE_TYPE_0 -> 内部存储：/data/user/0/[包名]/files/[文件名]
     *   FILE_TYPE_1 -> 外部存储私有目录：/storage/emulated/0/Android/data/[包名]/files/[文件名]
     *   FILE_TYPE_2 -> 外部存储共享空间：/storage/emulated/0/Pictures/[文件名]
     *
     */
    fun saveImg(bitmap: Bitmap?, folderName: String = "", fileName: String = "MyImg", fileType: Int = FILE_TYPE_1){
        if (bitmap == null){ return }

        if (fileType == FILE_TYPE_2 && Build.VERSION.SDK_INT > Build.VERSION_CODES.Q){
            saveImgQ(bitmap = bitmap, folderName = folderName, fileName = fileName)
            return
        }

        val folderPath = getFolderPath(folderName = folderName,fileType = fileType, EnvironmentType = Environment.DIRECTORY_PICTURES)


        if (folderExistOrCreate(folderPath)){
            ThreadPoolUtils.instance.cachedThreadPool().execute {
                val file = File(folderPath,"${fileName}.png")
                if (file.exists()){
                    file.delete()
                }
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
    fun getImg(fileName: String = "MyImg", fileType: Int = FILE_TYPE_1, result:(Bitmap?)->Unit){
        val folderPath = getFolderPath(folderName = "", fileType = fileType)
        val file = File(folderPath,"${fileName}.png")
        ThreadPoolUtils.instance.cachedThreadPool().execute {
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
    fun folderExistOrCreate(folderPath: String): Boolean {
        if (TextUtils.isEmpty(folderPath)) return false
        //传入指定的路径，然后判断路径是否存在
        val folder = File(folderPath)
        return if (folder.exists()) {
            true
        } else {
            //创建文件夹
            folder.mkdirs()
        }
    }

    /**
     * 检测文件夹是否存在
     */
    fun folderExist(folderPath: String): Boolean {
        if (TextUtils.isEmpty(folderPath)) return false
        //传入指定的路径，然后判断路径是否存在
        val folder = File(folderPath)
        return folder.exists()
    }

    /**
     * 检测文件夹是否为空文件夹
     * true：文件夹为空 | 不存在
     * false：文件夹存在且不为空
     */
    fun folderNull(folderPath: String): Boolean {
        if (TextUtils.isEmpty(folderPath)) return true
        //传入指定的路径，然后判断路径是否存在
        val folder = File(folderPath)
        if (!folder.exists()) return true
        try {
            //空文件夹会报异常
            folder.listFiles().isEmpty()
        } catch (e: Exception) {
            return true
        }
        return false
    }

    /**
     * 删除文件夹
     */
    fun deleteFolder(context: Context, folderPath: String, result: (Boolean) -> Unit){

        //传入指定的路径，然后判断路径是否存在
        val folder = File(folderPath)
        if (folder.exists()){
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R || Environment.isExternalStorageManager()) {
                result(folder.deleteRecursively())
            } else {
                ToastUtils.instance.show("请授予所有文件的管理权限")
                val intent = Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION)
                context.startActivity(intent)
            }
        }else{
            result(true)
        }
    }


    /**
     * 获取文件夹中文件的名字
     */
    fun getFilesName(folderPath: String): ArrayList<String>{
        if (folderNull(folderPath)) return ArrayList<String>()
        val nameList = ArrayList<String>()
        val file = File(folderPath)
        val files = file.listFiles()
        files.forEach {
            nameList.add(it.name)
        }
        return nameList
    }

    /**
     * Android10(Q)公共目录写入方法
     * 通过 MediaStore 在图片目录新建一个图片文件
     * uri.path -> content://media/external/images/media/
     * 真实目录 -> /storage/emulated/0/Pictures/
     */
    fun saveImgQ(bitmap: Bitmap,folderName: String = "folderName", fileName: String? = "MyImg") {

        val file = File(getFolderPath(folderName = folderName,fileType = FILE_TYPE_2),"${fileName}.png")
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
        val contentResolver = UtilsConstant.utilsContext.contentResolver
        // 通过 ContentResolver 在指定的公共目录下按照指定的 ContentValues 创建文件，会返回文件的 content uri（类似这样的地址 content://media/external/images/media/102）
        // 可以通过 MediaStore 保存文件的公共目录有：Images、Audio、Video、Downloads
        val uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues) ?: return

        ThreadPoolUtils.instance.cachedThreadPool().execute {
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
    fun uri2RealPath(contentUri: Uri): String? {

        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).absolutePath
    }

    /**
     * path转uri
     */
    fun path2Uri(filePath: String): Uri? {
        return Uri.fromFile(File(filePath))
    }

    /**
     * 获取文件路径
     */
    fun getFolderPath(folderName: String = "Test", fileType: Int = FILE_TYPE_1, EnvironmentType: String = Environment.DIRECTORY_PICTURES): String{

        return when(fileType){
            FILE_TYPE_0 -> {
                "${UtilsConstant.utilsContext.filesDir.absolutePath}/${folderName}"
            }
            FILE_TYPE_1 -> {
                "${UtilsConstant.utilsContext.getExternalFilesDir(null)?.absolutePath}/${folderName}"

            }
            FILE_TYPE_2 -> {
                "${Environment.getExternalStoragePublicDirectory(EnvironmentType).absolutePath}/${folderName}"
            }

            else ->{
                folderName
            }
        }
    }


}