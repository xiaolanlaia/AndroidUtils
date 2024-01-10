package com.wjf.modulesocket.terminal

import android.os.Handler
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.wjf.modulesocket.terminal.callback.MessageCallback
import com.wjf.modulesocket.utils.SOCKET_PORT
import com.wjf.moduleutils.LogUtils
import com.wjf.moduleutils.ThreadPoolUtils
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.io.OutputStream
import java.net.Socket

/**
 * Socket客户端
 */
class SocketClient : LifecycleEventObserver {

    companion object{
        val instance by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { SocketClient() }
    }

    private var socket: Socket? = null

    private var outputStream: OutputStream? = null
    private var inputStream: InputStream? = null

    private var inputStreamReader: InputStreamReader? = null

    private var mCallback: MessageCallback? = null

    //心跳发送间隔
    private val HEART_SPACETIME = 3 * 1000L

    private var mHandler: Handler? = null

    /**
     * 连接服务
     */
    fun connectServer(ipAddress: String, callback: MessageCallback) {
        mHandler = Handler()
        mCallback = callback
        ThreadPoolUtils.instance.cachedThreadPool().execute {
            try {
                socket = Socket(ipAddress, SOCKET_PORT)
                socket?.receiveBufferSize = 64 * 1024
                //开启心跳,每隔3秒钟发送一次心跳
                mHandler?.post(mHeartRunnable)
                clientLoop(socket)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    /**
     * 关闭连接
     */
    fun closeConnect() {
        mHandler?.removeMessages(HEART_SPACETIME.toInt())
        mHandler = null
        inputStreamReader?.close()
        inputStreamReader = null
        outputStream?.close()
        outputStream = null
        inputStream?.close()
        inputStream = null
        socket?.close()
        socket = null
        mCallback = null
        //关闭线程池
        ThreadPoolUtils.instance.shutdownSingleThreadExecutor()
        ThreadPoolUtils.instance.shutdownCachedThreadPool()
    }

    /**
     * 发送数据至服务器
     * @param msg 要发送至服务器的字符串
     */
    fun sendToServer(msg: String) {
        ThreadPoolUtils.instance.singleThreadExecutor().execute {
            if (socket == null) {
                mCallback?.otherMsg("客户端还未连接")
                return@execute
            }
            if (socket!!.isClosed) {
                mCallback?.otherMsg("Socket已关闭")
                return@execute
            }
            outputStream = socket?.getOutputStream()
            try {
                outputStream?.write(msg.toByteArray())
                outputStream?.flush()
            } catch (e: IOException) {
                e.printStackTrace()
                mCallback?.otherMsg("向服务端发送消息: $msg 失败")
            }
        }
    }

    private val mHeartRunnable = Runnable { sendHeartbeat() }

    /**
     * 发送心跳消息
     */
    private fun sendHeartbeat() {
        val msg = "洞幺洞幺，呼叫洞拐，听到请回答，听到请回答，Over!"
        ThreadPoolUtils.instance.singleThreadExecutor().execute {
            if (socket == null) {
                mCallback?.otherMsg("客户端还未连接")
                return@execute
            }
            if (socket!!.isClosed) {
                mCallback?.otherMsg("Socket已关闭")
                return@execute
            }
            outputStream = socket?.getOutputStream()
            try {
                outputStream?.write(msg.toByteArray())
                outputStream?.flush()
                //发送成功以后，重新建立一个心跳消息
                mHandler?.postDelayed(mHeartRunnable, HEART_SPACETIME)
                Log.i("__SocketClient-1", msg)
            } catch (e: IOException) {
                e.printStackTrace()
                mCallback?.otherMsg("向服务端发送消息: $msg 失败")
            }
        }
    }

    fun clientLoop(socket: Socket?){
        if (socket == null) return

        ThreadPoolUtils.instance.cachedThreadPool().execute {

            try {
                inputStream = socket.getInputStream()
                if (inputStream == null) return@execute
                val buffer = ByteArray(1024)
                var len: Int
                var receiveStr = ""
                if (inputStream!!.available() == 0) {
                    Log.e("__SocketClient-2", "inputStream.available() == 0")
                }
                while (inputStream!!.read(buffer).also { len = it } != -1) {
                    receiveStr += String(buffer, 0, len, Charsets.UTF_8)
                    if (len < 1024) {
                        socket.inetAddress.hostAddress?.let {
                            if (receiveStr == "洞拐收到，洞拐收到，Over!") {//收到来自服务端的心跳回复消息
                                Log.i("__SocketClient-3", "洞拐收到，洞拐收到，Over!")
                                //准备回复
                            } else {
                                mCallback?.receiveMsg(it, receiveStr)
                            }
                        }
                        receiveStr = ""
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {

        when(event){
            Lifecycle.Event.ON_DESTROY ->{
                LogUtils.d("__event-DESTROY","1")
                closeConnect()
            }

            else -> {}
        }
    }


}