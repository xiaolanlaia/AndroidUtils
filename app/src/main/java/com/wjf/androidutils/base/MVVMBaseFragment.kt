package com.wjf.androidutils.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2022/10/19 15:29
 */
abstract class MVVMBaseFragment<VM : ViewModel> : Fragment(), CommonMethod {
    lateinit var vm: VM
    lateinit var mView: View
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(initContentViewID(), container, false)
        vm = initViewModel()
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initClick()
        initData()
    }
    abstract fun initViewModel(): VM
    abstract fun initView()
    open fun initClick(){}
    open fun initData(){}
}