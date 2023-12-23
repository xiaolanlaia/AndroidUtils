package com.wjf.androidutils.origin.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.wjf.androidutils.R
import com.wjf.androidutils.origin.base.MVVMBaseFragment
import com.wjf.androidutils.databinding.FragmentImgLoaderBinding
import com.wjf.androidutils.origin.ui.home.HomeViewModel
import com.wjf.moduleimgloader.ImgLoaderUtils
import com.wjf.moduleutils.BitmapUtils

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/8 17:34
 *
 */

class ImgLoaderFragment : MVVMBaseFragment<HomeViewModel, FragmentImgLoaderBinding>() {

    override fun initViewModel() = ViewModelProviders.of(this).get(HomeViewModel::class.java)

    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentImgLoaderBinding {
        return FragmentImgLoaderBinding.inflate(inflater,container,false)
    }

    override fun initView() {

        ImgLoaderUtils.loadImg(R.mipmap.test_0,binding.ivImg)
        ImgLoaderUtils.loadGif(R.mipmap.duck,binding.ivGif,1)
        ImgLoaderUtils.loadGif(R.mipmap.duck,binding.ivGifLoop)
        ImgLoaderUtils.loadBitmap(BitmapUtils.instance.resource2Bitmap(R.mipmap.ic_launcher),binding.ivBitmap)
        ImgLoaderUtils.loadCircle(R.mipmap.test_0,binding.ivCircle)
        ImgLoaderUtils.loadCircleWithBorder(R.mipmap.test_0,binding.ivCircleBorder,1f)
        ImgLoaderUtils.loadCorner(R.mipmap.test_0,binding.ivCorner)
        ImgLoaderUtils.loadCornerSpecify(R.mipmap.test_0,binding.ivCornerSpecify)
    }
}