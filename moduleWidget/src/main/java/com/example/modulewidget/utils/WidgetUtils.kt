package com.example.modulewidget.utils

import android.graphics.Point
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/11 13:55
 *
 */

object WidgetUtils {

    /**
     * 两个point之间的距离
     */
    fun pointDistance(a : Point, b : Point) : Double{

        val dx = (b.x - a.x).toDouble().pow(2)
        val dy = (b.y - a.y).toDouble().pow(2)

        return sqrt(dx + dy)
    }
}