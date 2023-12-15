package com.wjf.modulesocket.terminal

/**
 * 服务端回调
 */
interface MessageCallback {
    //接收客户端的消息
    fun receiveMsg(ipAddress: String, msg: String)
    //其他消息
    fun otherMsg(msg: String)
}