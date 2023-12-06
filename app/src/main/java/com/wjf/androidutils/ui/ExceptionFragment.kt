package com.wjf.androidutils.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.wjf.androidutils.base.MVVMBaseFragment
import com.wjf.androidutils.databinding.FragmentExceptionBinding
import com.wjf.androidutils.ui.home.HomeViewModel
import com.wjf.androidutils.utils.CoroutineUtils.launch
import com.wjf.androidutils.utils.ExceptionUtils
import com.wjf.androidutils.utils.singleClick
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

            launch({
                throw NullPointerException()

            },{

            })

            launch({
                throw IllegalArgumentException()

            },{
                ExceptionUtils.instance.getCashHandler().uncaughtException(Thread.currentThread(),it)

            })

        }

    }
}