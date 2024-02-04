package com.wjf.androidutils.origin.base.transit

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.wjf.androidutils.R
import com.wjf.androidutils.origin.base.MVVMBaseActivity
import com.wjf.androidutils.databinding.ActivityTitleBarBinding
import com.wjf.androidutils.origin.ui.AnimFragment
import com.wjf.androidutils.origin.ui.ArrayFragment
import com.wjf.androidutils.origin.ui.ConstraintLayoutFragment
import com.wjf.androidutils.origin.ui.design.DesignFragment
import com.wjf.androidutils.origin.ui.ExceptionFragment
import com.wjf.androidutils.origin.ui.FileFragment
import com.wjf.androidutils.origin.ui.ImgLoaderFragment
import com.wjf.androidutils.origin.ui.PersistentFragment
import com.wjf.androidutils.origin.ui.ScreenFragment
import com.wjf.androidutils.origin.ui.SingletonFragment
import com.wjf.androidutils.origin.ui.ToastFragment
import com.wjf.androidutils.origin.ui.ViewPageFragment
import com.wjf.androidutils.origin.ui.WebViewFragment
import com.wjf.androidutils.origin.ui.blue.bt.BlueClientFragment
import com.wjf.androidutils.origin.ui.blue.BlueFragment
import com.wjf.androidutils.origin.ui.blue.ble.BleClientFragment
import com.wjf.androidutils.origin.ui.blue.ble.BleServiceFragment
import com.wjf.androidutils.origin.ui.blue.bt.BlueServiceFragment
import com.wjf.androidutils.origin.ui.dialog.DialogFragment
import com.wjf.androidutils.origin.ui.hilt.HiltFragment
import com.wjf.androidutils.origin.ui.home.HomeFragment
import com.wjf.androidutils.origin.ui.recyclerView.RecyclerViewMultiFragment
import com.wjf.androidutils.origin.ui.recyclerView.RecyclerViewSelectFragment
import com.wjf.androidutils.origin.ui.reflect.ReflectFragment
import com.wjf.androidutils.origin.ui.service.ServiceBindFragment
import com.wjf.androidutils.origin.ui.service.ServiceForegroundFragment
import com.wjf.androidutils.origin.ui.service.ServiceMessageFragment
import com.wjf.androidutils.origin.ui.service.ServiceSelectFragment
import com.wjf.androidutils.origin.ui.service.ServiceStartFragment
import com.wjf.androidutils.origin.ui.socket.SelectTypeFragment
import com.wjf.androidutils.origin.ui.socket.SocketClientFragment
import com.wjf.androidutils.origin.ui.socket.SocketServiceFragment
import com.wjf.androidutils.origin.utils.COMMON_FLAG
import com.wjf.androidutils.origin.utils.JUMP_TO
import com.wjf.androidutils.origin.utils.JumpSealed
import com.wjf.androidutils.origin.utils.START_FOR_RESULT
import com.wjf.moduleutils.LogUtils
import com.wjf.moduleutils.PermissionUtil
import com.wjf.moduleutils.ScreenUtils
import com.wjf.androidutils.receiver.time.TimeCallback
import com.wjf.androidutils.receiver.time.TimeReceiver
import com.wjf.moduleutils.singleClick
import dagger.hilt.android.AndroidEntryPoint
import java.util.LinkedList

@AndroidEntryPoint
class TitleBarActivity : MVVMBaseActivity<TitleBarViewModel, ActivityTitleBarBinding>(), TimeCallback.TimeInterface {


    companion object{
        fun newInstance(context: Context, jumpTo: String){
            val intent = Intent(context, TitleBarActivity::class.java)
            intent.putExtra(JUMP_TO,jumpTo)
            context.startActivity(intent)
        }
    }

    //首先定义请求变量
    private var permissionList = LinkedList<String>().apply {
        add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        add(Manifest.permission.READ_PHONE_STATE)
    }

    //请求状态码，请求码的作用是与回调函数进行匹配的，这样就可以对不同权限操作进行不同的提示
    val REQUEST_CODE = 1

    lateinit var fragment: Fragment

    override fun initViewBinding(inflater: LayoutInflater) = ActivityTitleBarBinding.inflate(inflater)


    override fun initViewModel() = ViewModelProviders.of(this).get(TitleBarViewModel::class.java)

