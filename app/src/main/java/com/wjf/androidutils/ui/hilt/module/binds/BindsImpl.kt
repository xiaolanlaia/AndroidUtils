package com.wjf.androidutils.ui.hilt.module.binds

import javax.inject.Inject

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/20 9:09
 *
 */

class BindsImpl @Inject constructor() : IBinds {

    override fun getName(): String {
        return this.javaClass.simpleName
    }
}