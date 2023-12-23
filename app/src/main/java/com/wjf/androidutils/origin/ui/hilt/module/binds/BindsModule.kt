package com.wjf.androidutils.origin.ui.hilt.module.binds


import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/20 9:12
 *
 */

/**
 * ActivityComponent:Hilt 将该依赖项注入 Activity 中，WorkModule所有依赖项可以在所有Activity中使用
 */
@Module
@InstallIn(ActivityComponent::class)
abstract class BindsModule {

    /**
     * 返回类型：告知 Hilt 该函数提供哪个接口的实例
     * 函数参数：告知 Hilt 要提供哪种实现
     */
    @Binds
    abstract fun bindImpl(bindsImpl: BindsImpl) : IBinds
}