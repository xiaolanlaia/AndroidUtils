package com.wjf.androidutils.ui.hilt.module.providers

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/20 9:48
 *
 */
@Module
@InstallIn(ActivityComponent::class)
object ProvidesModule {

    @Provides
    fun provideImpl() : IProviders{
        return ProvidersImpl()
    }

}