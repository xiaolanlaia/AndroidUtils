package com.wjf.androidutils.origin.ui.service

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.wjf.androidutils.databinding.FragmentServiceForegroundBinding
import com.wjf.androidutils.origin.base.MVVMBaseFragment
import com.wjf.androidutils.origin.service.MyForegroundService
import com.wjf.androidutils.origin.ui.home.HomeViewModel
import com.wjf.androidutils.origin.utils.COMMON_FLAG
import com.wjf.moduleutils.singleClick


/**
 * @Description
 * @Author WuJianFeng
 * @Date 2024/1/6 8:05
 *
 */

class ServiceForegroundFragment: MVVMBaseFragment<HomeViewModel, FragmentServiceForegroundBinding>() {

    override fun initViewModel() = ViewModelProviders.of(this).get(HomeViewModel::class.java)

    override fun initViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentServiceForegroundBinding.inflate(inflater,container,false)

    override fun initClick() {
        super.initClick()
        val intent = Intent(mView.context,MyForegroundService::class.java)

        binding.tvForegroundStart.singleClick {
            //0,开启前台服务,1,关闭前台服务
            intent.putExtra(COMMON_FLAG,0)
            mView.context.startService(intent)
        }

        binding.tvForegroundClose.singleClick {
            //0,开启前台服务,1,关闭前台服务
            intent.putExtra(COMMON_FLAG,1)
            mView.context.startService(intent)
        }
    }
}