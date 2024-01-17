package com.wjf.androidutils.origin.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.wjf.androidutils.origin.base.MVVMBaseFragment
import com.wjf.androidutils.databinding.LayoutRecyclerBinding
import com.wjf.androidutils.origin.ui.home.adapter.HomeAdapter
import com.wjf.androidutils.origin.utils.COMMON_FLAG
import com.wjf.moduleutils.LogUtils
import com.wjf.moduleutils.handler.HandlerCallback

class HomeFragment : MVVMBaseFragment<HomeViewModel, LayoutRecyclerBinding>() , HandlerCallback {

    companion object{
        var handlerCallback: HandlerCallback? = null
    }


    override fun initViewModel(): HomeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

    override fun initViewBinding(inflater: LayoutInflater, container: ViewGroup?): LayoutRecyclerBinding{

        return LayoutRecyclerBinding.inflate(inflater,container,false)
    }

    override fun initView() {
        handlerCallback = this
        val gridLayoutManager = GridLayoutManager(mView.context,3)
        binding.rvWidget.layoutManager = gridLayoutManager
        binding.rvWidget.setHasFixedSize(true)
        binding.rvWidget.adapter = HomeAdapter()

    }


    override fun handlerCallback() {
        LogUtils.d("__handlerCallback","handlerCallback")
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        LogUtils.d("__forResult-fragment","requestCode:$requestCode resultCode:$resultCode data:${data?.getStringExtra(COMMON_FLAG)}")
    }

    override fun onDestroy() {
        super.onDestroy()
        handlerCallback = null
    }

}