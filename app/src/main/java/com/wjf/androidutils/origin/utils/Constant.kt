package com.wjf.androidutils.origin.utils

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

sealed class JumpSealed(val jumpName: String, val jumpTag: String, val showInHome: Boolean) {
     object Design            : JumpSealed("Design"               , "jump_to_DesignFragment"               , true)
     object Persistent        : JumpSealed("Persistent"           , "jump_to_PersistentFragment"           , true)
     object Toast             : JumpSealed("Toast"                , "jump_to_ToastFragment"                , true)
     object Array             : JumpSealed("Array"                , "jump_to_ArrayFragment"                , true)
     object File              : JumpSealed("file"                 , "jump_to_FileFragment"                 , true)
     object Exception         : JumpSealed("exception"            , "jump_to_ExceptionFragment"            , true)
     object Reflect           : JumpSealed("reflect"              , "jump_to_ReflectFragment"              , true)
     object ImgLoader         : JumpSealed("img"                  , "jump_to_ImgLoaderFragment"            , true)
     object WebView           : JumpSealed("WebView"              , "jump_to_WebViewFragment"              , true)
     object Anim              : JumpSealed("anim"                 , "jump_to_AnimFragment"                 , true)
     object Blue              : JumpSealed("Blue"                 , "jump_to_BlueFragment"                 , true)
     object BlueClient        : JumpSealed("Blue client"          , "jump_to_BlueClientFragment"           , false)
     object BlueService       : JumpSealed("Blue service"         , "jump_to_BlueServiceFragment"          , false)
     object BleClient         : JumpSealed("Ble client"           , "jump_to_BleClientFragment"            , false)
     object BleService        : JumpSealed("Ble service"          , "jump_to_BleServiceFragment"           , false)
     object Dialog            : JumpSealed("Dialog"               , "jump_to_DialogFragment"               , true)
     object SelectType        : JumpSealed("socket"               , "jump_to_SelectTypeFragment"           , true)
     object SocketClient      : JumpSealed("socket client"        , "jump_to_SocketClientFragment"         , false)
     object SocketService     : JumpSealed("socket service"       , "jump_to_SocketServiceFragment"        , false)
     object Screen            : JumpSealed("Screen"               , "jump_to_ScreenFragment"               , true)
     object Hilt              : JumpSealed("hilt"                 , "jump_to_HiltFragment"                 , true)
     object ConstraintLayout  : JumpSealed("Constraint"           , "jump_to_ConstraintLayoutFragment"     , true)
     object ViewPage          : JumpSealed("ViewPage"             , "jump_to_ViewPageFragment"             , true)
     object ServiceSelect     : JumpSealed("Service"              , "jump_to_ServiceSelectFragment"        , true)
     object ServiceStart      : JumpSealed("Service start"        , "jump_to_ServiceStartFragment"         , false)
     object ServiceBind       : JumpSealed("Service bind"         , "jump_to_ServiceBindFragment"          , false)
     object ServiceMessage    : JumpSealed("Service message"      , "jump_to_ServiceMessageFragment"       , false)
     object ServiceForeground : JumpSealed("Service foreground"   , "JUMP_TO_ServiceForegroundFragment"    , false)
     object RecyclerViewSelect: JumpSealed("recyclerview"         , "JUMP_TO_RecyclerViewSelectFragment"   , true)
     object RecyclerViewMulti : JumpSealed("recyclerview multi"   , "JUMP_TO_RecyclerViewFragment"         , false)
     object Singleton         : JumpSealed("Singleton"            , "JUMP_TO_SingletonFragment"            , true)
     object Compose           : JumpSealed("Compose"              , ""                                     , true)
}


const val LABEL = "label"

const val START_FOR_RESULT = 1

class Constant {
}