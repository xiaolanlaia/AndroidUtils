package com.wjf.androidutils.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.wjf.androidutils.R
import com.wjf.androidutils.base.MVVMBaseFragment
import com.wjf.androidutils.databinding.FragmentImgLoaderBinding
import com.wjf.androidutils.ui.home.HomeViewModel
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

        ImgLoaderUtils.loadImg(mView.context, R.mipmap.ic_launcher,binding.ivImg)
        ImgLoaderUtils.loadGif(mView.context, R.mipmap.duck,binding.ivGif,1)
        ImgLoaderUtils.loadGif(mView.context, R.mipmap.duck,binding.ivGifLoop)
        ImgLoaderUtils.loadBitmap(mView.context, BitmapUtils.resource2Bitmap(R.mipmap.ic_launcher),binding.ivBitmap)
    }
}