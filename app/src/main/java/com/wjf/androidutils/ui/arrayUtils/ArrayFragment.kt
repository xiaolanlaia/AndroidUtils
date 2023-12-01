package com.wjf.androidutils.ui.arrayUtils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.wjf.androidutils.base.MVVMBaseFragment
import com.wjf.androidutils.databinding.FragmentArrayBinding
import com.wjf.androidutils.utils.ArrayUtils
import com.wjf.androidutils.utils.LogUtils

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/11/30 16:40
 *
 */

class ArrayFragment : MVVMBaseFragment<ArrayViewModel,FragmentArrayBinding>(), View.OnClickListener {

    override fun initViewModel() = ViewModelProviders.of(this).get(ArrayViewModel::class.java)

    override fun initViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentArrayBinding {
        return FragmentArrayBinding.inflate(inflater,container,false)
    }


    override fun initView() {
        binding.btnSplit.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        when(v){

            binding.btnSplit -> {
                val originArr = byteArrayOf(1,2,3,4,5,6,7,8,9)
                val splitArr = ArrayUtils.splitByteArray(originArr,2)

                splitArr.forEach {
                    LogUtils.d("__splitArr",it.contentToString())
                }
            }
        }
    }

}