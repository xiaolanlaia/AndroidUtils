package com.wjf.androidutils.compose.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.wjf.androidutils.R
import com.wjf.androidutils.compose.theme.textStyle_20_white

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2024/1/7 14:47
 *
 */


@Composable
fun TitleText(title: String){
    Text(
        text = title,
        style = textStyle_20_white,
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .background(color = colorResource(id = R.color.half_transparent))
            .wrapContentSize(Alignment.Center)
    )
}