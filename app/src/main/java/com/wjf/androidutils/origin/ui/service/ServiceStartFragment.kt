package com.wjf.androidutils.origin.ui.service

import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.wjf.androidutils.databinding.FragmentServiceStartBinding
import com.wjf.androidutils.origin.base.MVVMBaseFragment
import com.wjf.androidutils.origin.service.MyMessageService
import com.wjf.androidutils.origin.service.MyStartService
import com.wjf.androidutils.origin.ui.home.HomeViewModel
import com.wjf.androidutils.origin.utils.COMMON_FLAG
import com.wjf.moduleutils.LogUtils
import com.wjf.moduleutils.ToastUtils
import com.wjf.moduleutils.singleClick

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2024/1/5 8:05
 *
 */

class ServiceStartFragment : MVVMBaseFragment<HomeViewModel, FragmentServiceStartBinding>() {

    private lateinit var startIntent: Intent


    override fun initViewModel() = ViewModelProviders.of(this).get(HomeViewModel::class.java)

    override fun initViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentServiceStartBinding.inflate(inflater, container, false)

    override fun initView() {}

    override fun initData() {
        startIntent = Intent(mView.context, MyStartService::class.java)
    }

    override fun initClick() {

        binding.tvStart.singleClick {
            startIntent.putExtra(COMMON_FLAG, "101")
            it.context.startService(startIntent)
        }

        binding.tvStop.singleClick { mView.context.stopService(startIntent) }


    }

    

}