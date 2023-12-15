package com.wjf.androidutils.ui.dialog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.wjf.androidutils.base.MVVMBaseFragment
import com.wjf.androidutils.databinding.FragmentDialogBinding
import com.wjf.androidutils.ui.home.HomeViewModel

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/15 16:57
 *
 */

class DialogFragment : MVVMBaseFragment<HomeViewModel, FragmentDialogBinding>() {

    override fun initViewModel() = ViewModelProviders.of(this).get(HomeViewModel::class.java)

    override fun initViewBinding(inflater: LayoutInflater, container: ViewGroup?) = FragmentDialogBinding.inflate(inflater,container,false)

    override fun initView() {

    }
}