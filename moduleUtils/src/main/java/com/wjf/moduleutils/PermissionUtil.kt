package com.wjf.moduleutils

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.LinkedList

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/12 17:56
 *
 */

class PermissionUtil {


    companion object{
        val instance by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { PermissionUtil() }
    }


    /**
     * 判断权限集合
     * permissions 权限数组
     */
    fun requestPermissions(activity: Activity, permissionList : LinkedList<String>, requestCode : Int) {
        for (permission in permissionList) {
            if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity, permissionList.toTypedArray(), requestCode)
            }
        }
    }
}