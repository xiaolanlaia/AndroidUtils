package com.wjf.androidutils.origin.utils

import com.wjf.androidutils.entity.FragmentBean

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/11/29 10:05
 *
 */

const val COMMON_FLAG = "common_flag"

/**
 * 页面导航
 */
const val JUMP_TO = "jump_to"
const val JUMP_TO_DesignFragment                    = "jump_to_DesignFragment"
const val JUMP_TO_PersistentFragment                = "jump_to_PersistentFragment"
const val JUMP_TO_ToastFragment                     = "jump_to_ToastFragment"
const val JUMP_TO_ArrayFragment                     = "jump_to_ArrayFragment"
const val JUMP_TO_FileFragment                      = "jump_to_FileFragment"
const val JUMP_TO_ExceptionFragment                 = "jump_to_ExceptionFragment"
const val JUMP_TO_ReflectFragment                   = "jump_to_ReflectFragment"
const val JUMP_TO_ImgLoaderFragment                 = "jump_to_ImgLoaderFragment"
const val JUMP_TO_WebViewFragment                   = "jump_to_WebViewFragment"
const val JUMP_TO_AnimFragment                      = "jump_to_AnimFragment"
const val JUMP_TO_BlueFragment                      = "jump_to_BlueFragment"
const val JUMP_TO_BlueClientFragment                = "jump_to_BlueClientFragment"
const val JUMP_TO_BlueServiceFragment               = "jump_to_BlueServiceFragment"
const val JUMP_TO_BleClientFragment                 = "jump_to_BleClientFragment"
const val JUMP_TO_BleServiceFragment                = "jump_to_BleServiceFragment"
const val JUMP_TO_DialogFragment                    = "jump_to_DialogFragment"
const val JUMP_TO_SelectTypeFragment                = "jump_to_SelectTypeFragment"
const val JUMP_TO_SocketClientFragment              = "jump_to_SocketClientFragment"
const val JUMP_TO_SocketServiceFragment             = "jump_to_SocketServiceFragment"
const val JUMP_TO_ScreenFragment                    = "jump_to_ScreenFragment"
const val JUMP_TO_HiltFragment                      = "jump_to_HiltFragment"
const val JUMP_TO_ConstraintLayoutFragment          = "jump_to_ConstraintLayoutFragment"
const val JUMP_TO_ViewPageFragment                  = "jump_to_ViewPageFragment"
const val JUMP_TO_ServiceSelectFragment             = "jump_to_ServiceSelectFragment"
const val JUMP_TO_ServiceStartFragment              = "jump_to_ServiceStartFragment"
const val JUMP_TO_ServiceBindFragment               = "jump_to_ServiceBindFragment"
const val JUMP_TO_ServiceMessageFragment            = "jump_to_ServiceMessageFragment"
const val JUMP_TO_ServiceForegroundFragment         = "JUMP_TO_ServiceForegroundFragment"
const val JUMP_TO_RecyclerViewSelectFragment        = "JUMP_TO_RecyclerViewSelectFragment"
const val JUMP_TO_RecyclerViewMultiFragment         = "JUMP_TO_RecyclerViewFragment"
const val JUMP_TO_SingletonFragment                 = "JUMP_TO_SingletonFragment"


const val ITEM_DESIGN              = "设计模式"
const val ITEM_PERSISTENT          = "持久化"
const val ITEM_TOAST               = "Toast"
const val ITEM_ARRAY               = "Array"
const val ITEM_FILE                = "file"
const val ITEM_EXCEPTION           = "exception"
const val ITEM_REFLECT             = "reflect"
const val ITEM_IMG                 = "img"
const val ITEM_WEB                 = "web"
const val ITEM_ANIM                = "anim"
const val ITEM_BLUE                = "Blue"
const val ITEM_BLUE_CLIENT         = "Blue client"
const val ITEM_BLUE_SERVICE        = "Blue service"
const val ITEM_BLE_CLIENT          = "Ble client"
const val ITEM_BLE_SERVICE         = "Ble service"
const val ITEM_DIALOG              = "Dialog"
const val ITEM_SOCKET              = "socket"
const val ITEM_SOCKET_CLIENT       = "socket client"
const val ITEM_SOCKET_SERVICE      = "socket service"
const val ITEM_SCREEN              = "Screen"
const val ITEM_HILT                = "hilt"
const val ITEM_CONSTRAINT          = "Constraint"
const val ITEM_VIEW_PAGE           = "ViewPage"
const val ITEM_SERVICE_SELECT      = "Service"
const val ITEM_SERVICE_START       = "Service start"
const val ITEM_SERVICE_BIND        = "Service bind"
const val ITEM_SERVICE_MESSAGE     = "Service message"
const val ITEM_SERVICE_FOREGROUND  = "Service foreground"
const val ITEM_RECYCLERVIEW_SELECT = "recyclerview"
const val ITEM_RECYCLERVIEW_MULTI  = "recyclerview multi"
const val ITEM_SINGLETON           = "Singleton"
const val ITEM_COMPOSE             = "Compose"

