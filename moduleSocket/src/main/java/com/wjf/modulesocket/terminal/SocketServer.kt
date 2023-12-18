package com.wjf.modulesocket.terminal

import android.util.Log
import com.wjf.modulesocket.utils.SOCKET_PORT
import com.wjf.moduleutils.ThreadPoolUtils
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.net.*

/**
 * Socket服务端
 */
object SocketServer {


    private var socket: Socket? = null

    private var serverSocket: ServerSocket? = null

    private var mCallback: MessageCallback? = null

    private var outputStream: OutputStream? = null

    private var inputStream: InputStream? = null

    var result = true

    /**
     * 开启服务
     */
    fun startServer(callback: MessageCallback): Boolean {
        mCallback = callback
        result = true
        ThreadPoolUtils.instance.getCachedThreadPool().execute {
            try {
                serverSocket = ServerSocket(SOCKET_PORT)
                while (result) {
                    socket = serverSocket?.accept()
                    socket?.receiveBufferSize = 64 * 1024
                    inputStream = socket?.getInputStream()
                    mCallback?.otherMsg("${socket?.inetAddress} to connected")
                    serverLoop()
                }
            } catch (e: IOException) {
                e.printStackTrace()
                result = false
            }
        }
        return result
    }

    /**
     * 关闭服务
     */
    fun stopServer() {
        socket?.apply {
            shutdownInput()
            shutdownOutput()
            close()
        }
        socket = null
        serverSocket?.close()
        serverSocket = null
        mCallback = null
        outputStream?.close()
        outputStream = null
        inputStream?.close()
        inputStream = null
        //关闭线程池
        ThreadPoolUtils.instance.shutdownCachedThreadPool()
    }

    /**
     * 发送到客户端
     */
    fun sendToClient(msg: String) {
        ThreadPoolUtils.instance.getCachedThreadPool().execute {
            if (socket == null) {
                mCallback?.otherMsg("客户端还未连接")
                return@execute
            }
            if (socket!!.isClosed) {
                mCallback?.otherMsg("Socket已关闭")
                return@execute
            }
            outputStream = socket!!.getOutputStream()
            try {

                outputStream?.write(msg.toByteArray())
                outputStream?.flush()
            } catch (e: IOException) {
                e.printStackTrace()
                mCallback?.otherMsg("向客户端发送消息: $msg 失败")
            }
        }
    }

    /**
     * 回复心跳消息
     */
    fun replyHeartbeat() {
        val msg = "洞拐收到，洞拐收到，Over!"
        ThreadPoolUtils.instance.getCachedThreadPool().execute {
            if (socket == null) {
                mCallback?.otherMsg("客户端还未连接")
                return@execute
            }
            if (socket!!.isClosed) {
                mCallback?.otherMsg("Socket已关闭")
                return@execute
            }
            outputStream = socket!!.getOutputStream()
            try {
                outputStream?.write(msg.toByteArray())
                outputStream?.flush()
            } catch (e: IOException) {
                e.printStackTrace()
                mCallback?.otherMsg("向客户端发送消息: $msg 失败")
            }
        }
    }

    private fun serverLoop(){

        if (socket == null || inputStream == null) return
        try {
            val buffer = ByteArray(1024)
            var len: Int
            var receiveStr = ""
            if (inputStream?.available() == 0) {
                Log.e("__SocketServer-1", "inputStream.available() == 0")
            }
            while (inputStream?.read(buffer).also { len = it!! } != -1) {
                receiveStr += String(buffer, 0, len, Charsets.UTF_8)
                if (len < 1024) {
                    socket?.inetAddress?.hostAddress?.let {
                        if (receiveStr == "洞幺洞幺，呼叫洞拐，听到请回答，听到请回答，Over!") {//收到客户端发送的心跳消息
                            //准备回复
                            replyHeartbeat()
                        } else {
                            mCallback?.receiveMsg(it, receiveStr)
                        }
                    }
                    receiveStr = ""
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
            when (e) {
                is SocketTimeoutException -> {
                    Log.e("__SocketServer-2", "连接超时，正在重连")
                }
                is NoRouteToHostException -> {
                    Log.e("__SocketServer-3", "该地址不存在，请检查")
                }
                is ConnectException -> {
                    Log.e("__SocketServer-4", "连接异常或被拒绝，请检查")
                }
                is SocketException -> {
                    when (e.message) {
                        "Already connected" -> Log.e("__SocketServer-5", "连接异常或被拒绝，请检查")
                        "Socket closed" -> Log.e("__SocketServer-6", "连接已关闭")
                    }
                }
            }
        }
    }

}