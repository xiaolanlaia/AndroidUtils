package com.wjf.androidutils.utils

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

@SuppressLint("SimpleDateFormat")
object TimeUtil {
    private val format1 = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    private val format2 = SimpleDateFormat("yyyy年MM月dd日")
    private val format3 = SimpleDateFormat("yyyy/MM/dd")
    private val format4 = SimpleDateFormat("HH:mm:ss")
    private val format5 = SimpleDateFormat("MM/dd E HH:mm")
    private val format6 = SimpleDateFormat("yyyy-MM-dd")

    fun getTime(date: Date? = Date(),type: Int? = 1): String {
        return when(type){
            1 -> {format1.format(date!!)}
            2 -> {format2.format(date!!)}
            3 -> {format3.format(date!!)}
            4 -> {format4.format(date!!)}
            5 -> {format5.format(date!!)}
            6 -> {format6.format(date!!)}
            else -> {format1.format(date!!)}
        }
    }


    /**
     * 获取当前日期是星期几
     *
     * @param date
     * @return
     */
    fun getWeekOfDate(date: Date?): String {
        val weekDays = arrayOf("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六")
        val cal = Calendar.getInstance()
        cal.time = date
        var w = cal[Calendar.DAY_OF_WEEK] - 1
        if (w < 0) {
            w = 0
        }
        return weekDays[w]
    }

    /**
     * 获取当前日期是周几
     */
    fun weekOfDate(): String {
        val weekDays = arrayOf("周日", "周一", "周二", "周三", "周四", "周五", "周六")
        val cal = Calendar.getInstance()
        cal.time = Date()
        var w = cal[Calendar.DAY_OF_WEEK] - 1
        if (w < 0) {
            w = 0
        }
        return weekDays[w]
    }

    /**
     * 将 yyyy-MM-dd HH:mm:ss 转成 HHmm
     */
    fun getStringToDate(dateString: String?): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val dateFormat2 = SimpleDateFormat("HHmm")
        try {
            val date = dateFormat.parse(dateString)
            return dateFormat2.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return ""
    }
}