package com.wjf.androidutils.entity

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2024/1/7 15:42
 *
 */

data class BaseEntity<T>(
    var code: String? = "",
    var message: String? = "",
    var success: Boolean? = false,
    var data: T? = null
)