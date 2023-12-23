package com.wjf.androidutils.origin.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.wjf.androidutils.origin.base.MVVMBaseFragment
import com.wjf.androidutils.databinding.FragmentConstraintLayoutBinding
import com.wjf.androidutils.origin.ui.home.HomeViewModel

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/20 17:55
 *
 */

class ConstraintLayoutFragment : MVVMBaseFragment<HomeViewModel, FragmentConstraintLayoutBinding>() {
    override fun initViewModel() = ViewModelProviders.of(this).get(HomeViewModel::class.java)

    override fun initViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentConstraintLayoutBinding.inflate(inflater,container,false)

    override fun initView() {
        
    }
}