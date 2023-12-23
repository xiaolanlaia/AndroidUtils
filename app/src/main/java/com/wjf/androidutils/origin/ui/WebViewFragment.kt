package com.wjf.androidutils.origin.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.wjf.androidutils.origin.base.MVVMBaseFragment
import com.wjf.androidutils.databinding.FragmentWebViewBinding
import com.wjf.androidutils.origin.ui.home.HomeViewModel
import com.wjf.moduleutils.H5_RETURN_TO_MAIN
import com.wjf.moduleutils.webView.ReturnToMainCallback
import com.wjf.moduleutils.webView.ReturnToMainInterface
import com.wjf.moduleutils.webView.WebViewUtils

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/9 13:48
 *
 */

class WebViewFragment : MVVMBaseFragment<HomeViewModel, FragmentWebViewBinding>() {
    override fun initViewModel() = ViewModelProviders.of(this).get(HomeViewModel::class.java)

    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentWebViewBinding {
        return FragmentWebViewBinding.inflate(inflater,container,false)
    }

    override fun initView() {
        if (activity != null && !requireActivity().isDestroyed){
            binding.frameWenView.addView(WebViewUtils.instance.getWebView())
            val returnToMainInterface = ReturnToMainInterface(object : ReturnToMainCallback {
                override fun returnToMain() {
                    //h5调用了返回首页
                    activity?.finish()
                }

            })
            WebViewUtils.instance.addJavascriptInterface(returnToMainInterface, H5_RETURN_TO_MAIN)
            WebViewUtils.instance.loadUrl(mView.context,"https://www.baidu.com")

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        WebViewUtils.instance.recycler()
    }
}