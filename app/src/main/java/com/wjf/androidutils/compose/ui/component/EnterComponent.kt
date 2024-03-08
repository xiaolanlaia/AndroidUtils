package com.wjf.androidutils.compose.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import com.wjf.androidutils.compose.nav.RouteConfig

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/31 8:20
 *
 */

val tvContentList = ArrayList<String>().apply {
    add(RouteConfig.ROUTE_WIDGET)
    add(RouteConfig.ROUTE_FLOW)
    add(RouteConfig.ROUTE_FLOW_HOT)
    add(RouteConfig.ROUTE_NET)
    add(RouteConfig.ROUTE_COROUTINE)
    add(RouteConfig.ROUTE_NOTIFICATION)
    add(RouteConfig.ROUTE_REMEMBER)
}

@Composable
fun EnterComponent(
    navHostController : NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        contentPadding = PaddingValues(20.dp),
        columns = GridCells.Fixed(4),
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        content = {
            items(tvContentList.size){ index ->
                Text(
                    text = tvContentList[index],
                    style = TextStyle(fontSize = 40.sp, color = Color.White),
                    modifier = Modifier
                        .size(width = 240.dp, height = 120.dp)
                        .background(color = colorResource(id = R.color.half_transparent))
                        .clickable { navHostController.navigate(tvContentList[index]) }
                        .semantics { contentDescription = tvContentList[index] }
                        .wrapContentSize(Alignment.Center)
                )
            }
        }
    )

}

@Composable
@Preview(showBackground = true, backgroundColor = 0x000000, widthDp = 1080, heightDp = 1920)
fun EnterComponentPreview() {
    EnterComponent(rememberNavController())

}