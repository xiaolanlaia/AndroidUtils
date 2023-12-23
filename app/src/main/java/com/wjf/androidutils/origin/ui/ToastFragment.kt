package com.wjf.androidutils.origin.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.wjf.androidutils.origin.base.MVVMBaseFragment
import com.wjf.androidutils.databinding.FragmentToastBinding
import com.wjf.androidutils.origin.ui.home.HomeViewModel
import com.wjf.moduleutils.ToastUtils
import com.wjf.moduleutils.singleClick

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/11/30 15:09
 *
 */

class ToastFragment : MVVMBaseFragment<HomeViewModel, FragmentToastBinding>() {

    override fun initViewModel() = ViewModelProviders.of(this).get(HomeViewModel::class.java)

    override fun initViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentToastBinding {

        return FragmentToastBinding.inflate(inflater,container,false)
    }

    override fun initView() {
        binding.tvToast.singleClick {
            ToastUtils.instance.show("测试字体大小")
        }
    }

}