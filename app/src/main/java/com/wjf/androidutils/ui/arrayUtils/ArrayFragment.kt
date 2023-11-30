package com.wjf.androidutils.ui.arrayUtils

import android.view.View
import android.widget.Button
import androidx.lifecycle.ViewModelProviders
import com.wjf.androidutils.R
import com.wjf.androidutils.base.MVVMBaseFragment
import com.wjf.androidutils.utils.ArrayUtils
import com.wjf.androidutils.utils.LogUtils

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/11/30 16:40
 *
 */

class ArrayFragment : MVVMBaseFragment<ArrayViewModel>(), View.OnClickListener {

    lateinit var btnSplit: Button
    override fun initViewModel() = ViewModelProviders.of(this).get(ArrayViewModel::class.java)

    override fun initContentViewID() = R.layout.fragment_array

    override fun initView() {
        btnSplit = mView.findViewById(R.id.btn_split)
        btnSplit.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        when(v){

            btnSplit -> {
                val originArr = byteArrayOf(1,2,3,4,5,6,7,8,9)
                val splitArr = ArrayUtils.splitByteArray(originArr,2)

                splitArr.forEach {
                    LogUtils.d("__splitArr",it.contentToString())
                }
            }
        }
    }

}