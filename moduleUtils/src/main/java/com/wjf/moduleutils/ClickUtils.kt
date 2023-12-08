package com.wjf.moduleutils

import android.os.SystemClock
import android.view.View

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/11/29 10:12
 *
 */


inline fun View.singleClick(delayMillis: Long = 1000, crossinline onClick: (View) -> Unit) {
    this.setOnClickListener {
        this.isClickable = false
        onClick(this)
        this.postDelayed({
            this.isClickable = true
        }, delayMillis)
    }
}

/**
 * 连续点击多次
 */
private var mHits = LongArray(3)
fun View.continuousClick(count: Int = 3, duration: Long = 2000, result:() -> Unit) {
    this.setOnClickListener {
        //每次点击时，数组向前移动一位
        System.arraycopy(mHits, 1, mHits, 0, mHits.size - 1)
        //为数组最后一位赋值
        mHits[mHits.size - 1] = SystemClock.uptimeMillis()
        if (mHits[0] >= SystemClock.uptimeMillis() - duration) {
            mHits = LongArray(count) //重新初始化数组
            result()
        }
    }
}