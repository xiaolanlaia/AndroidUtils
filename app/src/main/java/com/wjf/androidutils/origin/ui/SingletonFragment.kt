package com.wjf.androidutils.origin.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.wjf.androidutils.databinding.FragmentSingletonBinding
import com.wjf.androidutils.origin.base.MVVMBaseFragment
import com.wjf.androidutils.origin.ui.home.HomeViewModel
import com.wjf.moduledesignpattern.createType.singleton.DoubleCheckSingleton
import com.wjf.moduledesignpattern.createType.singleton.HungrySingleton
import com.wjf.moduledesignpattern.createType.singleton.LazySingleton
import com.wjf.moduleutils.LogUtils
import com.wjf.moduleutils.singleClick

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2024/1/24 9:49
 *
 */

class SingletonFragment: MVVMBaseFragment<HomeViewModel,FragmentSingletonBinding>() {
    override fun initViewModel() = ViewModelProviders.of(this).get(HomeViewModel::class.java)

    override fun initViewBinding(inflater: LayoutInflater, container: ViewGroup?)
    = FragmentSingletonBinding.inflate(inflater,container,false)

    override fun initClick() {

        binding.tvHungary.singleClick {

            val hungaryClass = HungrySingleton::class.java
            val hungaryConstructor = hungaryClass.getDeclaredConstructor()
            hungaryConstructor.isAccessible = true
            val hungrySingleton = hungaryConstructor.newInstance()

            val mHungrySingleton = HungrySingleton.getInstance()

            LogUtils.d("__damage-hungary","${mHungrySingleton == hungrySingleton}")
        }

        binding.tvLazy.singleClick {

            val lazyClass = LazySingleton::class.java
            val lazyConstructor = lazyClass.getDeclaredConstructor()
            lazyConstructor.isAccessible = true

            val lazySingleton = lazyConstructor.newInstance()
            val mLazySingleton = LazySingleton.getInstance()

            LogUtils.d("__damage-lazy","${mLazySingleton == lazySingleton}")
        }

        binding.tvDoubleCheck.singleClick {

            val doubleCheckClass = DoubleCheckSingleton::class.java
            val doubleCheckConstructor = doubleCheckClass.getDeclaredConstructor()
            doubleCheckConstructor.isAccessible = true
            val doubleCheckSingleton = doubleCheckConstructor.newInstance()

            val mDoubleCheckSingleton = DoubleCheckSingleton.getInstance()

            LogUtils.d("__damage-doubleCheck","${mDoubleCheckSingleton == doubleCheckSingleton}")
        }

    }

}