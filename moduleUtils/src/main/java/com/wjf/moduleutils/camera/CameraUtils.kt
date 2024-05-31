package com.wjf.moduleutils.camera

import android.graphics.ImageFormat
import android.hardware.Camera
import android.util.Log
import android.view.SurfaceHolder
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.wjf.moduleutils.LogUtils

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2024/1/4 15:57
 *
 */

/**
 *         lifecycle.addObserver(CameraUtils.newInstance())
 */

class CameraUtils : LifecycleEventObserver {

    companion object{
        private var instance: CameraUtils? = null

        fun newInstance(): CameraUtils{
            if (instance == null){
                synchronized(CameraUtils::class){
                    if (instance == null){
                        instance = CameraUtils()
                    }
                }
            }
            return instance!!
        }
    }

    private var surfaceHolderList: ArrayList<SurfaceHolder>? = null
    var mCameraList: ArrayList<Camera?>? = null
    private var parametersList: ArrayList<Camera.Parameters?>? = null


    init {
        surfaceHolderList = ArrayList<SurfaceHolder>()
        mCameraList = ArrayList<Camera?>()
        parametersList = ArrayList<Camera.Parameters?>()
    }

    fun setSurfaceView(surfaceHolder: SurfaceHolder, previewData:(ByteArray) -> Unit) {
        openCamera(surfaceHolder,previewData)
    }


    fun getCameraList(): ArrayList<Camera?>?{
        return mCameraList
    }


    fun takePicture(fileName: String = "camera.jpg",cameraId: Int, savePath: (String) -> Unit) {
        mCameraList?.get(cameraId)?.takePicture(null, null, Camera.PictureCallback { data, camera ->


            camera.startPreview()

        })
    }


    private fun openCamera(surfaceHolder: SurfaceHolder, previewData:(ByteArray) -> Unit) {
        surfaceHolderList?.add(surfaceHolder)

        if (surfaceHolderList != null){
            val cameraId = surfaceHolderList!!.size - 1
            if (cameraId < 0) return
            try {
                val camera = Camera.open(cameraId)
                mCameraList?.add(camera)
                val params = camera!!.parameters
                params.setPreviewSize(1280,720)
//                params.setPreviewSize(576,432)
                params.setPictureSize(1280,720)
                parametersList?.add(params)
                parametersList?.get(cameraId)?.pictureFormat = ImageFormat.JPEG
                //如果硬件设备不支持所填参数则会报错
                camera.parameters = parametersList?.get(cameraId)
                camera.setPreviewDisplay(surfaceHolderList!![cameraId])
                //设置方向
//                mCameraList?.get(cameraId)?.setDisplayOrientation(180)
                camera.startPreview()
                camera.setPreviewCallback(object : Camera.PreviewCallback{
                    override fun onPreviewFrame(data: ByteArray, camera: Camera?) {
                        previewData(data)
                    }

                })


                // 获取所有支持的预览尺寸
                val supportedSizes = camera.parameters.supportedPreviewSizes

                // 打印所有支持的分辨率
                for (size in supportedSizes) {
                    LogUtils.d("__supportSize","cameraId: ${cameraId} Width: ${size.width}, Height: ${size.height}")
                }

            } catch (e: Exception) {
                Log.d("__CameraUtils-openCamera-e","${e.message}")
            }
        }
    }

    private fun stopCamera() {

        if (mCameraList != null){
            for (i in 0 until mCameraList!!.size) {
                mCameraList!![i]?.setPreviewCallback(null)
                mCameraList!![i]?.stopPreview()
                mCameraList!![i]?.lock()
                mCameraList!![i]?.release()

            }
        }
        mCameraList?.clear()
        mCameraList = null
        surfaceHolderList?.clear()
        surfaceHolderList = null
        parametersList?.clear()
        parametersList = null
        instance = null
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {

        when (event) {
            Lifecycle.Event.ON_STOP -> {
                stopCamera()
            }
        }
    }

}