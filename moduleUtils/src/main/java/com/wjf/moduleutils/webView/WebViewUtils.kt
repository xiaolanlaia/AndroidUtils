package com.wjf.moduleutils.webView

import android.annotation.SuppressLint
import android.content.Context
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.LinearLayout
import com.wjf.moduleutils.UtilsConstant.utilsContext

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/3/18 8:51
 *
 */

@SuppressLint("StaticFieldLeak")
class WebViewUtils {

    companion object{
        val instance by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { WebViewUtils() }
    }

    var mVebView: WebView? = null
    private var context: Context? = null
    fun getWebView(): WebView {
        if (mVebView == null) {
            synchronized(WebViewUtils::class.java) {
                if (mVebView == null) {
                    initWebView()
                }
            }
        }
        return mVebView!!
    }

    fun recycler(){
        mVebView?.destroy()
        mVebView = null
    }

    fun initWebView() {
        mVebView = WebView(utilsContext)
        val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        mVebView?.layoutParams = params

        mVebView?.webViewClient = object : WebViewClient() {
            override fun onPageFinished(webView: WebView, s: String) {
                super.onPageFinished(webView, s)
            }
        } //防止加载网页时调起系统浏览器
        initWebSetting()
        setClient()

    }

    fun loadUrl(context: Context?, url: String = "") {
        this.context = context

        if (this.context !=null){
            try {
                getWebView().loadUrl(url)
            }catch (e:Exception){

            }
        }
    }

    private fun setClient() {
        mVebView?.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                if (newProgress == 100 && context != null) {}
            }
        }
    }

    @SuppressLint("JavascriptInterface")
    fun addJavascriptInterface(obj: Any, interfaceName: String){
        mVebView?.addJavascriptInterface(obj,interfaceName)
    }

    fun destroyWebView(mViewGroup: ViewGroup?) {
        getWebView().loadDataWithBaseURL(null, "", "text/html", "utf-8", null)
        getWebView().clearHistory()
        mViewGroup?.removeView(getWebView())
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebSetting() {
        //第一个参数把自身传给js 第二个参数是this的一个名字
        val webSettings: WebSettings = mVebView!!.settings
        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.javaScriptEnabled = true
        // 若加载的 html 里有JS 在执行动画等操作，会造成资源浪费（CPU、电量）
        // 在 onStop 和 onResume 里分别把 setJavaScriptEnabled() 给设置成 false 和 true 即可
        //支持插件
        webSettings.layoutAlgorithm = WebSettings.LayoutAlgorithm.NARROW_COLUMNS
        webSettings.saveFormData = true
        //设置自适应屏幕，两者合用
        webSettings.useWideViewPort = true //将图片调整到适合webview的大小
        webSettings.loadWithOverviewMode = true // 缩放至屏幕的大小

        //缩放操作
        webSettings.displayZoomControls = false //隐藏原生的缩放控件

        webSettings.setSupportZoom(false) //支持缩放，默认为true。是下面那个的前提。

        webSettings.builtInZoomControls = false //设置内置的缩放控件。若为false，则该WebView不可缩放

        //其他细节操作
        // webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        webSettings.javaScriptCanOpenWindowsAutomatically = true //支持通过JS打开新窗口

        webSettings.loadsImagesAutomatically = true //支持自动加载图片

        webSettings.defaultTextEncodingName = "utf-8" //设置编码格式

        mVebView?.setBackgroundColor(0)
        webSettings.databaseEnabled = true
        webSettings.domStorageEnabled = true //开启DOM缓存，关闭的话H5自身的一些操作是无效的

        webSettings.cacheMode = WebSettings.LOAD_DEFAULT
        webSettings.defaultTextEncodingName = "utf-8"
        webSettings.allowFileAccess = true
    }

}