package com.wjf.androidutils.compose.ui.component.net

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.wjf.androidutils.net.api.ApiHelper
import com.wjf.androidutils.net.utils.Resource

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2024/1/7 15:08
 *
 */

class NetViewModel: ViewModel() {

    fun getUsers() = liveData {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(baseBean = ApiHelper.getUsers()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

}