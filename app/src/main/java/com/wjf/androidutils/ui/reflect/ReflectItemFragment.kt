package com.wjf.androidutils.ui.reflect

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.wjf.androidutils.base.MVVMBaseFragment
import com.wjf.androidutils.databinding.FragmentReflectItemBinding
import com.wjf.androidutils.ui.home.HomeViewModel
import com.wjf.androidutils.utils.LABEL

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/6 14:07
 *
 */

class ReflectItemFragment : MVVMBaseFragment<HomeViewModel, FragmentReflectItemBinding>() {

    companion object{
        fun newInstance(label : String) : ReflectItemFragment{
            val fragment = ReflectItemFragment()
            val bundle = Bundle()
            bundle.putString(LABEL,label)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun initViewModel() = ViewModelProviders.of(this).get(HomeViewModel::class.java)

    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentReflectItemBinding {
        return FragmentReflectItemBinding.inflate(inflater,container,false)
    }

    override fun initView() {

        binding.tvReflectItem.text = arguments?.getString(LABEL)
        binding.tvReflectItem.setBackgroundColor(Color.rgb((Math.random() * 255).toInt(),(Math.random() * 255).toInt(),(Math.random() * 255).toInt()))
    }
}