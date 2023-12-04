package com.wjf.androidutils.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.wjf.androidutils.base.MVVMBaseFragment
import com.wjf.androidutils.databinding.FragmentScreenBinding
import com.wjf.androidutils.ui.home.HomeViewModel
import com.wjf.androidutils.utils.LogUtils
import com.wjf.androidutils.utils.ScreenUtils
import com.wjf.androidutils.utils.singleClick

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/4 17:54
 *
 */

class ScreenFragment : MVVMBaseFragment<HomeViewModel, FragmentScreenBinding>() {

    override fun initViewModel() = ViewModelProviders.of(this).get(HomeViewModel::class.java)

    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentScreenBinding {
        return FragmentScreenBinding.inflate(inflater,container,false)

    }

    override fun initView() {
        binding.btnStatusBar.singleClick {
            LogUtils.d("__ScreenUtils-1","${ScreenUtils.getStatusBarHeight()}")
        }
        binding.btnDeviceWidth.singleClick {
            LogUtils.d("__ScreenUtils-2","${ScreenUtils.getDeviceWidth()}")
        }
        binding.btnDeviceHeight.singleClick {
            LogUtils.d("__ScreenUtils-3","${ScreenUtils.getDeviceHeight()}")
        }
        binding.btnImei.singleClick {
            LogUtils.d("__ScreenUtils-4","${ScreenUtils.getIMEI()}")
        }
    }
}