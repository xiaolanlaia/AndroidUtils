package com.wjf.androidutils.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2022/10/19 15:29
 */
abstract class MVVMBaseFragment<VM : ViewModel, VB: ViewBinding> : Fragment() {
    lateinit var vm: VM
    lateinit var binding: VB
    lateinit var mView: View
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = initViewBinding(inflater,container)
        vm = initViewModel()
        mView = binding.root
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initClick()
        initData()
    }
    abstract fun initViewModel(): VM
    abstract fun initViewBinding(inflater: LayoutInflater, container: ViewGroup?): VB
    abstract fun initView()
    open fun initClick(){}
    open fun initData(){}
}