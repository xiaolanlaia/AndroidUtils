package com.wjf.androidutils.compose.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.wjf.androidutils.compose.ui.base.TitleBarComponent

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/19 19:25
 *
 */

@Composable
fun MyNavHost(navHostController : NavHostController = rememberNavController()) {


    //建立对应关系
    NavHost(
        navController = navHostController,
        startDestination = RouteConfig.ROUTE_ENTER
    ){

        composable(RouteConfig.ROUTE_ENTER){
            TitleBarComponent(navHostController, RouteConfig.ROUTE_ENTER)
        }
        composable(RouteConfig.ROUTE_WIDGET){
            TitleBarComponent(navHostController, RouteConfig.ROUTE_WIDGET)
        }
        composable(RouteConfig.ROUTE_FLOW){
            TitleBarComponent(navHostController, RouteConfig.ROUTE_FLOW)
        }
        composable(RouteConfig.ROUTE_FLOW_HOT){
            TitleBarComponent(navHostController, RouteConfig.ROUTE_FLOW_HOT)
        }
        composable(RouteConfig.ROUTE_NET){
            TitleBarComponent(navHostController, RouteConfig.ROUTE_NET)
        }

//        composable(
//            route = "${RouteConfig.ROUTE_DETECT}/{$COMMON_FLAG}",
//            arguments = listOf(
//                navArgument(COMMON_FLAG){type = NavType.BoolType}
//            )
//        ){
//            val argument = requireNotNull(it.arguments)
//            val newIn = argument.getBoolean(COMMON_FLAG)
//            TitleBarComponent(navHostController,RouteConfig.ROUTE_DETECT,newIn = newIn)
//        }
    }
}