package com.wjf.androidutils.origin.ui.blue.bt

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.wjf.androidutils.origin.base.MVVMBaseFragment
import com.wjf.androidutils.databinding.FragmentBlueClientBinding
import com.wjf.androidutils.databinding.LayoutSendBinding
import com.wjf.androidutils.origin.ui.blue.bt.adapter.BtDevAdapter
import com.wjf.androidutils.origin.ui.home.HomeViewModel
import com.wjf.modulebluetooth.BLUE_CONNECTED
import com.wjf.modulebluetooth.BLUE_DISCONNECTED
import com.wjf.modulebluetooth.BLUE_MSG
import com.wjf.modulebluetooth.bt.BlueUtils
import com.wjf.moduleutils.ToastUtils
import com.wjf.moduleutils.singleClick
import com.wjf.modulebluetooth.bt.callback.BlueCallback
import com.wjf.modulebluetooth.bt.callback.BlueCallbackImpl
import com.wjf.moduleutils.ThreadPoolUtils

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/13 18:35
 *
 */

@SuppressLint("MissingPermission")
class BlueClientFragment : MVVMBaseFragment<HomeViewModel, FragmentBlueClientBinding>(), BlueCallback {


    private val mBtDevAdapter = BtDevAdapter()
    lateinit var layoutSendBinding : LayoutSendBinding

    override fun initViewModel() = ViewModelProviders.of(this).get(HomeViewModel::class.java)

    override fun initViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentBlueClientBinding {
        return FragmentBlueClientBinding.inflate(inflater,container,false)
    }


    override fun initView() {
        layoutSendBinding = LayoutSendBinding.bind(binding.root)

        binding.rvBt.layoutManager = LinearLayoutManager(mView.context)
        binding.rvBt.adapter = mBtDevAdapter

        lifecycle.addObserver(BlueCallbackImpl(mView.context,this))


        binding.btnScan.singleClick {
            mBtDevAdapter.reScan()
        }
        layoutSendBinding.btnSendMsg.singleClick {
            BlueUtils.instance.sendMsg(layoutSendBinding.inputMsg.text.toString())
        }
        layoutSendBinding.btnSendFile.singleClick {
            BlueUtils.instance.sendFile(layoutSendBinding.inputFile.text.toString())
        }

    }


    override fun onDestroy() {
        super.onDestroy()

        socketNotify(BLUE_DISCONNECTED, null)
        ThreadPoolUtils.instance.shutdownCachedThreadPool()
    }

    override fun scanStarted() {
        
    }

    override fun scanFinished() {
        
    }

    override fun scanning(device: BluetoothDevice) {
        mBtDevAdapter.add(device)
    }

    override fun bondRequest() {
        
    }

    override fun bondFail() {
        
    }

    override fun bonding() {
        
    }

    override fun bondSuccess() {
        
    }

    override fun connected() {
        
    }

    override fun disconnected() {
        
    }

    override fun socketNotify(state: Int, obj: Any?) {
        if (activity?.isDestroyed == true) {
            return
        }
        var msg: String? = null
        when (state) {
            BLUE_CONNECTED -> {
                activity?.runOnUiThread {
                    val dev = obj as BluetoothDevice?
                    msg = String.format("与%s(%s)连接成功", dev?.name, dev?.address)
                    binding.tvTips.text = msg
                }
            }

            BLUE_DISCONNECTED -> {
                activity?.runOnUiThread {
                    msg = "连接断开"
                    binding.tvTips.text = msg
                }
            }

            BLUE_MSG -> {
                activity?.runOnUiThread {

                    msg = String.format("\n%s", obj)
                    layoutSendBinding.tvLog.append(msg)
                }
            }
        }
        activity?.runOnUiThread {
            ToastUtils.instance.show("$msg")

        }
        
    }

    override fun notifyState(state: String) {
        ToastUtils.instance.show(state)
    }
}