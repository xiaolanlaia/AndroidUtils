package com.wjf.androidutils.compose.ui.component

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
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.wjf.androidutils.R
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

val textStyle = TextStyle(fontSize = 40.sp, color = Color.White, textAlign = TextAlign.Center)

@Composable
fun FlowComponent(
    navHostController : NavHostController,
    modifier: Modifier = Modifier
) {

    val tvModifier = Modifier
        .size(width = 240.dp, height = 120.dp)
        .background(color = colorResource(id = R.color.half_transparent))

    var tvFlowValue by rememberSaveable {
        mutableStateOf("")
    }
    val tvFlowContent = ArrayList<String>()

    var clickIndex = 0

    tvFlowContent.add(OPERATOR_flow)
    tvFlowContent.add(OPERATOR_asFlow)
    tvFlowContent.add(OPERATOR_flowOf)

    tvFlowContent.add(OPERATOR_process)

    tvFlowContent.add(OPERATOR_catch)

    tvFlowContent.add(OPERATOR_map)
    tvFlowContent.add(OPERATOR_transform)
    tvFlowContent.add(OPERATOR_take)
    tvFlowContent.add(OPERATOR_drop)
    tvFlowContent.add(OPERATOR_combine)
    tvFlowContent.add(OPERATOR_zip)
    tvFlowContent.add(OPERATOR_collectLatest)
    tvFlowContent.add(OPERATOR_buffer)
    tvFlowContent.add(OPERATOR_conflate)
    tvFlowContent.add(OPERATOR_flatMapConcat)
    tvFlowContent.add(OPERATOR_flatMapMerge)
    tvFlowContent.add(OPERATOR_flatMapLatest)
    tvFlowContent.add(OPERATOR_flowOn)
    tvFlowContent.add(OPERATOR_filter)
    tvFlowContent.add(OPERATOR_takeWhile)


    LazyVerticalGrid(
        contentPadding = PaddingValues(20.dp),
        columns = GridCells.Fixed(4),
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        content = {

            //创建流
            item(span = { GridItemSpan(maxLineSpan) }){ TitleText("创建流") }

            items((tvFlowContent.indexOf(OPERATOR_flowOf) - tvFlowContent.indexOf(OPERATOR_flow)) + 1) {index ->
                Text(
                    text = tvFlowContent[index + tvFlowContent.indexOf(OPERATOR_flow)],
                    style = textStyle,
                    modifier = tvModifier
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
                    style = textStyle,
                    modifier = tvModifier
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
                    style = textStyle,
                    modifier = tvModifier
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
                    style = textStyle,
                    modifier = tvModifier
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
                    style = textStyle,
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

@Composable
fun <T: Any> rememberMutableStateListOf(vararg elements: T): SnapshotStateList<T> {
    return rememberSaveable(
        saver = listSaver(
            save = { stateList ->
                if (stateList.isNotEmpty()) {
                    val first = stateList.first()
                    if (!canBeSaved(first)) {
                        throw IllegalStateException("${first::class} cannot be saved. By default only types which can be stored in the Bundle class can be saved.")
                    }
                }
                stateList.toList()
            },
            restore = { it.toMutableStateList() }
        )
    ) {
        elements.toList().toMutableStateList()
    }
}

@Composable
fun TitleText(title: String){
    Text(
        text = title,
        style = textStyle,
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .background(color = colorResource(id = R.color.half_transparent))
            .wrapContentSize(Alignment.Center)
    )
}