package com.wjf.moduleutils.receiver.time

class TimeCallback {

    companion object{
        var timeInterface: TimeInterface? = null
    }

    interface TimeInterface{
        fun updateTime(time: String)
    }
}