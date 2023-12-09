package com.wjf.androidutils.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.wjf.androidutils.base.MVVMBaseFragment
import com.wjf.androidutils.base.transit.TitleBarActivity
import com.wjf.androidutils.databinding.FragmentHomeBinding
import com.wjf.androidutils.utils.JUMP_TO_ArrayFragment
import com.wjf.androidutils.utils.JUMP_TO_DesignFragment
import com.wjf.androidutils.utils.JUMP_TO_ExceptionFragment
import com.wjf.androidutils.utils.JUMP_TO_FileFragment
import com.wjf.androidutils.utils.JUMP_TO_ImgLoaderFragment
import com.wjf.androidutils.utils.JUMP_TO_PersistentFragment
import com.wjf.androidutils.utils.JUMP_TO_ReflectFragment
import com.wjf.androidutils.utils.JUMP_TO_ToastFragment
import com.wjf.moduleutils.LogUtils
import com.wjf.moduleutils.handler.HandlerCallback

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
        binding.btnFile.setOnClickListener(this)
        binding.btnException.setOnClickListener(this)
        binding.btnReflect.setOnClickListener(this)
        binding.btnImgLoader.setOnClickListener(this)

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

            binding.btnFile -> {
                TitleBarActivity.newInstance(v.context, JUMP_TO_FileFragment)
            }

            binding.btnException -> {
                TitleBarActivity.newInstance(v.context, JUMP_TO_ExceptionFragment)
            }

            binding.btnReflect -> {
                TitleBarActivity.newInstance(v.context, JUMP_TO_ReflectFragment)
            }

            binding.btnImgLoader -> {
                TitleBarActivity.newInstance(v.context, JUMP_TO_ImgLoaderFragment)
            }
        }
    }
}