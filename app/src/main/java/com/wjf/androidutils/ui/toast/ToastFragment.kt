package com.wjf.androidutils.ui.toast

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.wjf.androidutils.base.MVVMBaseFragment
import com.wjf.androidutils.databinding.FragmentToastBinding
import com.wjf.androidutils.utils.ToastUtils
import com.wjf.androidutils.utils.singleClick

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/11/30 15:09
 *
 */

class ToastFragment : MVVMBaseFragment<ToastViewModel,FragmentToastBinding>() {

    override fun initViewModel() = ViewModelProviders.of(this).get(ToastViewModel::class.java)

    override fun initViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentToastBinding {

        return FragmentToastBinding.inflate(inflater,container,false)
    }

    override fun initView() {
        binding.tvToast.singleClick {
            ToastUtils.show("测试字体大小")
        }
    }

}