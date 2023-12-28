package com.wjf.androidutils.compose.widget

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/21 8:57
 *
 */

@Composable
fun CustomEdit(
    text: String = "",
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    hintText: String = "",
    hintTextStyle: TextStyle = TextStyle.Default,
    @DrawableRes startIconId: Int = -1,
    startIconModifier: Modifier = Modifier,
    @ColorRes bottomLineColor: Color = Color.Gray,
    iconSpacing: Dp = 6.dp,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = TextStyle.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    cursorBrush: Brush = SolidColor(Color.Black),
    backgroundColor: Color = Color.Transparent
) {
    // 焦点, 用于控制是否显示 右侧叉号
    var hasFocus by remember { mutableStateOf(false) }
    BasicTextField(
        value = text,
        onValueChange = onValueChange,
        modifier = modifier.onFocusChanged { hasFocus = it.isFocused },
        singleLine = true,
        enabled = enabled,
        readOnly = readOnly,
        textStyle = textStyle,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        visualTransformation = visualTransformation,
        cursorBrush = cursorBrush,
        decorationBox = @Composable { innerTextField ->
            ConstraintLayout (
                modifier = Modifier
                    .background(backgroundColor)
                    .fillMaxSize()
            ){

                val(startIcon,startIconSpacer,hint,clearIcon,bottomLine,innerTextFieldView) = createRefs()

                // -1 不显示 左侧Icon
                if(startIconId != -1){
                    Image(
                        painter = painterResource(id = startIconId),
                        contentDescription = null,
                        modifier = startIconModifier.constrainAs(startIcon){
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)

                        }
                    )
                }

                Spacer(modifier = Modifier
                    .width(iconSpacing)
                    .constrainAs(startIconSpacer) {
                        start.linkTo(startIcon.end)
                        top.linkTo(startIcon.top)
                        bottom.linkTo(startIcon.bottom)

                    })

                // 当空字符时, 显示hint
                if(text.isEmpty()){
                    Text(
                        text = hintText,
                        style = hintTextStyle,
                        modifier = Modifier
                            .constrainAs(hint){
                                start.linkTo(startIconSpacer.end)
                                end.linkTo(clearIcon.start)
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                            }
                    )
                }
                
                // 存在焦点 且 有输入内容时. 显示叉号
                if(hasFocus && text.isNotEmpty()) {
                    Image(imageVector = Icons.Filled.Clear, // 清除图标
                        contentDescription = null,
                        // 点击就清空text
                        modifier = Modifier
                            .constrainAs(clearIcon) {
                                end.linkTo(parent.end, margin = 10.dp)
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                            }
                            .clickable { onValueChange.invoke("") })
                }

                Box(modifier = Modifier
                    .background(bottomLineColor)
                    .constrainAs(bottomLine) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
                    .fillMaxWidth()
                    .height(1.dp))

                // 原本输入框的内容
                Box(modifier = Modifier
                    .constrainAs(innerTextFieldView){
                        start.linkTo(startIconSpacer.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                ) {
                    innerTextField()
                }
            }
        }
    )
}

@Composable
@Preview(showBackground = true, backgroundColor = 0x000000, widthDp = 540, heightDp = 32)
fun CustomEditPreview(){
    CustomEdit(
        text = "aEVValue",
        onValueChange = {  },
        textStyle = TextStyle(
            fontSize = 23.sp,
            color = Color.Black,
        ),
        bottomLineColor = Color.Red,
        modifier = Modifier
            .height(32.dp)
    )
}
