package com.wjf.androidutils.compose.ui.component.flow

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.wjf.androidutils.R
import com.wjf.androidutils.compose.theme.modifier_240_120
import com.wjf.androidutils.compose.theme.textStyle_20_white
import com.wjf.androidutils.compose.utils.*
import com.wjf.moduleutils.LogUtils

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2024/1/2 19:12
 *
 */

const val OPERATOR_flow = "flow"
const val OPERATOR_asFlow = "asFlow"
const val OPERATOR_flowOf = "flowOf"
const val OPERATOR_process = "process"
const val OPERATOR_catch = "catch"
const val OPERATOR_map = "map"
const val OPERATOR_transform = "transform"
const val OPERATOR_take = "take"
const val OPERATOR_combine = "combine"
const val OPERATOR_zip = "zip"
const val OPERATOR_collectLatest = "collectLatest"
const val OPERATOR_buffer = "buffer"
const val OPERATOR_conflate = "conflate"
const val OPERATOR_flatMapConcat = "flatMapConcat"
const val OPERATOR_flatMapMerge = "flatMapMerge"
const val OPERATOR_flatMapLatest = "flatMapLatest"
const val OPERATOR_flowOn = "flowOn"
const val OPERATOR_filter = "filter"
const val OPERATOR_takeWhile = "takeWhile"
const val OPERATOR_drop = "drop"


val tvFlowContent = ArrayList<String>().apply {
    add(OPERATOR_flow)
    add(OPERATOR_asFlow)
    add(OPERATOR_flowOf)

    add(OPERATOR_process)

    add(OPERATOR_catch)

    add(OPERATOR_map)
    add(OPERATOR_transform)
    add(OPERATOR_take)
    add(OPERATOR_drop)
    add(OPERATOR_combine)
    add(OPERATOR_zip)
    add(OPERATOR_collectLatest)
    add(OPERATOR_buffer)
    add(OPERATOR_conflate)
    add(OPERATOR_flatMapConcat)
    add(OPERATOR_flatMapMerge)
    add(OPERATOR_flatMapLatest)
    add(OPERATOR_flowOn)
    add(OPERATOR_filter)
    add(OPERATOR_takeWhile)
}

