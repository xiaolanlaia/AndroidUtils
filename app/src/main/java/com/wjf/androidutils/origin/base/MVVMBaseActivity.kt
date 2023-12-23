package com.wjf.androidutils.origin.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.wjf.androidutils.R
import com.wjf.moduleutils.StatusBar

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/15 16:15
 *
 */

abstract class MVVMBaseActivity<VM : ViewModel, VB : ViewBinding> : AppCompatActivity() {

    lateinit var binding: VB
    lateinit var vm: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val statusBar = StatusBar(this)
        statusBar.setColor(R.color.transparent)
        binding = initViewBinding(layoutInflater)
        setContentView(binding.root)
        vm = initViewModel()


        initView()
        initClick()
        initData()
    }

    abstract fun initViewModel(): VM


    abstract fun initView()
    open fun initClick() {}
    open fun initData() {}
    abstract fun initViewBinding(inflater: LayoutInflater): VB

}