package com.wjf.androidutils.ui.toast

import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import com.wjf.androidutils.R
import com.wjf.androidutils.base.MVVMBaseFragment
import com.wjf.androidutils.utils.ToastUtils
import com.wjf.androidutils.utils.singleClick

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/11/30 15:09
 *
 */

class ToastFragment : MVVMBaseFragment<ToastViewModel>() {

    override fun initViewModel() = ViewModelProviders.of(this).get(ToastViewModel::class.java)

    override fun initContentViewID() = R.layout.fragment_toast

    override fun initView() {
        val tvToast = mView.findViewById<TextView>(R.id.tv_toast)
        tvToast.singleClick {
            ToastUtils.show("测试字体大小")
        }
    }

}