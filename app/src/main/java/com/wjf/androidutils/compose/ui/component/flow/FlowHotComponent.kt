package com.wjf.androidutils.compose.ui.component.flow

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.wjf.androidutils.R
import com.wjf.androidutils.compose.theme.textStyle_20_white
import com.wjf.moduleutils.CoroutineUtils
import kotlinx.coroutines.delay
import java.lang.NullPointerException

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2024/1/3 17:50
 *
 */
const val FLOW_STATE = "FlowState"
const val FLOW_STATE_CRASH = "FlowStateCrash"
const val FLOW_SHARED = "FlowShared"
@Composable
fun FlowHotComponent(
    navHostController : NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
    vm: FlowHotViewModel = viewModel()
    ) {

    val tvContent = ArrayList<String>().apply {
        add(FLOW_STATE)
        add(FLOW_SHARED)
    }

    var tvFlowValue by rememberSaveable {
        mutableStateOf("")
    }


    LazyVerticalGrid(
        contentPadding = PaddingValues(20.dp),
        columns = GridCells.Fixed(4),
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        content = {

            items(tvContent.size) { index ->
                Text(
                    text = tvContent[index],
                    style = TextStyle(fontSize = 40.sp, color = Color.White),
                    modifier = Modifier
                        .size(width = 240.dp, height = 120.dp)
                        .background(color = colorResource(id = R.color.half_transparent))
                        .clickable {
                            when (tvContent[index]) {

                                FLOW_STATE -> {
                                    CoroutineUtils.instance.launch {
                                        vm.state.collect {
                                            tvFlowValue = "$tvFlowValue -> $it"
                                        }
                                    }
                                    vm.download()
                                }

                                FLOW_STATE_CRASH -> {
                                    CoroutineUtils.instance.launch {
                                        try {
                                            vm.state.collect {
                                                if (it == 3) {
                                                    throw NullPointerException("终止数据收集")
                                                }
                                                tvFlowValue = "$tvFlowValue -> $it"
                                            }
                                        } catch (e: Exception) {
                                            tvFlowValue = "$tvFlowValue -> ${e.message}"
                                        }
                                    }
                                    vm.download()
                                }

                                FLOW_SHARED -> {
                                    CoroutineUtils.instance.launch {

                                        CoroutineUtils.instance.launch {
                                            vm.sharedState.collect {
                                                tvFlowValue = "$tvFlowValue -> $it"
                                            }
                                        }

                                        CoroutineUtils.instance.launch {
                                            delay(3000)
                                            vm.sharedState.collect {
                                                tvFlowValue = "$tvFlowValue -> $it"
                                            }
                                        }
                                    }
                                    vm.haredDownload()
                                }
                            }
                        }
                        .wrapContentSize(Alignment.Center)

                )
            }


            //结果显示
            item(span = { GridItemSpan(maxLineSpan) }){

                Text(
                    text = tvFlowValue,
                    style = textStyle_20_white,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(500.dp)
                        .background(color = colorResource(id = R.color.half_transparent))
                        .wrapContentSize(Alignment.Center)
                )
            }
        }
    )
}

@Composable
@Preview(showBackground = true, backgroundColor = 0x000000, widthDp = 1920, heightDp = 1080)
fun FlowHotComponentPreview() {
    FlowHotComponent()
}