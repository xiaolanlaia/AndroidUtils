package com.wjf.androidutils.ui.socket

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.wjf.androidutils.base.MVVMBaseFragment
import com.wjf.androidutils.base.transit.TitleBarActivity
import com.wjf.androidutils.databinding.FragmentSelectTypeBinding
import com.wjf.androidutils.ui.home.HomeViewModel
import com.wjf.androidutils.utils.JUMP_TO_SocketClientFragment
import com.wjf.androidutils.utils.JUMP_TO_SocketServiceFragment
import com.wjf.moduleutils.singleClick

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/15 18:20
 *
 */

class SelectTypeFragment : MVVMBaseFragment<HomeViewModel, FragmentSelectTypeBinding>() {

    override fun initViewModel() = ViewModelProviders.of(this).get(HomeViewModel::class.java)

    override fun initViewBinding(inflater: LayoutInflater, container: ViewGroup?) = FragmentSelectTypeBinding.inflate(inflater,container,false)


    override fun initView() {
        binding.btnServer.singleClick {
            TitleBarActivity.newInstance(it.context, JUMP_TO_SocketServiceFragment)

        }
        binding.btnClient.singleClick {
            TitleBarActivity.newInstance(it.context, JUMP_TO_SocketClientFragment)

        }

    }
}