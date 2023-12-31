package com.wjf.androidutils.compose.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.wjf.androidutils.compose.ui.component.EnterComponent

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/19 19:25
 *
 */

@Composable
fun MyNavHost(navHostController : NavHostController = rememberNavController()) {


    NavHost(
        navController = navHostController,
        startDestination = RouteConfig.ROUTE_ENTER
    ){

        //建立对应关系
        composable(RouteConfig.ROUTE_ENTER){
            EnterComponent(navHostController)
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