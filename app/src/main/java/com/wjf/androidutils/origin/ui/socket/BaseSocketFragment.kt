package com.wjf.androidutils.origin.ui.socket

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.wjf.modulesocket.terminal.SocketServer
import com.wjf.androidutils.origin.base.MVVMBaseFragment
import com.wjf.androidutils.databinding.FragmentBaseSocketBinding
import com.wjf.androidutils.origin.ui.home.HomeViewModel
import com.wjf.androidutils.origin.ui.socket.adapter.MsgAdapter
import com.wjf.modulesocket.bean.Message
import com.wjf.modulesocket.terminal.callback.MessageCallback
import com.wjf.modulesocket.terminal.SocketClient
import com.wjf.modulesocket.utils.SocketUtils
import com.wjf.moduleutils.ToastUtils

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/15 18:05
 *
 */

open class BaseSocketFragment : MVVMBaseFragment<HomeViewModel, FragmentBaseSocketBinding>(), MessageCallback {

    lateinit var etMsg: EditText
    lateinit var btnSendMsg: Button
    lateinit var ivMore: ImageView

    //Socket服务是否打开
    var openSocket = false

    //Socket服务是否连接
    var connectSocket = false

    //消息列表
    private val messages = ArrayList<Message>()

    //消息适配器
    private lateinit var msgAdapter: MsgAdapter

    private var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>? = null

    override fun initViewModel() = ViewModelProviders.of(this).get(HomeViewModel::class.java)

    override fun initViewBinding(inflater: LayoutInflater, container: ViewGroup?) = FragmentBaseSocketBinding.inflate(inflater,container,false)

    override fun initView() {
        etMsg = binding.layBottomSheetEdit.etMsg
        btnSendMsg = binding.layBottomSheetEdit.btnSendMsg
        ivMore = binding.layBottomSheetEdit.ivMore
        //初始化BottomSheet
        initBottomSheet()
        //初始化列表
        msgAdapter = MsgAdapter(messages)
        binding.rvMsg.apply {
            layoutManager = LinearLayoutManager(mView.context)
            adapter = msgAdapter
        }
        
    }

    /**
     * 设置服务端页面标题
     */
    fun setServerTitle(startService: View.OnClickListener) =
        setTitle("服务端", "IP：${SocketUtils.getIp()}", "开启服务", startService)

    /**
     * 设置客户端页面标题
     */
    fun setClientTitle(connectService: View.OnClickListener) =
        setTitle(mTitle = "客户端", funcTitle = "连接服务", click = connectService)

    /**
     * 设置标题
     */
    private fun setTitle(
        mTitle: String, mSubtitle: String = "", funcTitle: String,
        click: View.OnClickListener
    ) {
        binding.toolbar.apply {
            title = mTitle
            subtitle = mSubtitle
            setNavigationOnClickListener { activity?.finish() }
        }
        binding.tvFunc.text = funcTitle
        binding.tvFunc.setOnClickListener(click)
    }

    /**
     * 开启服务
     */
    fun startServer() {
        openSocket = true
        SocketServer.instance.startServer(this)
        ToastUtils.instance.show("开启服务")
        binding.tvFunc.text = "关闭服务"
    }

    /**
     * 停止服务
     */
    fun stopServer() {
        openSocket = false
        SocketServer.instance.close()
        ToastUtils.instance.show("关闭服务")
        binding.tvFunc.text = "开启服务"
    }

    /**
     * 连接服务
     */
    fun connectServer(ipAddress: String) {
        connectSocket = true
        SocketClient.instance.connectServer(ipAddress, this)
        ToastUtils.instance.show("连接服务")
        binding.tvFunc.text = "关闭连接"
    }

    /**
     * 关闭连接
     */
    fun closeConnect() {
        connectSocket = false
        SocketClient.instance.closeConnect()
        ToastUtils.instance.show("关闭连接")
        binding.tvFunc.text = "连接服务"
    }

    /**
     * 发送到客户端
     */
    fun sendToClient(msg: String) {
        SocketServer.instance.sendToClient(msg)
        etMsg.setText("")
        updateList(true, msg)
    }

    /**
     * 发送到服务端
     */
    fun sendToServer(msg: String) {
        SocketClient.instance.sendToServer(msg)
        etMsg.setText("")
        updateList(true, msg)
    }

    /**
     * 初始化BottomSheet
     */
    private fun initBottomSheet() {
        bottomSheetBehavior =
            BottomSheetBehavior.from(binding.layBottomSheetEdit.bottomSheet).apply {
                state = BottomSheetBehavior.STATE_HIDDEN
                isHideable = false
                isDraggable = false
            }
        //BottomSheet显示隐藏的相关处理
        bottomSheetBehavior!!.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {

            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {}
        })
    }

    /**
     * 更新列表
     */
    private fun updateList(isMyself: Boolean, msg: String) {
        messages.add(Message(isMyself, msg))
        activity?.runOnUiThread {
            (if (messages.size == 0) 0 else messages.size - 1).apply {
                msgAdapter.notifyItemChanged(this)
                binding.rvMsg.smoothScrollToPosition(this)
            }
        }
    }

    /**
     * 接收客户端消息
     */
    override fun receiveMsg(ipAddress: String, msg: String) = updateList(false, msg)

    /**
     * 其他消息
     */
    override fun otherMsg(msg: String) {
        Log.d("__TAG", "otherMsg: $msg")
    }
}