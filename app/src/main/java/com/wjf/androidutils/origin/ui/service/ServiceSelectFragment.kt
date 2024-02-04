package com.wjf.androidutils.origin.ui.service

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.wjf.androidutils.databinding.FragmentServiceSelectBinding
import com.wjf.androidutils.origin.base.MVVMBaseFragment
import com.wjf.androidutils.origin.base.transit.TitleBarActivity
import com.wjf.androidutils.origin.ui.home.HomeViewModel
import com.wjf.androidutils.origin.utils.JumpSealed
import com.wjf.moduleutils.singleClick

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2024/1/5 18:06
 *
 */

class ServiceSelectFragment : MVVMBaseFragment<HomeViewModel, FragmentServiceSelectBinding>() {
    override fun initViewModel() =  ViewModelProviders.of(this).get(HomeViewModel::class.java)

    override fun initViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentServiceSelectBinding.inflate(inflater,container,false)

    override fun initClick() {
        binding.tvServiceStart.singleClick { TitleBarActivity.newInstance(mView.context, JumpSealed.ServiceStart.jumpTag) }
        binding.tvServiceBind.singleClick { TitleBarActivity.newInstance(mView.context, JumpSealed.ServiceBind.jumpTag) }
        binding.tvServiceMessage.singleClick { TitleBarActivity.newInstance(mView.context, JumpSealed.ServiceMessage.jumpTag) }
        binding.tvServiceForeground.singleClick { TitleBarActivity.newInstance(mView.context, JumpSealed.ServiceForeground.jumpTag) }
    }
}