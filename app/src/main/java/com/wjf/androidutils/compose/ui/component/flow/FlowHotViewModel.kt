package com.wjf.androidutils.compose.ui.component.flow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2024/1/3 19:16
 *
 */

class FlowHotViewModel : ViewModel() {
    private val _state: MutableStateFlow<Int> = MutableStateFlow(0)
    val state: StateFlow<Int> get() = _state

    fun download() {
        for (state in 0..5) {
            viewModelScope.launch(Dispatchers.IO) {
                delay(200L * state)
                _state.value = state
            }
        }
    }

    private val _sharedState: MutableSharedFlow<Int> = MutableSharedFlow(2)
    val sharedState: SharedFlow<Int> get() = _sharedState
    fun haredDownload() {
        for (state in 0..5) {
            viewModelScope.launch(Dispatchers.IO) {
                delay(100L * state)
                _sharedState.emit(state)
            }
        }
    }
}