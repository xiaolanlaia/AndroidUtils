package com.wjf.moduleutils.anim

import android.animation.TypeEvaluator
import android.graphics.Point

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/10 8:39
 *
 */

class PointEvaluator : TypeEvaluator<Point> {
    /**
     * 根据插值器计算出当前对象的属性的百分比fraction,估算属性当前具体的值
     * @param fraction 属性改变的百分比
     * @param startValue 对象开始的位置，例如这里点坐标开始位置：屏幕左上角位置
     * @param endValue 对象结束的位置，例如这里点坐标结束的位置:屏幕右下角位置
     */
    override fun evaluate(fraction: Float, startValue: Point?, endValue: Point?): Point {
        if (startValue == null || endValue == null) {
            return Point(0, 0)
        }

        return Point(
            (fraction * (endValue.x - startValue.x)).toInt(),
            (fraction * (endValue.y - startValue.y)).toInt()
        )
    }
}
