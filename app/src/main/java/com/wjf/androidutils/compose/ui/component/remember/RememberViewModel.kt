package com.wjf.androidutils.compose.ui.component.remember

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.wjf.androidutils.entity.CounterStateEntity
import com.wjf.moduleutils.LogUtils
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2024/3/7 16:50
 *
 */

class RememberViewModel: ViewModel() {
    var state = MutableStateFlow(CounterStateEntity())
    //对象中的整型属性，需要单独取出进行监听
    var stateCount = MutableStateFlow(state.value.count)

    fun onIncrement(){


        stateCount.value ++
        state.value = state.value.copy(count = stateCount.value)

        LogUtils.d("__ViewModel-onIncrement","${state.value.count}")

    }

    fun onTextChange(text: String){
        //改变对象的部分属性，使用copy创建新的对象
        state.value = state.value.copy(text = text)
    }

    fun onOtherTextChange(otherText: String){
        //改变对象的部分属性，使用copy创建新的对象
        state.value = state.value.copy(otherText = otherText)
    }

    fun onStateName(stateText: String){
        //改变对象的部分属性，使用copy创建新的对象
        state.value.stateName.value = stateText
    }

    fun onStateAge(){
        //改变对象的部分属性，使用copy创建新的对象
        state.value.stateAge.value = ++state.value.stateAge.value
    }

}