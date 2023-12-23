package com.wjf.androidutils.origin.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.wjf.androidutils.origin.base.MVVMBaseFragment
import com.wjf.androidutils.databinding.FragmentExceptionBinding
import com.wjf.androidutils.origin.ui.home.HomeViewModel
import com.wjf.moduleutils.CoroutineUtils
import com.wjf.moduleutils.ExceptionUtils
import com.wjf.moduleutils.singleClick
import java.lang.IllegalArgumentException

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/6 7:50
 *
 */

class ExceptionFragment : MVVMBaseFragment<HomeViewModel, FragmentExceptionBinding>() {
    override fun initViewModel() = ViewModelProviders.of(this).get(HomeViewModel::class.java)

    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentExceptionBinding {
        return FragmentExceptionBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        binding.btnUncaught.singleClick {

            CoroutineUtils.instance.launch({
                throw NullPointerException()

            },{

            })

            CoroutineUtils.instance.launch({
                throw IllegalArgumentException()

            },{
                ExceptionUtils.instance.getCashHandler().uncaughtException(Thread.currentThread(),it)

            })

        }

    }
}