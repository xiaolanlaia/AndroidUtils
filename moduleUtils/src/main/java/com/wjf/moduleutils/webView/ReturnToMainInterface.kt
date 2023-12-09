package com.wjf.moduleutils.webView

import android.webkit.JavascriptInterface

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/1/3 16:00
 */
class ReturnToMainInterface(val returnToMainCallback: ReturnToMainCallback) {

    /**
     * JS调用Android(Java)含参数的方法
     */
    @JavascriptInterface
    fun jsReturnToMain() {
        //Android代码逻辑
        returnToMainCallback.returnToMain()
    }
}