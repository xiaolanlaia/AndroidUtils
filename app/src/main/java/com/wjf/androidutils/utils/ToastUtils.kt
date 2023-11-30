package com.wjf.androidutils.utils

import android.os.Build
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.wjf.androidutils.MyApplication


object ToastUtils {
    var mToast: Toast? = null

    fun setToastTextSize() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R){
            mToast = Toast.makeText(MyApplication.instance, null, Toast.LENGTH_SHORT)
            val linearLayout = mToast!!.view as LinearLayout
            val messageTextView = linearLayout.getChildAt(0) as TextView
            messageTextView.textSize = 30f
            messageTextView.gravity = Gravity.CENTER
        }

    }

    /**
     * 传入文字
     */
    fun show(text: String) {
        if (mToast == null){
            mToast = Toast.makeText(MyApplication.instance, null, Toast.LENGTH_SHORT)
        }
        mToast?.setText(text)
        mToast?.show()
    }

    /**
     * 传入文字
     * Android 11，R，API30 及以上 setGravity 无效
     */
    fun show(text: String, gravity: Int) {
        if (mToast == null){
            mToast = Toast.makeText(MyApplication.instance, null, Toast.LENGTH_SHORT)
        }
        mToast?.setGravity(gravity,0,0)
        mToast?.setText(text)
        mToast?.show()
    }



    /**
     * 传入文字
     */
    fun showLong(text: String) {
        if (mToast == null){
            mToast = Toast.makeText(MyApplication.instance, null, Toast.LENGTH_LONG)
        }
        mToast?.duration = Toast.LENGTH_LONG
        mToast?.setText(text)
        mToast?.show()
    }


    /**
     * 传入文字
     * Android 11，R，API30 及以上 setGravity 无效
     */
    fun showLong(text: String, gravity: Int) {
        if (mToast == null){
            mToast = Toast.makeText(MyApplication.instance, null, Toast.LENGTH_LONG)
        }
        mToast?.setGravity(gravity,0,0)
        mToast?.duration = Toast.LENGTH_LONG
        mToast?.setText(text)
        mToast?.show()
    }

}