package com.wjf.androidutils.compose.ui.component.net

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
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.wjf.androidutils.R
import com.wjf.androidutils.compose.theme.modifier_240_120
import com.wjf.androidutils.compose.theme.textStyle_20_white
import com.wjf.androidutils.net.utils.Status
import com.wjf.moduleutils.LogUtils

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2024/1/7 14:31
 *
 */

const val NET_GET       = "get"
const val NET_POST      = "post"
const val NET_UPLOAD    = "upload"
const val NET_DOWNLOAD  = "download"
const val NET_HEADER    = "header"

val tvNetContent = ArrayList<String>().apply { 
    add(NET_GET)
    add(NET_POST)
    add(NET_UPLOAD)
    add(NET_DOWNLOAD)
    add(NET_HEADER)
}
@Composable
fun NetComponent(
    navHostController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
    vm: NetViewModel = viewModel(),
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    var tvFlowValue by rememberSaveable { mutableStateOf("") }

    LazyVerticalGrid(
        contentPadding = PaddingValues(20.dp),
        columns = GridCells.Fixed(4),
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        content = {

            items((tvNetContent.size)) { index ->
                Text(
                    text = tvNetContent[index],
                    style = textStyle_20_white,
                    modifier = modifier_240_120
                        .clickable {
                            tvFlowValue = ""
                            when (tvNetContent[index]) {

                                NET_GET -> {
                                    vm.getUsers().observe(lifecycleOwner, Observer {
                                        it?.let { resource ->
                                            when (resource.status) {
                                                Status.SUCCESS -> {
                                                    LogUtils.d("__getUsers-SUCCESS","1")
                                                }
                                                Status.ERROR -> {
                                                    LogUtils.d("__getUsers-ERROR","${it.message}")

                                                }
                                                Status.LOADING -> {
                                                    LogUtils.d("__getUsers-LOADING","1")
                                                }
                                            }
                                        }
                                    })
                                }
                                NET_POST -> {}
                                NET_UPLOAD -> {}
                                NET_DOWNLOAD -> {}
                                NET_HEADER -> {}


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
fun NetComponentPreview() {
    NetComponent()
}