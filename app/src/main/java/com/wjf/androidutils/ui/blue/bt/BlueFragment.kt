package com.wjf.androidutils.ui.blue.bt

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.wjf.androidutils.base.MVVMBaseFragment
import com.wjf.androidutils.base.transit.TitleBarActivity
import com.wjf.androidutils.databinding.FragmentBlueBinding
import com.wjf.androidutils.ui.home.HomeViewModel
import com.wjf.androidutils.utils.JUMP_TO_BlueClientFragment
import com.wjf.androidutils.utils.JUMP_TO_BlueServiceFragment
import com.wjf.modulebluetooth.bt.BlueUtils
import com.wjf.moduleutils.ToastUtils
import com.wjf.moduleutils.singleClick

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/13 18:30
 *
 */

class BlueFragment : MVVMBaseFragment<HomeViewModel, FragmentBlueBinding>() {

    private val REQUEST_CODE = 2

    override fun initViewModel() = ViewModelProviders.of(this).get(HomeViewModel::class.java)

    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentBlueBinding {
        return FragmentBlueBinding.inflate(inflater,container,false)
    }

    override fun initView() {

        binding.btnBtClient.singleClick {
            TitleBarActivity.newInstance(it.context,JUMP_TO_BlueClientFragment)
        }

        binding.btnBtServer.singleClick {
            TitleBarActivity.newInstance(it.context,JUMP_TO_BlueServiceFragment)
        }
    }

    override fun initData() {
        super.initData()

        BlueUtils.instance.requestPermission(requireActivity(),REQUEST_CODE)


        if (!BlueUtils.instance.isSupport()) {
            ToastUtils.instance.show("本机没有找到蓝牙硬件或驱动！")
            activity?.finish()
            return
        } else {

            //直接开启蓝牙
            BlueUtils.instance.enable()
            //跳转到设置界面
            //startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE), 112);
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}