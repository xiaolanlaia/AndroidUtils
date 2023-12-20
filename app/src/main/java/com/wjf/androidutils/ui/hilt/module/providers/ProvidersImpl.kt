package com.wjf.androidutils.ui.hilt.module.providers

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/20 9:57
 *
 */

class ProvidersImpl : IProviders {
    override fun getName(): String {
        return this.javaClass.simpleName
    }
}