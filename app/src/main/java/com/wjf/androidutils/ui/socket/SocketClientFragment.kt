package com.wjf.androidutils.ui.socket

import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.wjf.androidutils.R
import com.wjf.androidutils.databinding.DialogEditIpBinding
import com.wjf.moduleutils.ToastUtils

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/15 18:16
 *
 */

class SocketClientFragment : BaseSocketFragment() {

    override fun initView() {
        super.initView()
        //连接服务/关闭服务
        setClientTitle { if (connectSocket) closeConnect() else showEditDialog() }
        //发送消息给服务端
        btnSendMsg.setOnClickListener {
            val msg = etMsg.text.toString().trim()
            if (msg.isEmpty()) {
                ToastUtils.instance.show("请输入要发送的信息");return@setOnClickListener
            }
            //检查是否能发送消息
            val isSend = if (connectSocket) connectSocket else false
            if (!isSend) {
                ToastUtils.instance.show("当前未开启服务或连接服务");return@setOnClickListener
            }
            sendToServer(msg)
        }
    }

    private fun showEditDialog() {
        val dialogBinding =
            DialogEditIpBinding.inflate(LayoutInflater.from(mView.context), null, false)
        AlertDialog.Builder(mView.context).apply {
            setIcon(R.drawable.ic_connect)
            setTitle("连接Ip地址")
            setView(dialogBinding.root)
            setPositiveButton("确定") { dialog, _ ->
                val ip = dialogBinding.etIpAddress.text.toString()
                if (ip.isEmpty()) {
                    ToastUtils.instance.show("请输入Ip地址");return@setPositiveButton
                }
                connectServer(ip)
                dialog.dismiss()
            }
            setNegativeButton("取消") { dialog, _ -> dialog.dismiss() }
        }.show()
    }

}