val JumpPageKey = LinkedHashMap<String, FragmentBean>().apply {
     put(ITEM_DESIGN,              FragmentBean(isShow = true,  jumpFlag = JUMP_TO_DesignFragment))
     put(ITEM_PERSISTENT,          FragmentBean(isShow = true,  jumpFlag = JUMP_TO_PersistentFragment))
     put(ITEM_TOAST,               FragmentBean(isShow = true,  jumpFlag = JUMP_TO_ToastFragment))
     put(ITEM_ARRAY,               FragmentBean(isShow = true,  jumpFlag = JUMP_TO_ArrayFragment))
     put(ITEM_FILE,                FragmentBean(isShow = true,  jumpFlag = JUMP_TO_FileFragment))
     put(ITEM_EXCEPTION,           FragmentBean(isShow = true,  jumpFlag = JUMP_TO_ExceptionFragment))
     put(ITEM_REFLECT,             FragmentBean(isShow = true,  jumpFlag = JUMP_TO_ReflectFragment))
     put(ITEM_IMG,                 FragmentBean(isShow = true,  jumpFlag = JUMP_TO_ImgLoaderFragment))
     put(ITEM_WEB,                 FragmentBean(isShow = true,  jumpFlag = JUMP_TO_WebViewFragment))
     put(ITEM_ANIM,                FragmentBean(isShow = true,  jumpFlag = JUMP_TO_AnimFragment))
     put(ITEM_BLUE,                FragmentBean(isShow = true,  jumpFlag = JUMP_TO_BlueFragment))
     put(ITEM_BLUE_CLIENT,         FragmentBean(isShow = false, jumpFlag = JUMP_TO_BlueClientFragment))
     put(ITEM_BLUE_SERVICE,        FragmentBean(isShow = false, jumpFlag = JUMP_TO_BlueServiceFragment))
     put(ITEM_BLE_CLIENT,          FragmentBean(isShow = false, jumpFlag = JUMP_TO_BleClientFragment))
     put(ITEM_BLE_SERVICE,         FragmentBean(isShow = false, jumpFlag = JUMP_TO_BleServiceFragment))
     put(ITEM_DIALOG,              FragmentBean(isShow = true,  jumpFlag = JUMP_TO_DialogFragment))
     put(ITEM_SOCKET,              FragmentBean(isShow = true,  jumpFlag = JUMP_TO_SelectTypeFragment))
     put(ITEM_SOCKET_CLIENT,       FragmentBean(isShow = false, jumpFlag = JUMP_TO_SocketClientFragment))
     put(ITEM_SOCKET_SERVICE,      FragmentBean(isShow = false, jumpFlag = JUMP_TO_SocketServiceFragment))
     put(ITEM_SCREEN,              FragmentBean(isShow = true,  jumpFlag = JUMP_TO_ScreenFragment))
     put(ITEM_HILT,                FragmentBean(isShow = true,  jumpFlag = JUMP_TO_HiltFragment))
     put(ITEM_CONSTRAINT,          FragmentBean(isShow = true,  jumpFlag = JUMP_TO_ConstraintLayoutFragment))
     put(ITEM_VIEW_PAGE,           FragmentBean(isShow = true,  jumpFlag = JUMP_TO_ViewPageFragment))
     put(ITEM_SERVICE_SELECT,      FragmentBean(isShow = true,  jumpFlag = JUMP_TO_ServiceSelectFragment))
     put(ITEM_SERVICE_START,       FragmentBean(isShow = false, jumpFlag = JUMP_TO_ServiceStartFragment))
     put(ITEM_SERVICE_BIND,        FragmentBean(isShow = false, jumpFlag = JUMP_TO_ServiceBindFragment))
     put(ITEM_SERVICE_MESSAGE,     FragmentBean(isShow = false, jumpFlag = JUMP_TO_ServiceMessageFragment))
     put(ITEM_SERVICE_FOREGROUND,  FragmentBean(isShow = false, jumpFlag = JUMP_TO_ServiceForegroundFragment))
     put(ITEM_RECYCLERVIEW_SELECT, FragmentBean(isShow = true,  jumpFlag = JUMP_TO_RecyclerViewSelectFragment))
     put(ITEM_RECYCLERVIEW_MULTI,  FragmentBean(isShow = false, jumpFlag = JUMP_TO_RecyclerViewMultiFragment))
     put(ITEM_SINGLETON,           FragmentBean(isShow = true,  jumpFlag = JUMP_TO_SingletonFragment))
     put(ITEM_COMPOSE,             FragmentBean(isShow = true,  jumpFlag = ""))
}


const val LABEL = "label"

const val START_FOR_RESULT = 1

class Constant {
}