package com.wjf.androidutils.ui.home

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import com.wjf.androidutils.R
import com.wjf.androidutils.base.MVVMBaseFragment
import com.wjf.androidutils.base.transit.TitleBarActivity
import com.wjf.androidutils.utils.CoroutineUtils.launch
import com.wjf.androidutils.utils.JUMP_TO_DesignFragment
import com.wjf.androidutils.utils.LogUtils
import com.wjf.androidutils.utils.handler.HandlerCallback

class HomeActivity : MVVMBaseFragment<HomeViewModel>() , HandlerCallback, View.OnClickListener {

    companion object{
        lateinit var handlerCallback: HandlerCallback
    }

    lateinit var btnDesign: Button

    override fun initViewModel(): HomeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

    override fun initContentViewID(): Int = R.layout.fragment_home

    override fun initView() {
        handlerCallback = this
        btnDesign = mView.findViewById(R.id.btn_design)
        btnDesign.setOnClickListener(this)
        val btnSave = mView.findViewById<Button>(R.id.btn_save)
        val btnGet  = mView.findViewById<Button>(R.id.btn_get)
        val ivShow  = mView.findViewById<ImageView>(R.id.iv_show)
        val tvShow  = mView.findViewById<TextView>(R.id.tv_show)


        btnSave.setOnClickListener {
            launch {
//                incrementCounter()
            }
        }

        btnGet.setOnClickListener {


        }
    }


    override fun handlerCallback() {
        LogUtils.d("__handlerCallback","handlerCallback")
    }

    override fun onClick(v: View?) {

        when(v){

            btnDesign -> {
                TitleBarActivity.newInstance(v.context,JUMP_TO_DesignFragment)
            }
        }
    }
}