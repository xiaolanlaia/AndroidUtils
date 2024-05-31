package com.wjf.moduleutils.camera

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.ImageFormat
import android.graphics.SurfaceTexture
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraCaptureSession
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraDevice
import android.hardware.camera2.CameraManager
import android.hardware.camera2.CaptureFailure
import android.hardware.camera2.CaptureRequest
import android.hardware.camera2.CaptureResult
import android.hardware.camera2.TotalCaptureResult
import android.media.ImageReader
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import android.util.Size
import android.view.Surface
import android.view.TextureView
import androidx.core.content.ContextCompat
import com.wjf.moduleutils.BitmapUtils
import com.wjf.moduleutils.LogUtils
import com.wjf.moduleutils.ToastUtils


class Camera2Helper(
    private var mActivity: Activity,
    private var textureViews: ArrayList<TextureView>,
    private var camera2Callback: Camera2Callback?
) {

    companion object {
        //预览的宽度
        const val PREVIEW_WIDTH = 1920
        //预览的高度
        const val PREVIEW_HEIGHT = 1080
        //保存图片的宽度
        const val SAVE_WIDTH = 2048
        //保存图片的高度
        const val SAVE_HEIGHT = 1536
    }

    private lateinit var mCameraManager: CameraManager



    //摄像头方向
    private var mCameraSensorOrientation = 0
    //默认使用后置摄像头
    private var mCameraFacing = CameraCharacteristics.LENS_FACING_BACK
    //是否可以拍照
    private var canTakePic = true
    //是否可以切换摄像头
    private var canExchangeCamera = false

    private var mCameraHandler: Handler
    private val handlerThread = HandlerThread("CameraThread")
    //预览大小
    private var mPreviewSize = Size(PREVIEW_WIDTH, PREVIEW_HEIGHT)
    //保存图片大小
    private var mSavePicSize = Size(SAVE_WIDTH, SAVE_HEIGHT)

    //拍照的相机id
    private var takePicId = "0"


    private var cameraIds = ArrayList<String>()
    private var cameraDevices = ArrayList<CameraDevice?>()
    private var cameraCaptureSessions = ArrayList<CameraCaptureSession?>()

    private var mImageReaderList = ArrayList<ImageReader?>()

    init {
        handlerThread.start()
        mCameraHandler = Handler(handlerThread.looper)

        initCameraInfo()

    }

    /**
     * 初始化
     */
    private fun initCameraInfo() {
        mCameraManager = mActivity.getSystemService(Context.CAMERA_SERVICE) as CameraManager

        //根据textureViews的数量去设置相机id的数量
        textureViews.forEachIndexed { index, TextureView ->
            cameraIds.add(mCameraManager.cameraIdList[index])
        }


        if (cameraIds.isEmpty()) {
            ToastUtils.instance.show("没有可用相机")
            return
        }

        cameraIds.forEachIndexed { index, id ->

            val cameraCharacteristics = mCameraManager.getCameraCharacteristics(id)
            queryCameraResolution(id)

            //获取摄像头方向
            mCameraSensorOrientation = cameraCharacteristics.get(CameraCharacteristics.SENSOR_ORIENTATION)!!

            textureViews[index].surfaceTexture?.setDefaultBufferSize(mPreviewSize.width, mPreviewSize.height)

            if (ContextCompat.checkSelfPermission(mActivity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ToastUtils.instance.show("没有相机权限！")
                return
            }

            openCamera(id,textureViews[index], index)

        }
    }

    @SuppressLint("MissingPermission")
    private fun openCamera(cameraId: String, textureView: TextureView, index: Int) {
        mCameraManager.openCamera(cameraId, object : CameraDevice.StateCallback() {
            override fun onOpened(camera: CameraDevice) {
                cameraDevices.add(camera)
                LogUtils.d("__C2H-4", "onOpened")
                // 创建预览会话
                createCaptureSession(camera, textureView, index)

            }

            override fun onDisconnected(camera: CameraDevice) {
                cameraDevices.remove(camera)

                LogUtils.d("__C2H-5", "onDisconnected")
            }

            override fun onError(camera: CameraDevice, error: Int) {
                LogUtils.d("__C2H-6", "onError $error")
                cameraDevices.remove(camera)
                camera.close()
                ToastUtils.instance.show("打开相机失败！$error")
            }
        }, mCameraHandler)
    }

    /**
     * 创建预览会话
     */
    private fun createCaptureSession(camera: CameraDevice, textureView: TextureView, index: Int) {



        val imageReaderPre = ImageReader.newInstance(mPreviewSize.width, mPreviewSize.height, ImageFormat.YUV_420_888, 1)
        //预览回调
        imageReaderPre.setOnImageAvailableListener({ reader ->
            val image = reader?.acquireNextImage()

            image?.close()
        }, mCameraHandler)

        //拍照回调
        mImageReaderList.add(ImageReader.newInstance(mSavePicSize.width, mSavePicSize.height, ImageFormat.YUV_420_888, 1))
        mImageReaderList[index]?.setOnImageAvailableListener({ reader ->
            val image = reader.acquireNextImage()

            image.close()
        }, mCameraHandler)

        val surfaceTexture = textureView.surfaceTexture
        surfaceTexture?.setDefaultBufferSize(textureView.width, textureView.height)
        val surface = Surface(surfaceTexture)


        val surfaces = mutableListOf<Surface>().apply {
            add(surface)
            add(imageReaderPre.surface)
            add(mImageReaderList[index]!!.surface)
        }


        // 为相机预览，创建一个CameraCaptureSession对象
        camera.createCaptureSession(surfaces, object : CameraCaptureSession.StateCallback() {
            override fun onConfigureFailed(session: CameraCaptureSession) {
                ToastUtils.instance.show("开启预览会话失败！")
            }

            override fun onConfigured(session: CameraCaptureSession) {
                cameraCaptureSessions.add(session)
                val captureRequestBuilder = camera.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW)

                captureRequestBuilder.addTarget(surface)  // 将CaptureRequest的构建器与Surface对象绑定在一起
                captureRequestBuilder.addTarget(imageReaderPre.surface)  // 将CaptureRequest的构建器与Surface对象绑定在一起
                captureRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE, CaptureRequest.CONTROL_AE_MODE_ON_AUTO_FLASH)      // 闪光灯
                captureRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE, CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE) // 自动对焦

                session.setRepeatingRequest(captureRequestBuilder.build(), mCaptureCallBack, mCameraHandler)
            }

        }, mCameraHandler)
    }

    private val mCaptureCallBack = object : CameraCaptureSession.CaptureCallback() {

        override fun onCaptureCompleted(session: CameraCaptureSession, request: CaptureRequest, result: TotalCaptureResult) {
            super.onCaptureCompleted(session, request, result)
//            LogUtils.d("__C2H-7","onCaptureCompleted")
            canExchangeCamera = true
            canTakePic = true
        }

        override fun onCaptureFailed(session: CameraCaptureSession, request: CaptureRequest, failure: CaptureFailure) {
            super.onCaptureFailed(session, request, failure)
            LogUtils.d("__C2H-8","onCaptureFailed reason:${failure.reason}")
            ToastUtils.instance.show("开启预览失败！")
        }
    }


    /**
     * 拍照
     */
    fun takePic(textureView: TextureView) {
        val index = textureViews.indexOf(textureView)
        takePicId = cameraIds[index]
        LogUtils.d("__image-takePic-index","index:${index}")
        if (cameraDevices[index] == null) {
            LogUtils.d("__image-takePic-null1","${cameraDevices.size}")
        }else if (!textureViews[index].isAvailable){
            LogUtils.d("__image-takePic-null2","1")

        }else if (!canTakePic){
            LogUtils.d("__image-takePic-null3","1")

        }else{
            LogUtils.d("__image-takePic-have","1")
        }
        cameraDevices[index]?.apply {

            val captureRequestBuilder = createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE)
            captureRequestBuilder.addTarget(mImageReaderList[index]!!.surface)

            captureRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE, CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE) // 自动对焦
            captureRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE, CaptureRequest.CONTROL_AE_MODE_ON_AUTO_FLASH)     // 闪光灯
            captureRequestBuilder.set(CaptureRequest.JPEG_ORIENTATION, mCameraSensorOrientation)      //根据摄像头方向对保存的照片进行旋转，使其为"自然方向"
            cameraCaptureSessions[index]?.capture(captureRequestBuilder.build(), null, mCameraHandler)
                    ?: ToastUtils.instance.show("拍照异常！")
        }
    }

    /**
     * 切换摄像头
     */
    fun exchangeCamera(index: Int) {
        if (cameraDevices[index] == null || !canExchangeCamera || !textureViews[index].isAvailable) return

        mCameraFacing = if (mCameraFacing == CameraCharacteristics.LENS_FACING_FRONT){
            CameraCharacteristics.LENS_FACING_BACK
        } else{
            CameraCharacteristics.LENS_FACING_FRONT
        }

        mPreviewSize = Size(PREVIEW_WIDTH, PREVIEW_HEIGHT) //重置预览大小
        releaseCamera()
        initCameraInfo()
    }


    fun releaseCamera() {
        cameraCaptureSessions.forEach {
            it?.close()
        }

        cameraDevices.forEach {
            it?.close()
        }
        mImageReaderList.forEach {
            it?.close()
        }

        canExchangeCamera = false

        releaseThread()
    }

    fun releaseThread() {
        handlerThread.quitSafely()
    }
    private fun setVideoFrameRate(requestBuilder: CaptureRequest.Builder) {
    }
    fun queryFrameRates () {

//        var cameraId: String? = null
//        cameraId = try {
//            mCameraManager.cameraIdList[0]
//        } catch (e: CameraAccessException) {
//            throw RuntimeException(e)
//        }
//        var characteristics: CameraCharacteristics? = null
//        characteristics = try {
//            cameraId?.let { mCameraManager.getCameraCharacteristics(it) }
//        } catch (e: CameraAccessException) {
//            throw RuntimeException(e)
//        }
//        // 该相机的FPS范围
//        if (characteristics != null) {
//            fpsRanges = characteristics.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES) as Array<Range<Int>>
//        };
//        Log.d("FPS", "SYNC_MAX_LATENCY_PER_FRAME_CONTROL: " + Arrays.toString(fpsRanges));

    }

    private fun queryCameraResolution(id: String) {
        val cameraCharacteristics = mCameraManager.getCameraCharacteristics(id)
        val map = cameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)
        // 获取摄像头分辨率
        var cameraId: String? = null
        cameraId = try {
            mCameraManager.cameraIdList[0]
        } catch (e: CameraAccessException) {
            throw RuntimeException(e)
        }

        val fpsRange = cameraCharacteristics.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES)
            ?.get(1);
        val minFps = fpsRange?.lower;
        val maxFps = fpsRange?.upper;

        Log.d("fpsRange", "摄像机帧率范围：$minFps - $maxFps");

        val sizes = map!!.getOutputSizes(SurfaceTexture::class.java)
        // 打印支持的分辨率信息
        for (size in sizes) {
            Log.d("__C2H-supportSize", "支持分辨率 - width:${size.width} height:${size.height}")
        }
        val supportLevel = cameraCharacteristics.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL)
        if (supportLevel == CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL_LEGACY) {
            ToastUtils.instance.show("相机${id}硬件不支持新特性")
        }

    }

    // 设置曝光时间时，您可能还需要设置相关的AE（自动曝光）和AWB（自动白平衡）模式，以及锁定参数，以防止相机硬件在拍照时自动调整这些设置
    var mCaptureSession: CameraCaptureSession? = null
    // 设置曝光时间的方法
    private fun setExposureTime(exposureTimeNanos:Long) {
        try {
            // 构建CaptureRequest.Builder
//            val builder = mCameraDevice!!.createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE)
//            builder.set<Long>(CaptureRequest.SENSOR_EXPOSURE_TIME, exposureTimeNanos)
//
//            // 设置请求模式为手动模式
//            builder.set<Int>(CaptureRequest.CONTROL_MODE, CameraMetadata.TONEMAP_MODE_GAMMA_VALUE)
//
//            // 设置AE和AWB模式为OFF
//            builder.set(CaptureRequest.CONTROL_AE_MODE, CaptureRequest.CONTROL_AE_MODE_OFF)
//            builder.set(CaptureRequest.CONTROL_AWB_MODE, CaptureRequest.CONTROL_AWB_MODE_OFF)
//
//            // 设置闪光灯为关闭状态
//            builder.set(CaptureRequest.CONTROL_AE_LOCK, false)
//            builder.set(CaptureRequest.CONTROL_AWB_LOCK, false)
//
//            // 将新的CaptureRequest应用到相机
//            mCaptureSession!!.setRepeatingRequest(builder.build(), null, null)

        } catch (e: CameraAccessException) {
            e.printStackTrace()
        }

    }
    /**
     * 拍照 备用
     */
    fun takePicture(textureView: TextureView) {
        try {
            val index = textureViews.indexOf(textureView)
            takePicId = cameraIds[index]
            LogUtils.d("__image-takePic-index","index:${index}")
            if (cameraDevices[index] == null) {
                LogUtils.d("__image-takePic-null1","${cameraDevices.size}")
            }else if (!textureViews[index].isAvailable){
                LogUtils.d("__image-takePic-null2","1")

            }else if (!canTakePic){
                LogUtils.d("__image-takePic-null3","1")

            }else{
                LogUtils.d("__image-takePic-have","1")

            }
            cameraDevices[index]?.apply {

                val captureRequestBuilder = createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE)
                captureRequestBuilder.addTarget(mImageReaderList[index]!!.surface)

                captureRequestBuilder.set(CaptureRequest.SENSOR_EXPOSURE_TIME, 2000000000L)
                // 设置AE和AWB模式为OFF
                captureRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE, CaptureRequest.CONTROL_AE_MODE_OFF)
                captureRequestBuilder.set(CaptureRequest.CONTROL_AWB_MODE, CaptureRequest.CONTROL_AWB_MODE_OFF)

                // 设置闪光灯为关闭状态
                captureRequestBuilder.set(CaptureRequest.CONTROL_AE_LOCK, false)
                captureRequestBuilder.set(CaptureRequest.CONTROL_AWB_LOCK, false)
                captureRequestBuilder.set(CaptureRequest.JPEG_ORIENTATION, mCameraSensorOrientation)      //根据摄像头方向对保存的照片进行旋转，使其为"自然方向"

                // ... 设置其他相机参数 ...
                cameraCaptureSessions[index]?.capture(captureRequestBuilder.build(), object : CameraCaptureSession.CaptureCallback() {
                    override fun onCaptureCompleted(session: CameraCaptureSession, request: CaptureRequest, result: TotalCaptureResult) {
                        super.onCaptureCompleted(session, request, result)
                        // 获取曝光时间
                        val sensorExposureTime = result.get(CaptureResult.SENSOR_EXPOSURE_TIME)
                        if (sensorExposureTime != null) {
                            // 曝光时间单位是纳秒
                            Log.d("ExposureTime", "摄像头曝光时间: $sensorExposureTime 纳秒") //30001000 纳秒
                        }
                    }
                }, mCameraHandler)
                    ?: ToastUtils.instance.show("拍照异常！")
            }

        } catch (e: CameraAccessException) {
            e.printStackTrace()
        }
    }
}