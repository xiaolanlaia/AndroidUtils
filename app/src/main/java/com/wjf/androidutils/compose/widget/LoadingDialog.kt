package com.wjf.androidutils.compose.widget

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.constraintlayout.compose.ConstraintLayout
import com.wjf.androidutils.R

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/22 15:36
 *
 */
@Composable
fun LoadingDialog(msg : String = "加载中..", dismiss: () -> Unit) {

    // 创建一个 [InfiniteTransition] 实列用来管理子动画
    val infiniteTransition = rememberInfiniteTransition()

    // 创建一个float类型的子动画
    val angle by infiniteTransition.animateFloat(
        initialValue = 0F, //动画创建后，会从[initialValue] 执行至[targetValue]，
        targetValue = 360F,
        animationSpec = infiniteRepeatable(
            //tween是补间动画，使用线性[LinearEasing]曲线无限重复1000 ms的补间动画
            animation = tween(1000, easing = LinearEasing),
            //每次迭代后的动画(即每1000毫秒), 动画将从上面定义的[initialValue]重新开始执行
            //动画执行模式
            // repeatMode = RepeatMode.Restart //此处不需要，它自己会无线重复执行
        )
    )

    Dialog(
        onDismissRequest = { dismiss() }
    ) {

        ConstraintLayout(
            modifier = Modifier
                .size(width = 300.dp, height = 220.dp)
                .background(colorResource(id = R.color.half_transparent))
        ){

            val (ivLoad, tvLoad) = createRefs()

            Image(
                painter = painterResource(id = R.mipmap.icon_loading),
                contentDescription = "",
                modifier = Modifier
                    .size(70.dp)
                    .graphicsLayer { rotationZ = angle }
                    .constrainAs(ivLoad) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top, margin = 50.dp)

                    }
            )

            Text(
                text = msg,
                style = TextStyle(
                    color = Color.White,
                    fontSize = 30.sp
                ),
                modifier = Modifier
                    .constrainAs(tvLoad){
                        top.linkTo(ivLoad.bottom, margin = 15.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
        }
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0x000000, widthDp = 1920, heightDp = 1080)
fun LoadingDialogPreview(){
    LoadingDialog(){

    }
}