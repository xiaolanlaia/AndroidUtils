package com.wjf.androidutils

import android.Manifest
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.wjf.androidutils.utils.BitmapUtils
import com.wjf.androidutils.utils.FILE_TYPE_2
import com.wjf.androidutils.utils.FileUtils

//首先定义请求变量
val PERMISSIONS_STORAGE = arrayOf(
    Manifest.permission.READ_EXTERNAL_STORAGE,
    Manifest.permission.WRITE_EXTERNAL_STORAGE,
    Manifest.permission.MANAGE_EXTERNAL_STORAGE
)
//请求状态码，请求码的作用是与回调函数进行匹配的，这样就可以对不同权限操作进行不同的提示
const val REQUEST_EXTERNAL_STORAGE = 1
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE)
        val btnSave = findViewById<Button>(R.id.btn_save)
        val btnGet = findViewById<Button>(R.id.btn_get)
        val ivShow = findViewById<ImageView>(R.id.iv_show)
        btnSave.setOnClickListener {

            FileUtils.saveImg(
                bitmap = BitmapUtils.getBitmapFromResource(R.mipmap.ic_launcher),
                fileType = FILE_TYPE_2
            )

        }

        btnGet.setOnClickListener {
            val bitmap = FileUtils.getImg(fieType = FILE_TYPE_2)
            ivShow.setImageBitmap(bitmap)

        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}