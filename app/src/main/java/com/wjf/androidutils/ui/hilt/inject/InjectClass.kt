package com.wjf.androidutils.ui.hilt.inject

import javax.inject.Inject

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/20 7:35
 *
 */

/**
 * 构造函数前加上@Inject注解，以告知Hilt如何提供该类的对象实例
 */
class InjectClass @Inject constructor(){
    private var name = "张三"
    private var age = "12"

    override fun toString(): String {
        return "姓名：$name 年龄：$age"
    }
}