    override fun initView(){

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU){
            permissionList.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        }else{
            permissionList.add(Manifest.permission.READ_MEDIA_AUDIO)
            permissionList.add(Manifest.permission.READ_MEDIA_IMAGES)
            permissionList.add(Manifest.permission.READ_MEDIA_VIDEO)
        }
        PermissionUtil.instance.requestPermissions(this,permissionList,REQUEST_CODE)

        val layoutParams = binding.viewStatus.layoutParams
        layoutParams.height = ScreenUtils.instance.getStatusBarHeight()
        binding.viewStatus.layoutParams = layoutParams

        fragment = when(intent.getStringExtra(JUMP_TO)) {

            JumpSealed.Design.jumpTag -> { DesignFragment() }

            JumpSealed.Persistent.jumpTag -> { PersistentFragment() }

            JumpSealed.Toast.jumpTag -> { ToastFragment() }

            JumpSealed.Array.jumpTag -> { ArrayFragment() }

            JumpSealed.File.jumpTag -> { FileFragment() }

            JumpSealed.Exception.jumpTag -> { ExceptionFragment() }

            JumpSealed.Reflect.jumpTag -> { ReflectFragment() }

            JumpSealed.ImgLoader.jumpTag -> { ImgLoaderFragment() }

            JumpSealed.WebView.jumpTag -> { WebViewFragment() }

            JumpSealed.Anim.jumpTag -> { AnimFragment() }

            JumpSealed.Blue.jumpTag -> { BlueFragment() }

            JumpSealed.BlueClient.jumpTag -> { BlueClientFragment() }

            JumpSealed.BlueService.jumpTag -> { BlueServiceFragment() }

            JumpSealed.BleClient.jumpTag -> { BleClientFragment() }

            JumpSealed.BleService.jumpTag -> { BleServiceFragment() }

            JumpSealed.Dialog.jumpTag -> { DialogFragment() }

            JumpSealed.SelectType.jumpTag -> { SelectTypeFragment() }

            JumpSealed.SocketClient.jumpTag -> { SocketClientFragment() }

            JumpSealed.SocketService.jumpTag -> { SocketServiceFragment() }

            JumpSealed.Screen.jumpTag -> { ScreenFragment() }

            JumpSealed.Hilt.jumpTag -> { HiltFragment() }

            JumpSealed.ConstraintLayout.jumpTag -> { ConstraintLayoutFragment() }

            JumpSealed.ViewPage.jumpTag -> { ViewPageFragment() }

            JumpSealed.ServiceSelect.jumpTag -> { ServiceSelectFragment() }

            JumpSealed.ServiceStart.jumpTag -> { ServiceStartFragment() }

            JumpSealed.ServiceBind.jumpTag -> { ServiceBindFragment() }

            JumpSealed.ServiceMessage.jumpTag -> { ServiceMessageFragment() }

            JumpSealed.ServiceForeground.jumpTag -> { ServiceForegroundFragment() }

            JumpSealed.RecyclerViewSelect.jumpTag -> { RecyclerViewSelectFragment() }

            JumpSealed.RecyclerViewMulti.jumpTag -> { RecyclerViewMultiFragment() }

            JumpSealed.Singleton.jumpTag -> { SingletonFragment() }

            else -> { HomeFragment() }
        }

        binding.commonTitle.tvPageTitle.text = fragment.javaClass.simpleName
        supportFragmentManager.beginTransaction().replace(R.id.info_content, fragment).commit()

    }

    override fun initData() {
        lifecycle.addObserver(TimeReceiver(this))
    }

    override fun initClick(){
        binding.commonTitle.linearBack.singleClick {
            onBackPressed()
        }
    }

    override fun onBackPressed() {

        when(fragment){

            is DesignFragment -> {
                val intent = Intent()
                intent.putExtra(COMMON_FLAG,"finish DesignFragment")
                setResult(START_FOR_RESULT,intent)
            }

            is PersistentFragment -> {
                val intent = Intent()
                intent.putExtra(COMMON_FLAG,"finish PersistentFragment")
                setResult(START_FOR_RESULT,intent)
            }
        }
        super.onBackPressed()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        LogUtils.d("__forResult-activity",
            "requestCode:$requestCode resultCode:$resultCode data:${data?.getStringExtra(COMMON_FLAG)}")
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun updateTime(time: String) {

    }
}