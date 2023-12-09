package com.wjf.moduleimgloader

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.wjf.androidutils.R
import com.wjf.moduleimgloader.transformation.CircleWithBorder
import com.wjf.moduleimgloader.transformation.RoundedSpecifyCorners
import com.wjf.moduleutils.BitmapUtils


/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/8 16:18
 *
 */

object ImgLoaderUtils {

    /**
     * 加载图片
     */
    fun loadImg(id : Int, iv : ImageView) {
        GlideApp.with(iv.context).load(id).into(iv)
    }

    /**
     * gif
     * 播放一次
     * 循环播放
     */
    fun loadGif(id : Int, iv : ImageView, looperCount : Int = -1){
        GlideApp.with(iv.context)
            .asGif()
            .load(id)
            .listener(object : RequestListener<GifDrawable>{
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<GifDrawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: GifDrawable?,
                    model: Any?,
                    target: Target<GifDrawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    resource?.setLoopCount(looperCount)
                    resource?.registerAnimationCallback(object : Animatable2Compat.AnimationCallback(){

                        override fun onAnimationStart(drawable: Drawable?) {
                            super.onAnimationStart(drawable)
                        }
                        override fun onAnimationEnd(drawable: Drawable?) {
                            super.onAnimationEnd(drawable)
                        }

                    })
                    return false
                }

            })
            .into(iv)
    }

    /**
     * bitmap
     */
    fun loadBitmap(bitmap : Bitmap, iv : ImageView){
        GlideApp.with(iv.context).load(BitmapUtils.bitmap2ByteArray(bitmap)).into(iv)
    }

    /**
     * 圆形图
     */
    fun loadCircle(id : Int, iv : ImageView){
        GlideApp.with(iv.context).load(id).centerCrop().apply(RequestOptions.bitmapTransform(CircleCrop())).into(iv)
    }

    /**
     * 带边框圆形图
     */
    fun loadCircleWithBorder(id : Int, iv : ImageView, borderWidth : Float = 1f){

        GlideApp.with(iv.context)
            .load(id)
            .transform(CircleWithBorder(borderWidth,iv.context.resources.getColor(R.color.video_bg)))
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(iv)
    }

    /**
     * 圆角图
     * diskCacheStrategy(DiskCacheStrategy.NONE)
     */
    fun loadCorner(id : Int, iv : ImageView, corner : Int = 50){
        GlideApp
            .with(iv.context)
            .load(id)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(corner)))
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(iv)
    }

    /**
     * 指定圆角边圆角图
     */
    fun loadCornerSpecify(id : Int, iv : ImageView, corner : Int = 50){
        GlideApp
            .with(iv.context)
            .load(id)
            .transform(RoundedSpecifyCorners(corner,0, RoundedSpecifyCorners.CornerType.TOP_LEFT))
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(iv)
    }
}