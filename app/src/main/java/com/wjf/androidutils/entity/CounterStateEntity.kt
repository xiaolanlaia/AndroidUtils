package com.wjf.androidutils.entity

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import java.io.Serializable

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2024/3/7 16:55
 *
 */
/**
 * rememberSaveable将数据缓存在bundle中
 * 所以数据类需要实现可序列化接口
 */
data class CounterStateEntity(

    var count: Int = 0,
    var text: String = "text",
    var otherText: String = "otherText",
    var stateName: MutableState<String> = mutableStateOf("stateName"),
    var stateAge: MutableState<Int> = mutableStateOf(0),
    ) : Serializable

