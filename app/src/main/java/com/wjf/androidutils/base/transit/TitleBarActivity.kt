package com.wjf.androidutils.base.transit

import android.Manifest
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.wjf.androidutils.R
import com.wjf.androidutils.ui.designPattern.DesignFragment
import com.wjf.androidutils.ui.home.HomeActivity
import com.wjf.androidutils.utils.DeviceUtils
import com.wjf.androidutils.utils.IMG_POSITION
import com.wjf.androidutils.utils.JUMP_TO
import com.wjf.androidutils.utils.JUMP_TO_DesignFragment
import com.wjf.androidutils.utils.StatusBar
import com.wjf.androidutils.utils.singleClick

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
    var PERMISSIONS_STORAGE = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.MANAGE_EXTERNAL_STORAGE
    )

    //请求状态码，请求码的作用是与回调函数进行匹配的，这样就可以对不同权限操作进行不同的提示
    val REQUEST_EXTERNAL_STORAGE = 1

    lateinit var linearBack: LinearLayout
    lateinit var fragment: Fragment
    lateinit var tvPageTitle: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val statusBar = StatusBar(this)
        statusBar.setColor(R.color.transparent)

        setContentView(R.layout.activity_title_bar)
        ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE)
        initView()
        initClick()
    }

    private fun initView(){
        linearBack = findViewById(R.id.linear_back)
        tvPageTitle = findViewById(R.id.tv_page_title)
        val commonTitle = findViewById<RelativeLayout>(R.id.common_title)
        val viewStatus = findViewById<View>(R.id.view_status)
        val layoutParams = viewStatus.layoutParams
        layoutParams.height = DeviceUtils.getStatusBarHeight()
        viewStatus.layoutParams = layoutParams

        when(intent.getStringExtra(JUMP_TO)){

            JUMP_TO_DesignFragment -> {
                tvPageTitle.text = "设计模式"
                fragment = DesignFragment()
            }

            else -> {
                commonTitle.visibility = View.GONE
                fragment = HomeActivity()
            }

        }

        supportFragmentManager.beginTransaction().replace(R.id.info_content, fragment).commit()

    }

    fun initClick(){

        linearBack.singleClick {


            onBackPressed()
        }
    }
}