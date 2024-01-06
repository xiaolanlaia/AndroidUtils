package com.wjf.androidutils.origin.ui.service

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.wjf.androidutils.databinding.FragmentServiceSelectBinding
import com.wjf.androidutils.origin.base.MVVMBaseFragment
import com.wjf.androidutils.origin.base.transit.TitleBarActivity
import com.wjf.androidutils.origin.ui.home.HomeViewModel
import com.wjf.androidutils.origin.utils.JUMP_TO_ServiceBindFragment
import com.wjf.androidutils.origin.utils.JUMP_TO_ServiceForegroundFragment
import com.wjf.androidutils.origin.utils.JUMP_TO_ServiceMessageFragment
import com.wjf.androidutils.origin.utils.JUMP_TO_ServiceStartFragment
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
        binding.tvServiceStart.singleClick { TitleBarActivity.newInstance(mView.context, JUMP_TO_ServiceStartFragment) }
        binding.tvServiceBind.singleClick { TitleBarActivity.newInstance(mView.context, JUMP_TO_ServiceBindFragment) }
        binding.tvServiceMessage.singleClick { TitleBarActivity.newInstance(mView.context, JUMP_TO_ServiceMessageFragment) }
        binding.tvServiceForeground.singleClick { TitleBarActivity.newInstance(mView.context, JUMP_TO_ServiceForegroundFragment) }
    }
}