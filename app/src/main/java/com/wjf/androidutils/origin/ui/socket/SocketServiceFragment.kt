package com.wjf.androidutils.origin.ui.socket

import com.wjf.modulesocket.terminal.SocketServer
import com.wjf.moduleutils.ToastUtils

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/15 18:19
 *
 */

class SocketServiceFragment : BaseSocketFragment() {

    override fun initView() {
        super.initView()
        lifecycle.addObserver(SocketServer.instance)
        //开启服务/停止服务
        setServerTitle { if (openSocket) stopServer() else startServer() }

        //发送消息给服务端
        btnSendMsg.setOnClickListener {
            val msg = etMsg.text.toString().trim()
            if (msg.isEmpty()) {
                ToastUtils.instance.show("请输入要发送的信息");
                return@setOnClickListener
            }
            //检查是否能发送消息
            val isSend = if (openSocket) openSocket else false
            if (!isSend) {
                ToastUtils.instance.show("当前未开启服务或连接服务");
                return@setOnClickListener
            }
            sendToClient(msg)
        }
    }
}