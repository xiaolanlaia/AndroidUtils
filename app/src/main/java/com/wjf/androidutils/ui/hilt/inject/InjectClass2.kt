package com.wjf.androidutils.ui.hilt.inject

import javax.inject.Inject

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/20 8:31
 *
 */

/**
 * 构造函数有参数，该参数默认也是@Inject标记注入
 */
class InjectClass2 @Inject constructor(private val injectClass: InjectClass) {

    var str = ""
    override fun toString(): String {
        return "${str} - ${injectClass.toString()}"
    }

    /**
     * 注解方法，该方法会在对象被注入后调用
     */
    @Inject
    fun appendStr(){
        str = "str"
    }
}