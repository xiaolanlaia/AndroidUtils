package com.wjf.androidutils.compose.utils

import android.os.SystemClock
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.semantics.Role

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/27 9:01
 *
 */


private var mHits = LongArray(3)
fun Modifier.continuousClick(
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    count: Int = 3,
    duration: Long = 2000,
    onClick: () -> Unit
) = composed(
    inspectorInfo = debugInspectorInfo {
        name = "clickable"
        properties["enabled"] = enabled
        properties["onClickLabel"] = onClickLabel
        properties["role"] = role
        properties["onClick"] = onClick
    }
) {
    Modifier.clickable(
        enabled = enabled,
        onClickLabel = onClickLabel,
        onClick = {
            //每次点击时，数组向前移动一位
            System.arraycopy(mHits, 1, mHits, 0, mHits.size - 1)
            //为数组最后一位赋值
            mHits[mHits.size - 1] = SystemClock.uptimeMillis()
            if (mHits[0] >= SystemClock.uptimeMillis() - duration) {
                mHits = LongArray(count) //重新初始化数组
                onClick()
            }
        },
        role = role,
        indication = LocalIndication.current,
        interactionSource = remember { MutableInteractionSource() }
    )
}