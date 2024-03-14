package com.wjf.androidutils.origin.ui

import android.net.Uri
import android.os.Build
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.webkit.JavascriptInterface
import android.webkit.ValueCallback
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.ViewModelProviders
import com.wjf.androidutils.databinding.FragmentWebViewBinding
import com.wjf.androidutils.origin.base.MVVMBaseFragment
import com.wjf.androidutils.origin.ui.home.HomeViewModel
import com.wjf.moduleutils.LogUtils
import com.wjf.moduleutils.singleClick
import com.wjf.moduleutils.webView.WebViewUtils
import org.json.JSONObject
import java.net.URLDecoder


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

        }
    }

    override fun initClick() {

        val url = "file:///android_asset/h5demo2.html"
        WebViewUtils.instance.getWebView().webViewClient = object : WebViewClient(){

            override fun shouldOverrideUrlLoading(
                view: WebView?,
                url: String
            ): Boolean {
                try {

                    LogUtils.d("__webView","拦截到Url:${url}")
                    //url如果以qiushi开头，就是h5和我们定义的传值协议
                    if (url.startsWith("qiushi")){

                        val uriRequest = Uri.parse(url)
                        val scheme = uriRequest.scheme
                        val action  = uriRequest.host
                        val query   = uriRequest.query
                        if ("qiushi" == scheme) {
                            if (!TextUtils.isEmpty(query)){
                                val names = uriRequest.queryParameterNames
                                val jsonObject = JSONObject()
                                names.forEach {
                                    jsonObject.put(it,uriRequest.getQueryParameter(it)!!)
                                }
                                if ("setH5Info" == action){
                                    if (jsonObject != null && jsonObject.has("params")){
                                        val h5InfoParams = jsonObject.optString("params")
                                        LogUtils.d("__webView","拦截到的参数${h5InfoParams}")
                                    }
                                }
                            }

                        }
                    }else{
                        view?.loadUrl(url)
                    }
                } catch (e: Exception) {
                    LogUtils.d("__err-webview", "${e.message}")
                }

                return true
            }

        }

        binding.tv1.singleClick {

            /**
             * js调用安卓
             */
            //这里必须开启
            WebViewUtils.instance.getWebView().settings.javaScriptEnabled = true
            //把当前JavaH5Activity对象作为androidObject别名传递给js
            //js通过window.androidObject.androidMethod()就可以直接调用安卓的androidMethod方法
            WebViewUtils.instance.getWebView().addJavascriptInterface(this, "androidObject")





        }

        binding.tv2.singleClick {

            WebViewUtils.instance.loadUrl(mView.context,url)

        }

        binding.tv3.singleClick {

            val jsonParams = "123456"
            val url = "javascript:jsMethod($jsonParams)"
            WebViewUtils.instance.loadUrl(mView.context,url)

        }

        binding.tv4.singleClick {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                val jsonParams = "123456"
                //String method = "jsMethod()";//不拼接参数，直接调用js的jsMethod函数
                val method = "jsMethod2($jsonParams)" //拼接参数，就可以把数据传递给js
                WebViewUtils.instance.getWebView().evaluateJavascript(method,
                    ValueCallback<String> { value ->
                        LogUtils.d("__webView", "js返回的数据$value")
                    })
            }

        }

    }

    /**
     * js调用安卓，必须加@JavascriptInterface注释的方法才可以被js调用
     */
    @JavascriptInterface
    fun androidMethod(): String{
        LogUtils.d("__webView-1","1")
        return "从Android返回的数据"
    }

    override fun onDestroy() {
        super.onDestroy()
        WebViewUtils.instance.recycler()
    }
}