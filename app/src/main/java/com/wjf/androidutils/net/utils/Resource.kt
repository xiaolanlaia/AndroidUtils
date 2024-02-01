package com.wjf.androidutils.net.utils

import com.wjf.androidutils.entity.BaseEntity


data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(baseBean: BaseEntity<T>): Resource<Any?> {
            return when(baseBean.code){
                "200" -> { Resource(status = Status.SUCCESS, data = baseBean.data?: "", message = null) }
                else -> { Resource(status = Status.ERROR, data = baseBean, message = baseBean.message) }
            }
        }

        fun <T> error(data: T?, message: String): Resource<Any?> =
            Resource(status = Status.ERROR, data = data, message = message)

        fun <T> loading(data: T?): Resource<T> = Resource(status = Status.LOADING, data = data, message = null)
    }
}