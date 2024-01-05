package com.wjf.androidutils.entity

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2024/1/5 18:54
 *
 */

data class FragmentBean(
    /**
     * 跳转的Key
     */
    var jumpFlag: String = "",
    /**
     * 是否在首页显示
     */
    var isShow: Boolean = false
)
