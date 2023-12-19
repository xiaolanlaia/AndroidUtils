package com.wjf.androidutils.ui.blue.ble

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.wjf.androidutils.base.MVVMBaseFragment
import com.wjf.androidutils.databinding.FragmentBleClientBinding
import com.wjf.androidutils.ui.blue.ble.adapter.BleDevAdapter
import com.wjf.androidutils.ui.home.HomeViewModel
import com.wjf.modulebluetooth.ble.BleDev
import com.wjf.modulebluetooth.ble.BleUtils
import com.wjf.modulebluetooth.ble.callback.BleCallback
import com.wjf.modulebluetooth.ble.callback.BleCallbackImpl
import com.wjf.moduleutils.ToastUtils
import com.wjf.moduleutils.singleClick

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/15 9:08
 *
 */

class BleClientFragment : MVVMBaseFragment<HomeViewModel, FragmentBleClientBinding>(), BleCallback {

    private var mBleDevAdapter: BleDevAdapter? = null

    override fun initViewModel() = ViewModelProviders.of(this).get(HomeViewModel::class.java)

    override fun initViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentBleClientBinding {
        return FragmentBleClientBinding.inflate(inflater,container,false)
    }

    override fun initView() {
        lifecycle.addObserver(BleCallbackImpl(this))

        binding.rvBle.layoutManager = LinearLayoutManager(mView.context)
        mBleDevAdapter = BleDevAdapter()
        binding.rvBle.adapter = mBleDevAdapter

        binding.btnBleScan.singleClick {

            if (BleUtils.instance.getScanState()) {
                ToastUtils.instance.show("正在扫描...", 0)
            } else {
                mBleDevAdapter!!.reScan()
            }
        }

        binding.btnBleRead.singleClick {
            BleUtils.instance.read()
        }

        binding.btnBleWrite.singleClick {
            BleUtils.instance.write(binding.etBleWrite.text.toString())
        }

        binding.btnBleNotify.singleClick {
            BleUtils.instance.setNotify()
        }
    }

    override fun connected() {
        BleUtils.instance.setConnectState()
    }

    override fun disConnected() {
        BleUtils.instance.closeConnect()
    }

    override fun scanning(bleDev: BleDev) {
        mBleDevAdapter?.updateList(bleDev)
    }

    override fun notify(msg: String) {
        activity?.runOnUiThread {
            ToastUtils.instance.show(msg)
            binding.tvBleTips.append("\n\n$msg".trimIndent())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        BleUtils.instance.closeConnect()
    }
}