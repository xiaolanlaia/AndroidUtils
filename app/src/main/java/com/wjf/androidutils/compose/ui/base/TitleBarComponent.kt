package com.wjf.androidutils.compose.ui.base

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.wjf.androidutils.R
import com.wjf.androidutils.compose.nav.RouteConfig
import com.wjf.androidutils.compose.ui.component.EnterComponent
import com.wjf.androidutils.compose.ui.component.flow.FlowComponent
import com.wjf.androidutils.compose.ui.component.flow.FlowHotComponent
import com.wjf.androidutils.compose.ui.component.WidgetComponent
import com.wjf.androidutils.compose.widget.CommonTitle
import com.wjf.androidutils.compose.ui.component.net.NetComponent

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/21 8:00
 *
 */

@Composable
fun TitleBarComponent(
    navHostController: NavHostController,
    pageRoute: String
) {
    var title by rememberSaveable {
        mutableStateOf("")
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        val (viewTitle, viewPage, ivBg) = createRefs()

        Image(
            painter = painterResource(id = R.mipmap.common_bg),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(ivBg) {
                    top.linkTo(parent.top)
                }
        )
        CommonTitle(
            navHostController = navHostController,
            title = title,
            pageRoute = pageRoute,
            modifier = Modifier.constrainAs(viewTitle) {
                top.linkTo(parent.top)
            }
        )
        getPage(
            navHostController = navHostController,
            pageRoute = pageRoute,
            modifier = Modifier
                .constrainAs(viewPage) {
                    top.linkTo(viewTitle.bottom)
                },
            funName = {
                title = "$it"
            }
        )
    }
}

@Composable
fun getPage(
    navHostController: NavHostController,
    pageRoute: String,
    modifier: Modifier,
    funName: (String?)-> Unit
) {

    when (pageRoute) {
        RouteConfig.ROUTE_ENTER -> {
            EnterComponent(navHostController, modifier, funName)
        }
        RouteConfig.ROUTE_WIDGET -> {
            WidgetComponent(navHostController, modifier)
        }
        RouteConfig.ROUTE_FLOW -> {
            FlowComponent(navHostController, modifier)
        }
        RouteConfig.ROUTE_FLOW_HOT -> {
            FlowHotComponent(navHostController, modifier)
        }
        RouteConfig.ROUTE_NET -> {
            NetComponent(navHostController, modifier)
        }
    }
}

@Composable
@Preview(widthDp = 1080, heightDp = 1920, backgroundColor = 0xffffff, showBackground = true)
fun TitleBarComponentPreview() {
    TitleBarComponent(rememberNavController(), RouteConfig.ROUTE_ENTER)
}