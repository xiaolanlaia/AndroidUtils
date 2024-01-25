package com.wjf.androidutils.origin.utils

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.wjf.androidutils.R

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2024/1/25 15:10
 *
 */

class SealedUtils {
    sealed class BottomNavScreen(val route: String, @StringRes val resourceId: Int, @DrawableRes val id: Int) {
        object HomeScreen : BottomNavScreen("home", R.string.bt, R.mipmap.ic_launcher)
        object ProjectScreen : BottomNavScreen("project", R.string.bt, R.mipmap.ic_launcher)
        object SquareScreen : BottomNavScreen("square", R.string.bt, R.mipmap.ic_launcher)
        object PublicNumScreen : BottomNavScreen("publicNum", R.string.bt, R.mipmap.ic_launcher)
        object LearnScreen : BottomNavScreen("learn", R.string.bt, R.mipmap.ic_launcher)
        object MineScreen : BottomNavScreen("mine", R.string.bt, R.mipmap.ic_launcher)
    }
}