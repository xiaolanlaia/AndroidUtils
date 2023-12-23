package com.wjf.androidutils.origin.ui.blue.bt

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.wjf.androidutils.origin.base.MVVMBaseFragment
import com.wjf.androidutils.databinding.FragmentBlueServiceBinding
import com.wjf.androidutils.databinding.LayoutSendBinding
import com.wjf.androidutils.origin.ui.home.HomeViewModel
import com.wjf.modulebluetooth.BLUE_CONNECTED
import com.wjf.modulebluetooth.BLUE_DISCONNECTED
import com.wjf.modulebluetooth.BLUE_MSG
import com.wjf.modulebluetooth.bt.BlueUtils
import com.wjf.moduleutils.ToastUtils
import com.wjf.moduleutils.singleClick
import com.wjf.modulebluetooth.bt.callback.BlueCallback
import com.wjf.modulebluetooth.bt.callback.BlueCallbackImpl

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/13 18:36
 *
 */

@SuppressLint("MissingPermission")
class BlueServiceFragment : MVVMBaseFragment<HomeViewModel, FragmentBlueServiceBinding>(), BlueCallback {

    lateinit var layoutSendBinding : LayoutSendBinding

    override fun initViewModel() = ViewModelProviders.of(this).get(HomeViewModel::class.java)

    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentBlueServiceBinding {
        return FragmentBlueServiceBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        layoutSendBinding = LayoutSendBinding.bind(binding.root)
        lifecycle.addObserver(BlueCallbackImpl(mView.context,this))
        BlueUtils.instance.bluetoothServerSocket()

        layoutSendBinding.btnSendMsg.singleClick {
            BlueUtils.instance.sendMsg(layoutSendBinding.inputMsg.text.toString())
        }
        layoutSendBinding.btnSendFile.singleClick {
            BlueUtils.instance.sendFile(layoutSendBinding.inputFile.text.toString())
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        BlueUtils.instance.close()
        socketNotify(BLUE_DISCONNECTED, null)
    }

    override fun scanStarted() {
        
    }

    override fun scanFinished() {
        
    }

    override fun scanning(device: BluetoothDevice) {
        
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
        activity?.runOnUiThread(Runnable {
            if (activity?.isDestroyed == true) {
                return@Runnable
            }
            var msg: String? = null
            when (state) {
                BLUE_CONNECTED -> {
                    activity?.runOnUiThread {
                        val dev = obj as BluetoothDevice?
                        msg = String.format("与%s(%s)连接成功", dev!!.name, dev.address)
                        binding.tvTips.text = msg
                    }
                }

                BLUE_DISCONNECTED -> {
                    activity?.runOnUiThread {
                        BlueUtils.instance.bluetoothServerSocket()
                        msg = "连接断开,正在重新监听..."
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
        })
        
    }

    override fun notifyState(state: String) {
        ToastUtils.instance.show(state)
    }
}