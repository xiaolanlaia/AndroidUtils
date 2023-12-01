package com.wjf.androidutils.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.wjf.androidutils.base.MVVMBaseFragment
import com.wjf.androidutils.base.transit.TitleBarActivity
import com.wjf.androidutils.databinding.FragmentHomeBinding
import com.wjf.androidutils.utils.CoroutineUtils.launch
import com.wjf.androidutils.utils.JUMP_TO_ArrayFragment
import com.wjf.androidutils.utils.JUMP_TO_DesignFragment
import com.wjf.androidutils.utils.JUMP_TO_PersistentFragment
import com.wjf.androidutils.utils.JUMP_TO_ToastFragment
import com.wjf.androidutils.utils.LogUtils
import com.wjf.androidutils.utils.handler.HandlerCallback

class HomeFragment : MVVMBaseFragment<HomeViewModel,FragmentHomeBinding>() , HandlerCallback, View.OnClickListener {

    companion object{
        lateinit var handlerCallback: HandlerCallback
    }


    override fun initViewModel(): HomeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

    override fun initViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentHomeBinding{

        return FragmentHomeBinding.inflate(inflater,container,false)
    }

    override fun initView() {
        handlerCallback = this
        binding.btnDesign.setOnClickListener(this)
        binding.btnPersistent.setOnClickListener(this)
        binding.btnToast.setOnClickListener(this)
        binding.btnArray.setOnClickListener(this)

        binding.btnSave.setOnClickListener {
            launch {
//                incrementCounter()
            }
        }

        binding.btnGet.setOnClickListener {


        }
    }


    override fun handlerCallback() {
        LogUtils.d("__handlerCallback","handlerCallback")
    }

    override fun onClick(v: View?) {

        when(v){

            binding.btnDesign -> {
                TitleBarActivity.newInstance(v.context,JUMP_TO_DesignFragment)
            }

            binding.btnPersistent -> {
                TitleBarActivity.newInstance(v.context,JUMP_TO_PersistentFragment)
            }

            binding.btnToast -> {
                TitleBarActivity.newInstance(v.context, JUMP_TO_ToastFragment)
            }

            binding.btnArray -> {
                TitleBarActivity.newInstance(v.context, JUMP_TO_ArrayFragment)
            }
        }
    }
}