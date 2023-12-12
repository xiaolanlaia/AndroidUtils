package com.wjf.androidutils.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.wjf.androidutils.base.MVVMBaseFragment
import com.wjf.androidutils.databinding.FragmentDeviceBinding
import com.wjf.androidutils.ui.home.HomeViewModel
import com.wjf.moduleutils.DeviceUtils
import com.wjf.moduleutils.LogUtils
import com.wjf.moduleutils.singleClick

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/4 18:14
 *
 */

class DeviceFragment : MVVMBaseFragment<HomeViewModel, FragmentDeviceBinding>() {
    override fun initViewModel() = ViewModelProviders.of(this).get(HomeViewModel::class.java)

    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDeviceBinding {
        return FragmentDeviceBinding.inflate(inflater,container,false)
    }

    override fun initView() {
        binding.btnManufacturer.singleClick {
            LogUtils.d("__DeviceUtils-getManufacturer","${DeviceUtils.instance.getManufacturer()}")
        }
        binding.btnProduct.singleClick {
            LogUtils.d("__DeviceUtils-getProduct","${DeviceUtils.instance.getProduct()}")

        }
        binding.btnBrand.singleClick {
            LogUtils.d("__DeviceUtils-getBrand","${DeviceUtils.instance.getBrand()}")

        }
        binding.btnModel.singleClick {
            LogUtils.d("__DeviceUtils-getModel","${DeviceUtils.instance.getModel()}")
        }
        binding.btnBoard.singleClick {
            LogUtils.d("__DeviceUtils-getBoard","${DeviceUtils.instance.getBoard()}")
        }
        binding.btnDevice.singleClick {
            LogUtils.d("__DeviceUtils-getDevice","${DeviceUtils.instance.getDevice()}")
        }
        binding.btnFingerprint.singleClick {
            LogUtils.d("__DeviceUtils-getFingerprint","${DeviceUtils.instance.getFingerprint()}")
        }
        binding.btnRam.singleClick {
            LogUtils.d("__DeviceUtils-getRAMInfo","${DeviceUtils.instance.getRAMInfo()}")
        }
    }
}