package com.wjf.androidutils.compose.ui.component.remember

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.wjf.androidutils.entity.CounterStateEntity

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2024/3/7 9:05
 *
 */

@Composable
fun RememberComponent(
    navHostController : NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
    viewModel: RememberViewModel = viewModel(),

) {
    val state by viewModel.state.collectAsState()
    val stateCount by viewModel.stateCount.collectAsState()


    //当组合不得不持有状态时，需要加上remember，避免影响性能
    //更好的做法是使用参数传入
    val list = rememberSaveable { listOf("a", "b", "c") }

    Column(
        modifier = modifier
    ) {

        Counter(
            state = state,
            count = stateCount,
            onIncrement = {

                viewModel.onIncrement()
            },
            onTextChange = {
                viewModel.onTextChange(it)
            },
            onOtherTextChange = {
                viewModel.onOtherTextChange(it)
            },
        )

        StateCounter(
            state = state,
            count = state.stateAge.value,
            onIncrement = {

                viewModel.onStateAge()
            },
            onStateTextChange = {
                viewModel.onStateName(it)
            },
        )

    }
}

@Composable
fun Counter(
    state: CounterStateEntity,
    count: Int,
    onIncrement: () -> Unit,
    onTextChange: (String) -> Unit,
    onOtherTextChange: (String) -> Unit,
) {
    Column {
        Button(
            onClick = {
                onIncrement.invoke()
            }
        ) {
            Text("count ${count}")
        }
        TextField(
            value = state.text,
            onValueChange = {
                onTextChange.invoke(it)
            }
        )
        OtherCounter(
            text = state.otherText,
            onTextChange = onOtherTextChange,
        )
    }
}

@Composable
fun StateCounter(
    state: CounterStateEntity,
    count: Int,
    onIncrement: () -> Unit,
    onStateTextChange: (String) -> Unit,
) {
    Column {
        Button(
            onClick = {
                onIncrement.invoke()
            }
        ) {
            Text("count ${count}")
        }
        TextField(
            value = state.stateName.value,
            onValueChange = {
                onStateTextChange.invoke(it)
            }
        )
    }
}

@Composable
fun OtherCounter(
    text: String,
    onTextChange: (String) -> Unit,
) {
    Column {
        Text(text)
        TextField(
            value = text,
            onValueChange = {
                onTextChange.invoke(it)
            }
        )
    }
}

