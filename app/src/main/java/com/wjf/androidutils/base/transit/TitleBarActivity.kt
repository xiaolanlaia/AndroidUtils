package com.wjf.androidutils.base.transit

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.wjf.androidutils.R
import com.wjf.androidutils.ui.AnimFragment
import com.wjf.androidutils.ui.ArrayFragment
import com.wjf.androidutils.ui.DesignFragment
import com.wjf.androidutils.ui.ExceptionFragment
import com.wjf.androidutils.ui.FileFragment
import com.wjf.androidutils.ui.ImgLoaderFragment
import com.wjf.androidutils.ui.PersistentFragment
import com.wjf.androidutils.ui.ToastFragment
import com.wjf.androidutils.ui.WebViewFragment
import com.wjf.androidutils.ui.blue.bt.BlueClientFragment
import com.wjf.androidutils.ui.blue.BlueFragment
import com.wjf.androidutils.ui.blue.ble.BleClientFragment
import com.wjf.androidutils.ui.blue.ble.BleServiceFragment
import com.wjf.androidutils.ui.blue.bt.BlueServiceFragment
import com.wjf.androidutils.ui.home.HomeFragment
import com.wjf.androidutils.ui.reflect.ReflectFragment
import com.wjf.androidutils.utils.IMG_POSITION
import com.wjf.androidutils.utils.JUMP_TO
import com.wjf.androidutils.utils.JUMP_TO_AnimFragment
import com.wjf.androidutils.utils.JUMP_TO_ArrayFragment
import com.wjf.androidutils.utils.JUMP_TO_BleClientFragment
import com.wjf.androidutils.utils.JUMP_TO_BleServiceFragment
import com.wjf.androidutils.utils.JUMP_TO_BlueClientFragment
import com.wjf.androidutils.utils.JUMP_TO_BlueFragment
import com.wjf.androidutils.utils.JUMP_TO_BlueServiceFragment
import com.wjf.androidutils.utils.JUMP_TO_DesignFragment
import com.wjf.androidutils.utils.JUMP_TO_ExceptionFragment
import com.wjf.androidutils.utils.JUMP_TO_FileFragment
import com.wjf.androidutils.utils.JUMP_TO_ImgLoaderFragment
import com.wjf.androidutils.utils.JUMP_TO_PersistentFragment
import com.wjf.androidutils.utils.JUMP_TO_ReflectFragment
import com.wjf.androidutils.utils.JUMP_TO_ToastFragment
import com.wjf.androidutils.utils.JUMP_TO_WebViewFragment
import com.wjf.moduleutils.PermissionUtil
import com.wjf.moduleutils.ScreenUtils
import com.wjf.moduleutils.StatusBar
import com.wjf.moduleutils.singleClick

class TitleBarActivity : AppCompatActivity() {


    companion object{
        fun newInstance(context: Context, jumpTo: String, position: Int = -1){
            val intent = Intent(context, TitleBarActivity::class.java)
            intent.putExtra(JUMP_TO,jumpTo)
            if (position != -1){
                intent.putExtra(IMG_POSITION,position)
            }
            context.startActivity(intent)
        }
    }

    //首先定义请求变量
    private var permissionList = ArrayList<String>().apply {
        add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        add(Manifest.permission.READ_PHONE_STATE)
    }

    //请求状态码，请求码的作用是与回调函数进行匹配的，这样就可以对不同权限操作进行不同的提示
    val REQUEST_CODE = 1

    lateinit var linearBack: LinearLayout
    lateinit var fragment: Fragment
    lateinit var tvPageTitle: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val statusBar = StatusBar(this)
        statusBar.setColor(R.color.transparent)
        setContentView(R.layout.activity_title_bar)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU){
            permissionList.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        }else{
            permissionList.add(Manifest.permission.READ_MEDIA_AUDIO)
            permissionList.add(Manifest.permission.READ_MEDIA_IMAGES)
            permissionList.add(Manifest.permission.READ_MEDIA_VIDEO)
        }
        PermissionUtil.instance.requestPermissions(this,permissionList,REQUEST_CODE)

        initView()
        initClick()
    }

    private fun initView(){
        linearBack = findViewById(R.id.linear_back)
        tvPageTitle = findViewById(R.id.tv_page_title)
        val commonTitle = findViewById<RelativeLayout>(R.id.common_title)
        val viewStatus = findViewById<View>(R.id.view_status)
        val layoutParams = viewStatus.layoutParams
        layoutParams.height = ScreenUtils.instance.getStatusBarHeight()
        viewStatus.layoutParams = layoutParams

        when(intent.getStringExtra(JUMP_TO)){

            JUMP_TO_DesignFragment -> {
                tvPageTitle.text = "设计模式"
                fragment = DesignFragment()
            }

            JUMP_TO_PersistentFragment -> {
                tvPageTitle.text = "持久化存储"
                fragment = PersistentFragment()
            }

            JUMP_TO_ToastFragment -> {
                tvPageTitle.text = "Toast"
                fragment = ToastFragment()
            }

            JUMP_TO_ArrayFragment -> {
                tvPageTitle.text = "Array"
                fragment = ArrayFragment()
            }

            JUMP_TO_FileFragment -> {
                tvPageTitle.text = "FileFragment"
                fragment = FileFragment()
            }

            JUMP_TO_ExceptionFragment -> {
                tvPageTitle.text = "ExceptionFragment"
                fragment = ExceptionFragment()
            }

            JUMP_TO_ReflectFragment -> {
                tvPageTitle.text = "ReflectFragment"
                fragment = ReflectFragment()
            }

            JUMP_TO_ImgLoaderFragment -> {
                tvPageTitle.text = "ImgLoaderFragment"
                fragment = ImgLoaderFragment()
            }

            JUMP_TO_WebViewFragment -> {
                tvPageTitle.text = "WebViewFragment"
                fragment = WebViewFragment()
            }

            JUMP_TO_AnimFragment -> {
                tvPageTitle.text = "AnimFragment"
                fragment = AnimFragment()
            }

            JUMP_TO_BlueFragment -> {
                tvPageTitle.text = "BlueFragment"
                fragment = BlueFragment()
            }

            JUMP_TO_BlueClientFragment -> {
                tvPageTitle.text = "BlueClientFragment"
                fragment = BlueClientFragment()
            }

            JUMP_TO_BlueServiceFragment -> {
                tvPageTitle.text = "BlueServiceFragment"
                fragment = BlueServiceFragment()
            }


            JUMP_TO_BleClientFragment -> {
                tvPageTitle.text = "BleClientFragment"
                fragment = BleClientFragment()
            }

            JUMP_TO_BleServiceFragment -> {
                tvPageTitle.text = "BleServiceFragment"
                fragment = BleServiceFragment()
            }

            else -> {
                commonTitle.visibility = View.GONE
                fragment = HomeFragment()
            }

        }

        supportFragmentManager.beginTransaction().replace(R.id.info_content, fragment).commit()

    }

    fun initClick(){

        linearBack.singleClick {


            onBackPressed()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}