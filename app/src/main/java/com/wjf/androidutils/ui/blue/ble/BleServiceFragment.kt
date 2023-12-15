package com.wjf.androidutils.ui.blue.ble

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.wjf.androidutils.base.MVVMBaseFragment
import com.wjf.androidutils.databinding.FragmentBleServiceBinding
import com.wjf.androidutils.ui.home.HomeViewModel
import com.wjf.modulebluetooth.ble.BleDev
import com.wjf.modulebluetooth.ble.BleUtils
import com.wjf.modulebluetooth.ble.callback.BleCallback
import com.wjf.modulebluetooth.ble.callback.BleCallbackImpl
import com.wjf.moduleutils.ToastUtils

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/15 9:18
 *
 */

class BleServiceFragment : MVVMBaseFragment<HomeViewModel, FragmentBleServiceBinding>(), BleCallback {
    
    override fun initViewModel() = ViewModelProviders.of(this).get(HomeViewModel::class.java)

    override fun initViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentBleServiceBinding {
        return FragmentBleServiceBinding.inflate(inflater,container,false)
    }

    override fun initView() {
        BleCallbackImpl.setGattCallback(this)
        BleUtils.instance.bleServiceSetting()

    }

    override fun connected() {
        
    }

    override fun disConnected() {
        
    }

    override fun scanning(bleDev: BleDev) {
        
    }

    override fun notify(msg: String) {
        activity?.runOnUiThread {
            ToastUtils.instance.show(msg)
            binding.tvBleServiceTips.append("\n\n$msg".trimIndent())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        BleUtils.instance.bleServiceClose()
    }
}