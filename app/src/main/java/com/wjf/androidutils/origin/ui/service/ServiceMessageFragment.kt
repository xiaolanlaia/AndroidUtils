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
import com.wjf.androidutils.databinding.FragmentServiceMessageBinding
import com.wjf.androidutils.origin.base.MVVMBaseFragment
import com.wjf.androidutils.origin.service.MyMessageService
import com.wjf.androidutils.origin.ui.home.HomeViewModel
import com.wjf.androidutils.origin.utils.COMMON_FLAG
import com.wjf.moduleutils.LogUtils
import com.wjf.moduleutils.ToastUtils
import com.wjf.moduleutils.singleClick

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2024/1/5 18:11
 *
 */

class ServiceMessageFragment : MVVMBaseFragment<HomeViewModel, FragmentServiceMessageBinding>() {

    override fun initViewModel() = ViewModelProviders.of(this).get(HomeViewModel::class.java)

    override fun initViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentServiceMessageBinding.inflate(inflater, container, false)

    override fun initClick() {
        super.initClick()

        binding.tvBindMessage.singleClick {
            mView.context.bindService(
                Intent(mView.context, MyMessageService::class.java),
                messageConnection,
                Service.BIND_AUTO_CREATE
            )
        }

        binding.tvUnbindMessage.singleClick {
            if (messenger != null) {
                mView.context.unbindService(messageConnection)
                messenger = null
            }
        }

        binding.tvBindMessageSend.singleClick {
            if (messenger != null) {
                val msg = Message.obtain(null, MyMessageService.MSG_SAY_HELLO, 0, 0)
                msg.replyTo = receiverReplyMessenger
                try {
                    messenger?.send(msg)
                } catch (e: Exception) {
                    LogUtils.d("__err-tvBindMessageSend", "${e.message}")
                }
            }
        }
    }

    var messenger: Messenger? = null
    private val messageConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            messenger = Messenger(service)
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            messenger = null
        }

    }

    private val receiverReplyMessenger = Messenger(ReceiverReplyMsgHandler())

    class ReceiverReplyMsgHandler : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            LogUtils.d("__MyMessageService-reply-receive", "${msg.what}")
            when (msg.what) {
                MyMessageService.MSG_SAY_HELLO -> {
                    ToastUtils.instance.show("${msg.data.getString(COMMON_FLAG)}")

                }
            }
        }
    }
}