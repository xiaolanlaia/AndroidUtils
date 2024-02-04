package com.wjf.androidutils.origin.base

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding


abstract class MVVMDialogFragment<VM : ViewModel, VB : ViewBinding> : DialogFragment() {

    lateinit var binding: VB
    lateinit var vm: VM
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
        initData()
        initClick()
    }

    override fun onStart() {
        super.onStart()
        resizeDialogFragment()
    }

    private fun resizeDialogFragment() {
        val mDialog = dialog
        if (null != mDialog) {
            val window = mDialog.window
            if (window != null) {
                val lp = window.attributes
                val metric = DisplayMetrics()
                requireActivity().windowManager.defaultDisplay.getMetrics(metric)
                lp.width = initWidth()// 屏幕宽度（像素）
                lp.height = initHeight() // 屏幕高度（像素）
                window.setLayout(lp.width, lp.height)
            }
        }
    }

    abstract fun initViewModel(): VM
    abstract fun initWidth(): Int
    abstract fun initHeight(): Int
    open fun initView(){}
    open fun initData(){}
    open fun initClick(){}
    abstract fun initViewBinding(inflater: LayoutInflater, container: ViewGroup?): VB
}