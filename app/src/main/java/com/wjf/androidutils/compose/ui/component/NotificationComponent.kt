package com.wjf.androidutils.compose.ui.component

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.wjf.androidutils.R
import com.wjf.androidutils.origin.base.transit.TitleBarActivity
import com.wjf.moduleutils.LogUtils
import com.wjf.moduleutils.notification.NotificationUtils
import java.util.LinkedList

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2024/1/17 16:59
 *
 */

const val NOTIFY_NORMAL              = "普通通知"
const val NOTIFY_IMPORTANT           = "重要通知"
const val NOTIFY_PROGRESS            = "进度条通知"
const val NOTIFY_PROGRESS_UPDATE     = "进度条更新"
const val NOTIFY_PROGRESS_SUCCESS    = "进度条完成"
const val NOTIFY_BIG_TEXT            = "大文本通知"
const val NOTIFY_BIG_IMG             = "大图通知"
const val NOTIFY_CUSTOMIZE           = "自定义通知"
const val NOTIFY_CUSTOMIZE_UPDATE    = "自定义通知更新"
const val NOTIFY_CUSTOMIZE_DONE      = "自定义通知完成"
const val NOTIFY_CUSTOMIZE_CANCEL    = "自定义通知取消"

val notifyList = LinkedList<String>().apply{
    add(NOTIFY_NORMAL)
    add(NOTIFY_IMPORTANT)
    add(NOTIFY_PROGRESS)
    add(NOTIFY_PROGRESS_UPDATE)
    add(NOTIFY_PROGRESS_SUCCESS)
    add(NOTIFY_BIG_TEXT)
    add(NOTIFY_BIG_IMG)
    add(NOTIFY_CUSTOMIZE)
    add(NOTIFY_CUSTOMIZE_UPDATE)
    add(NOTIFY_CUSTOMIZE_DONE)
    add(NOTIFY_CUSTOMIZE_CANCEL)
}
@Composable
fun NotificationComponent(
    navHostController : NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current as ComponentActivity
    LaunchedEffect(key1 = "", block = {
        context.lifecycle.addObserver(NotificationUtils.newInstance(context))
    })

    LazyVerticalGrid(
        contentPadding = PaddingValues(20.dp),
        columns = GridCells.Fixed(4),
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        content = {
            items(notifyList.size){ index ->
                Text(
                    text = notifyList[index],
                    textAlign = TextAlign.Center,
                    style = TextStyle(fontSize = 40.sp, color = Color.White),
                    modifier = Modifier
                        .size(width = 240.dp, height = 120.dp)
                        .background(color = colorResource(id = R.color.half_transparent))
                        .clickable {
                            when (notifyList[index]) {
                                NOTIFY_NORMAL -> {
                                    NotificationUtils.newInstance().notificationNormal(TitleBarActivity::class.java)
                                }

                                NOTIFY_IMPORTANT -> {
                                    NotificationUtils.newInstance().notificationHigh(TitleBarActivity::class.java)
                                }

                                NOTIFY_PROGRESS -> {
                                    NotificationUtils.newInstance().notificationProgress()
                                }

                                NOTIFY_PROGRESS_UPDATE -> {
                                    NotificationUtils.newInstance().updateNotificationProgress(50)
                                }

                                NOTIFY_PROGRESS_SUCCESS -> {
                                    NotificationUtils.newInstance().updateNotificationProgress(100)
                                }

                                NOTIFY_BIG_TEXT -> {
                                    NotificationUtils.newInstance().notificationBigText()
                                }

                                NOTIFY_BIG_IMG -> {
                                    NotificationUtils.newInstance().notificationBigImage()
                                }

                                NOTIFY_CUSTOMIZE -> {
                                    LogUtils.d("__CUSTOMIZE","1")
                                    NotificationUtils.newInstance().notificationCustom()
                                }

                                NOTIFY_CUSTOMIZE_UPDATE -> {
                                    NotificationUtils.newInstance().updateNotificationCustom()
                                }

                                NOTIFY_CUSTOMIZE_CANCEL -> {
                                    NotificationUtils.newInstance().cancelNotificationCustom()
                                }

                                NOTIFY_CUSTOMIZE_DONE -> {
                                    NotificationUtils.newInstance().doneNotificationCustom()
                                }
                            }
                        }
                        .semantics { contentDescription = notifyList[index] }
                        .wrapContentSize(Alignment.Center)
                )
            }
        }
    )
}

@Composable
@Preview(showBackground = true, backgroundColor = 0x000000, widthDp = 1080, heightDp = 1920)
fun NotificationComponentPreview(){
    NotificationComponent()
}