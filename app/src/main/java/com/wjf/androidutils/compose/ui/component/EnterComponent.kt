package com.wjf.androidutils.compose.ui.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/31 8:20
 *
 */

@Composable
fun EnterComponent(
    navHostController : NavHostController
) {

    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {

        val (btnWidget) = createRefs()

        Button(
            onClick = {

            }) {

            Text(
                text = "Widget",
                style = TextStyle(
                    fontSize = 40.sp,
                    color = Color.Black
                )
            )

        }

    }

}

@Composable
@Preview(showBackground = true, backgroundColor = 0x000000, widthDp = 1080, heightDp = 1920)
fun EnterComponentPreview() {
    EnterComponent(rememberNavController())
}