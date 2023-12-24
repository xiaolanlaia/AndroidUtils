package com.wjf.androidutils.compose.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.wjf.androidutils.MyApplication
import com.wjf.androidutils.R

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )

)

val textStyle_52_222222_bold = TextStyle(
    fontSize = 52.sp,
    color = Color(MyApplication.instance.getColor(R.color.color_222222)),
    fontWeight = FontWeight.Bold
)

val textStyle_40_999999 = TextStyle(
    fontSize = 40.sp,
    color = Color(MyApplication.instance.getColor(R.color.color_999999)),
)
