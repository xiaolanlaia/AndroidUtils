package com.wjf.androidutils.ui.hilt

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.wjf.androidutils.base.MVVMBaseFragment
import com.wjf.androidutils.databinding.FragmentHiltBinding
import com.wjf.androidutils.ui.hilt.inject.InjectClass
import com.wjf.androidutils.ui.hilt.inject.InjectClass2
import com.wjf.androidutils.ui.home.HomeViewModel
import com.wjf.moduleutils.ToastUtils
import com.wjf.moduleutils.singleClick
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/20 7:27
 *
 */
/**
 * 1、告诉Hilt这些组件可以接收依赖注入
 * 2、使用 @AndroidEntryPoint 为某个 Android 类添加注解，
 * 则还必须为依赖于该类的 Android 类添加注解。
 * 例如，如果您为某个 fragment 添加注解，则还必须为使用该 fragment 的所有 activity 添加注解
 * 3、抽象类不需要该注解
 */
@AndroidEntryPoint
class HiltFragment : MVVMBaseFragment<HomeViewModel, FragmentHiltBinding>() {

    @Inject
    lateinit var injectClass: InjectClass

    @Inject
    lateinit var injectClass2: InjectClass2

    override fun initViewModel() = ViewModelProviders.of(this).get(HomeViewModel::class.java)

    override fun initViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentHiltBinding.inflate(inflater, container, false)

    override fun initView() {

        binding.btnInject.singleClick {
            ToastUtils.instance.show(injectClass.toString())
        }
        binding.btnInject2.singleClick {
            ToastUtils.instance.show(injectClass2.toString())
        }

    }
}