@Composable
fun FlowComponent(
    navHostController : NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {



    var tvFlowValue by rememberSaveable {
        mutableStateOf("")
    }

    var clickIndex by rememberSaveable {
        mutableStateOf(0)
    }


    LazyVerticalGrid(
        contentPadding = PaddingValues(20.dp),
        columns = GridCells.Fixed(4),
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        content = {

            //创建流
            item(span = { GridItemSpan(maxLineSpan) }){ TitleText("创建流") }

            items((tvFlowContent.indexOf(OPERATOR_flowOf) - tvFlowContent.indexOf(OPERATOR_flow)) + 1) { index ->
                Text(
                    text = tvFlowContent[index + tvFlowContent.indexOf(OPERATOR_flow)],
                    style = textStyle_20_white,
                    modifier = modifier_240_120
                        .clickable {
                            clickIndex = index + tvFlowContent.indexOf(OPERATOR_flow)

                            tvFlowValue = ""
                            when (tvFlowContent[clickIndex]) {

                                OPERATOR_flow -> {
                                    FlowUtils.coldFlow {
                                        LogUtils.d("__FlowUtils-coldFlow", "$it")
                                        tvFlowValue = "${tvFlowValue}:$it"
                                    }
                                }

                                OPERATOR_asFlow -> {
                                    FlowUtils.asFlow {
                                        tvFlowValue = "${tvFlowValue}:$it"
                                    }
                                }

                                OPERATOR_flowOf -> {
                                    val job = FlowUtils.coldFlowOf {
                                        tvFlowValue = "${tvFlowValue}:$it"
                                    }
                                }

                            }
                        }
                        .wrapContentSize(Alignment.Center)
                )
            }


            //流程操作符
            item(span = { GridItemSpan(maxLineSpan) }){ TitleText("流程操作符") }

            items(1) {index ->
                Text(
                    text = tvFlowContent[index + tvFlowContent.indexOf(OPERATOR_process)],
                    style = textStyle_20_white,
                    modifier = modifier_240_120
                        .clickable {
                            clickIndex = index + tvFlowContent.indexOf(OPERATOR_process)
                            tvFlowValue = ""
                            when (tvFlowContent[clickIndex]) {

                                OPERATOR_process -> {
                                    FlowUtils.processOperator {
                                        LogUtils.d("__exactlyIndex", tvFlowContent[clickIndex])
                                        tvFlowValue = "${tvFlowValue}:$it"
                                    }
                                }

                            }
                        }
                        .wrapContentSize(Alignment.Center)
                )
            }

            //异常操作符
            item(span = { GridItemSpan(maxLineSpan) }){ TitleText("异常操作符") }

            items(1){ index ->

                Text(
                    text = tvFlowContent[index + tvFlowContent.indexOf(OPERATOR_catch)],
                    style = textStyle_20_white,
                    modifier = modifier_240_120
                        .clickable {
                            clickIndex = index + tvFlowContent.indexOf(OPERATOR_catch)
                            tvFlowValue = ""
                            when (tvFlowContent[clickIndex]) {
                                OPERATOR_catch -> {
                                    FlowUtils.catchOperator {
                                        tvFlowValue = "${tvFlowValue}:$it"
                                    }
                                }
                            }
                        }
                        .wrapContentSize(Alignment.Center)
                )

            }

            //转换操作符
            item(span = { GridItemSpan(maxLineSpan) }){ TitleText("转换操作符") }

            items((tvFlowContent.indexOf(OPERATOR_takeWhile) - tvFlowContent.indexOf(OPERATOR_map)) + 1){ index ->
                Text(
                    text = tvFlowContent[index + tvFlowContent.indexOf(OPERATOR_map)],
                    style = textStyle_20_white,
                    modifier = modifier_240_120
                        .clickable {
                            clickIndex = index + tvFlowContent.indexOf(OPERATOR_map)
                            tvFlowValue = ""

                            when (tvFlowContent[clickIndex]) {
                                OPERATOR_map -> {
                                    FlowUtils.mapOperator {
                                        tvFlowValue = "${tvFlowValue}:$it"
                                    }
                                }

                                OPERATOR_transform -> {
                                    FlowUtils.transformOperator {
                                        tvFlowValue = "${tvFlowValue}:$it"
                                    }
                                }

                                OPERATOR_take -> {
                                    FlowUtils.takeOperator {
                                        tvFlowValue = "${tvFlowValue}:$it"
                                    }
                                }

                                OPERATOR_drop -> {
                                    FlowUtils.dropOperator {
                                        tvFlowValue = "${tvFlowValue}:$it"
                                    }
                                }

                                OPERATOR_combine -> {
                                    FlowUtils.combineOperator {
                                        tvFlowValue = "${tvFlowValue}:$it"
                                    }
                                }

                                OPERATOR_zip -> {
                                    FlowUtils.zipOperator {
                                        tvFlowValue = "${tvFlowValue}:$it"
                                    }
                                }

                                OPERATOR_collectLatest -> {
                                    FlowUtils.collectLatestOperator {
                                        tvFlowValue = "${tvFlowValue}:$it"
                                    }
                                }

                                OPERATOR_buffer -> {
                                    FlowUtils.bufferOperator {
                                        tvFlowValue = "${tvFlowValue}:$it"
                                    }
                                }

                                OPERATOR_conflate -> {
                                    //conflate
                                    tvFlowValue = "conflate"

                                }

                                OPERATOR_flatMapConcat -> {
                                    FlowUtils.flatMapConcatOperator {
                                        tvFlowValue = "${tvFlowValue}:$it"
                                    }
                                }

                                OPERATOR_flatMapMerge -> {
                                    FlowUtils.flatMapMergeOperator {
                                        tvFlowValue = "${tvFlowValue}:$it"
                                    }
                                }

                                OPERATOR_flatMapLatest -> {
                                    FlowUtils.flatMapLatestOperator {
                                        tvFlowValue = "${tvFlowValue}:$it"
                                    }
                                }

                                OPERATOR_flowOn -> {
                                    FlowUtils.flowOnOperator {
                                        tvFlowValue = "${tvFlowValue}:$it"
                                    }
                                }

                                OPERATOR_filter -> {
                                    FlowUtils.filterOperator {
                                        tvFlowValue = "${tvFlowValue}:$it"
                                    }
                                }

                                OPERATOR_takeWhile -> {
                                    FlowUtils.takeWhileOperator {
                                        tvFlowValue = "${tvFlowValue}:$it"
                                    }
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
        })



}

@Composable
@Preview(showBackground = true, backgroundColor = 0x000000, widthDp = 1080, heightDp = 1920)
fun FlowComponentPreview(){
    FlowComponent(
        rememberNavController(),
        Modifier
    )
}

