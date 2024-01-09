package com.wjf.androidutils.compose.widget

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.wjf.androidutils.R
import com.wjf.androidutils.compose.nav.RouteConfig
import com.wjf.androidutils.compose.theme.textStyle_52_222222_bold
import com.wjf.androidutils.compose.utils.TAG_COMMON_TITLE_BACK
import com.wjf.androidutils.compose.utils.continuousClick

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/20 19:24
 *
 */

@Composable
fun CommonTitle(
    navHostController: NavHostController,
    title: String = "",
    pageRoute: String = RouteConfig.ROUTE_ENTER,
    modifier: Modifier = Modifier,
) {
    val activity = LocalContext.current as Activity

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(198.dp)
    ) {
        Text(
            text = title,
            style = textStyle_52_222222_bold,
            modifier = Modifier
                .fillMaxSize()
                .continuousClick {

                }
                .wrapContentSize(Alignment.Center)
        )
        Box(
            modifier = Modifier
                .size(height = 198.dp, width = 198.dp)
                .testTag(TAG_COMMON_TITLE_BACK)
                .clickable {
                    if (pageRoute == RouteConfig.ROUTE_ENTER){
                        activity.finish()
                    }else{
                        navHostController.popBackStack()
                    }
                }
        ) {

            Image(
                painter = painterResource(id = R.mipmap.title_back),
                contentDescription = "",
                modifier = Modifier
                    .size(width = 69.dp, height = 69.dp)
                    .align(Alignment.Center)
            )
        }
    }
}

@Composable
@Preview(widthDp = 1080, heightDp = 198)
fun CommonTitlePreview() {
    CommonTitle(rememberNavController(), "测试标题")
}
