package com.wjf.androidutils.utils

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

@SuppressLint("SimpleDateFormat")
object TimeUtil {

    /**
     * Y表示这周的年份，y表示标准的年份，若本周过年，Y会算入下一年
     *
     * y：年份
     * yy：两位数年份
     * yyyy：四位数年份
     * M：月份
     * MM：月份
     * MMM：自动切换语言
     * H：24小时制的小时数，用HH表示
     * h：12小时制的小时数，用hh表示
     * m：分钟数，用mm表示
     * s：秒数，用ss表示
     * S：毫秒数，用SSS表示
     * d：月份中的天数，用dd表述
     * D：年份中的天数
     * E：周几，会自动切换语言
     * F：月份中的第几个星期几
     * w：年份中的第几个星期
     */
    fun getTime(date: Date? = Date(),pattern: String? = "yyyy-MM-dd HH:mm:ss"): String {
        return SimpleDateFormat(pattern).format(date!!)
    }

    /**
     * 将pattern1格式日期转为pattern2格式
     */
    fun getStringToDate(dateString: String?, pattern1: String, pattern2: String): String {
        val dateFormat = SimpleDateFormat(pattern1)
        val dateFormat2 = SimpleDateFormat(pattern2)
        try {
            val date = dateFormat.parse(dateString)
            return dateFormat2.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return ""
    }
}