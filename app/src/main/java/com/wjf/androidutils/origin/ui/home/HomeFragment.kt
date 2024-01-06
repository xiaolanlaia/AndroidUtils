package com.wjf.androidutils.origin.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.wjf.androidutils.origin.base.MVVMBaseFragment
import com.wjf.androidutils.databinding.FragmentHomeBinding
import com.wjf.androidutils.origin.ui.home.adapter.HomeAdapter
import com.wjf.moduleutils.LogUtils
import com.wjf.moduleutils.handler.HandlerCallback

class HomeFragment : MVVMBaseFragment<HomeViewModel, FragmentHomeBinding>() , HandlerCallback {

    companion object{
        lateinit var handlerCallback: HandlerCallback
    }


    override fun initViewModel(): HomeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

    override fun initViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentHomeBinding{

        return FragmentHomeBinding.inflate(inflater,container,false)
    }

    override fun initView() {
        handlerCallback = this
        val gridLayoutManager = GridLayoutManager(mView.context,3)
        binding.rvHome.layoutManager = gridLayoutManager
        binding.rvHome.setHasFixedSize(true)
        binding.rvHome.adapter = HomeAdapter()

    }


    override fun handlerCallback() {
        LogUtils.d("__handlerCallback","handlerCallback")
    }

}