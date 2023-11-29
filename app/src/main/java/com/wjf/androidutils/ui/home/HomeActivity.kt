package com.wjf.androidutils.ui.home

import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import com.wjf.androidutils.R
import com.wjf.androidutils.base.MVVMBaseFragment
import com.wjf.androidutils.utils.CoroutineUtils.launch
import com.wjf.androidutils.utils.LogUtils
import com.wjf.androidutils.utils.handler.HandlerCallback

class HomeActivity : MVVMBaseFragment<HomeViewModel>() , HandlerCallback {

    companion object{
        lateinit var handlerCallback: HandlerCallback
    }

    override fun initViewModel(): HomeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

    override fun initContentViewID(): Int = R.layout.fragment_home

    override fun initView() {
        handlerCallback = this
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
}