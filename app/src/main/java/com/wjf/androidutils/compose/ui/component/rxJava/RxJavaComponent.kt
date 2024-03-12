package com.wjf.androidutils.compose.ui.component.rxJava

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.wjf.androidutils.R

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2024/3/12 9:50
 *
 */

@Composable
fun RxJavaComponent(
    navHostController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
    vm: RxJavaViewModel = viewModel()
) {

    val observerValue by vm.observerValue.collectAsState()
    val chainValue by vm.chainValue.collectAsState()

    Column(
        modifier = modifier
    ) {

        /**
         * 分步骤实现
         */
        stepWidget(
            observerValue = observerValue,
            createObservable = { vm.createObservable() },
            createObserver = { vm.createObserver() },
            observer = { vm.observer() },
            disposable = { vm.disposable() }
        )

        /**
         * 基于事件流的链式调用
         */
        chainWidget(
            observerValue = chainValue,
            chainCall = { vm.chainCall() },
            disposableChain = { vm.disposableChain() }
        )
    }

}

@Composable
fun stepWidget(
    observerValue: String,
    createObservable: () -> Unit,
    createObserver: () -> Unit,
    observer: () -> Unit,
    disposable: () -> Unit,
    ) {

    Text(
        text = "分步骤实现",
        fontSize = 45.sp,
        color = Color.White,
        modifier = Modifier
            .padding(20.dp)
            .background(color = colorResource(id = R.color.color_42))
            .clickable {

            }
            .padding(20.dp)
    )
    Row {
        Text(
            text = "创建被观察者",
            fontSize = 45.sp,
            modifier = Modifier
                .padding(20.dp)
                .clickable {
                    createObservable()
                }
                .padding(20.dp)
        )

        Text(
            text = "创建观察者",
            fontSize = 45.sp,
            modifier = Modifier
                .padding(20.dp)
                .clickable {
                    createObserver()
                }
                .padding(20.dp)
        )

        Text(
            text = "订阅",
            fontSize = 45.sp,
            modifier = Modifier
                .padding(20.dp)
                .clickable {
                    observer()
                }
                .padding(20.dp)
        )

        Text(
            text = "取消订阅",
            fontSize = 45.sp,
            modifier = Modifier
                .padding(20.dp)
                .clickable {
                    disposable()
                }
                .padding(20.dp)
        )
    }


    Text(
        text = observerValue,
        fontSize = 45.sp,
        modifier = Modifier
            .padding(20.dp)
            .clickable {

            }
            .padding(20.dp)
    )
}

@Composable
fun chainWidget(
    observerValue: String,
    chainCall: () -> Unit,
    disposableChain: () -> Unit,
) {

    Text(
        text = "链式调用",
        fontSize = 45.sp,
        color = Color.White,
        modifier = Modifier
            .padding(100.dp)
            .background(color = colorResource(id = R.color.color_42))
            .clickable {

            }
            .padding(20.dp)
    )
    Text(
        text = "调用",
        fontSize = 45.sp,
        modifier = Modifier
            .padding(20.dp)
            .clickable {
                chainCall()
            }
            .padding(20.dp)
    )

    Text(
        text = "取消订阅",
        fontSize = 45.sp,
        modifier = Modifier
            .padding(20.dp)
            .clickable {
                disposableChain()
            }
            .padding(20.dp)
    )


    Text(
        text = observerValue,
        fontSize = 45.sp,
        modifier = Modifier
            .padding(20.dp)
            .clickable {

            }
            .padding(20.dp)
    )
}