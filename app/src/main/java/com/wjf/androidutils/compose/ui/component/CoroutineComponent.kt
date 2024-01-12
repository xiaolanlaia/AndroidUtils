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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.wjf.androidutils.R
import com.wjf.androidutils.compose.theme.textStyle_20_white
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2024/1/11 19:23
 *
 */
const val TV_GlobalScope   = "GlobalScope"
const val TV_runBlocking   = "runBlocking"
const val TV_launch        = "launch"
const val TV_async         = "async"
val tvCoroutineList = ArrayList<String>().apply {
    add(TV_GlobalScope)
    add(TV_runBlocking)
    add(TV_launch)
    add(TV_async)
}
@Composable
fun CoroutineComponent(
    navHostController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
) {
    var tvCoroutineValue by rememberSaveable {
        mutableStateOf("")
    }
    LazyVerticalGrid(
        contentPadding = PaddingValues(20.dp),
        columns = GridCells.Fixed(4),
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        content = {
            items(tvCoroutineList.size){ index ->
                Text(
                    text = tvCoroutineList[index],
                    style = TextStyle(fontSize = 40.sp, color = Color.White),
                    modifier = Modifier
                        .size(width = 240.dp, height = 120.dp)
                        .background(color = colorResource(id = R.color.half_transparent))
                        .clickable {
                            tvCoroutineValue = ""
                            when(tvCoroutineList[index]){
                                TV_GlobalScope -> {
                                    tvCoroutineValue = "$tvCoroutineValue -> 0"
                                    GlobalScope.launch {
                                        delay(1000)
                                        tvCoroutineValue = "$tvCoroutineValue -> 1"
                                    }
                                    tvCoroutineValue = "$tvCoroutineValue -> 2"
                                }
                                TV_runBlocking -> {
                                    tvCoroutineValue = "$tvCoroutineValue -> 0"
                                    runBlocking {
                                        delay(1000)
                                        tvCoroutineValue = "$tvCoroutineValue -> 1"
                                    }
                                    tvCoroutineValue = "$tvCoroutineValue -> 2"
                                }
                                TV_launch -> {
                                    tvCoroutineValue = "$tvCoroutineValue -> 0"
                                    val job: Job = CoroutineScope(Dispatchers.IO).launch {
                                        delay(1000)
                                        tvCoroutineValue = "$tvCoroutineValue -> 1"
                                    }
                                    tvCoroutineValue = "$tvCoroutineValue -> 2"


                                }
                                TV_async -> {
                                    CoroutineScope(Dispatchers.IO).launch {

                                        tvCoroutineValue = "$tvCoroutineValue -> 0"

                                        val deferred: Deferred<String> = async {
                                            delay(2000)
                                            " → deferred"
                                        }

                                        val deferred1: Deferred<String> = async {
                                            delay(2000)
                                            " → deferred1"
                                        }

                                        val deferred2: Deferred<String> = async {
                                            delay(2000)
                                            " → deferred2"
                                        }

                                        tvCoroutineValue = "$tvCoroutineValue -> 1"

                                        val result = deferred.await() + deferred1.await() + deferred2.await()
                                        tvCoroutineValue = "$tvCoroutineValue -> ${result}"
                                    }
                                }
                            }
                            tvCoroutineValue = "$tvCoroutineValue "
                        }
                        .semantics { contentDescription = tvCoroutineList[index] }
                        .wrapContentSize(Alignment.Center)
                )
            }

            //结果显示
            item(span = { GridItemSpan(maxLineSpan) }){

                Text(
                    text = tvCoroutineValue,
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
@Preview(showBackground = true, backgroundColor = 0x000000, widthDp = 1080, heightDp = 1920)
fun CoroutineComponentPreview() {
    CoroutineComponent()
}