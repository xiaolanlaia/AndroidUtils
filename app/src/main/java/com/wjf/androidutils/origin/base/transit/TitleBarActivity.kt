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
import com.wjf.androidutils.databinding.CommonTitleBinding
import com.wjf.androidutils.origin.ui.AnimFragment
import com.wjf.androidutils.origin.ui.ArrayFragment
import com.wjf.androidutils.origin.ui.ConstraintLayoutFragment
import com.wjf.androidutils.origin.ui.DesignFragment
import com.wjf.androidutils.origin.ui.ExceptionFragment
import com.wjf.androidutils.origin.ui.FileFragment
import com.wjf.androidutils.origin.ui.ImgLoaderFragment
import com.wjf.androidutils.origin.ui.PersistentFragment
import com.wjf.androidutils.origin.ui.ScreenFragment
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
import com.wjf.androidutils.origin.ui.reflect.ReflectFragment
import com.wjf.androidutils.origin.ui.socket.SelectTypeFragment
import com.wjf.androidutils.origin.ui.socket.SocketClientFragment
import com.wjf.androidutils.origin.ui.socket.SocketServiceFragment
import com.wjf.androidutils.origin.utils.JUMP_TO
import com.wjf.androidutils.origin.utils.JUMP_TO_AnimFragment
import com.wjf.androidutils.origin.utils.JUMP_TO_ArrayFragment
import com.wjf.androidutils.origin.utils.JUMP_TO_BleClientFragment
import com.wjf.androidutils.origin.utils.JUMP_TO_BleServiceFragment
import com.wjf.androidutils.origin.utils.JUMP_TO_BlueClientFragment
import com.wjf.androidutils.origin.utils.JUMP_TO_BlueFragment
import com.wjf.androidutils.origin.utils.JUMP_TO_BlueServiceFragment
import com.wjf.androidutils.origin.utils.JUMP_TO_ConstraintLayoutFragment
import com.wjf.androidutils.origin.utils.JUMP_TO_DesignFragment
import com.wjf.androidutils.origin.utils.JUMP_TO_DialogFragment
import com.wjf.androidutils.origin.utils.JUMP_TO_ExceptionFragment
import com.wjf.androidutils.origin.utils.JUMP_TO_FileFragment
import com.wjf.androidutils.origin.utils.JUMP_TO_HiltFragment
import com.wjf.androidutils.origin.utils.JUMP_TO_ImgLoaderFragment
import com.wjf.androidutils.origin.utils.JUMP_TO_PersistentFragment
import com.wjf.androidutils.origin.utils.JUMP_TO_ReflectFragment
import com.wjf.androidutils.origin.utils.JUMP_TO_ScreenFragment
import com.wjf.androidutils.origin.utils.JUMP_TO_SelectTypeFragment
import com.wjf.androidutils.origin.utils.JUMP_TO_SocketClientFragment
import com.wjf.androidutils.origin.utils.JUMP_TO_SocketServiceFragment
import com.wjf.androidutils.origin.utils.JUMP_TO_ToastFragment
import com.wjf.androidutils.origin.utils.JUMP_TO_ViewPageFragment
import com.wjf.androidutils.origin.utils.JUMP_TO_WebViewFragment
import com.wjf.moduleutils.PermissionUtil
import com.wjf.moduleutils.ScreenUtils
import com.wjf.moduleutils.singleClick
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TitleBarActivity : MVVMBaseActivity<TitleBarViewModel, ActivityTitleBarBinding>() {


    companion object{
        fun newInstance(context: Context, jumpTo: String){
            val intent = Intent(context, TitleBarActivity::class.java)
            intent.putExtra(JUMP_TO,jumpTo)
            context.startActivity(intent)
        }
    }

    //首先定义请求变量
    private var permissionList = ArrayList<String>().apply {
        add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        add(Manifest.permission.READ_PHONE_STATE)
    }

    //请求状态码，请求码的作用是与回调函数进行匹配的，这样就可以对不同权限操作进行不同的提示
    val REQUEST_CODE = 1

    lateinit var fragment: Fragment
    lateinit var commonTitleBinding: CommonTitleBinding

    override fun initViewBinding(inflater: LayoutInflater) = ActivityTitleBarBinding.inflate(layoutInflater)


    override fun initViewModel() = ViewModelProviders.of(this).get(TitleBarViewModel::class.java)

    override fun initView(){
        commonTitleBinding = CommonTitleBinding.bind(binding.root)
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

            JUMP_TO_DesignFragment -> { DesignFragment() }

            JUMP_TO_PersistentFragment -> { PersistentFragment() }

            JUMP_TO_ToastFragment -> { ToastFragment() }

            JUMP_TO_ArrayFragment -> { ArrayFragment() }

            JUMP_TO_FileFragment -> { FileFragment() }

            JUMP_TO_ExceptionFragment -> { ExceptionFragment() }

            JUMP_TO_ReflectFragment -> { ReflectFragment() }

            JUMP_TO_ImgLoaderFragment -> { ImgLoaderFragment() }

            JUMP_TO_WebViewFragment -> { WebViewFragment() }

            JUMP_TO_AnimFragment -> { AnimFragment() }

            JUMP_TO_BlueFragment -> { BlueFragment() }

            JUMP_TO_BlueClientFragment -> { BlueClientFragment() }

            JUMP_TO_BlueServiceFragment -> { BlueServiceFragment() }

            JUMP_TO_BleClientFragment -> { BleClientFragment() }

            JUMP_TO_BleServiceFragment -> { BleServiceFragment() }

            JUMP_TO_DialogFragment -> { DialogFragment() }

            JUMP_TO_SelectTypeFragment -> { SelectTypeFragment() }

            JUMP_TO_SocketClientFragment -> { SocketClientFragment() }

            JUMP_TO_SocketServiceFragment -> { SocketServiceFragment() }

            JUMP_TO_ScreenFragment -> { ScreenFragment() }

            JUMP_TO_HiltFragment -> { HiltFragment() }

            JUMP_TO_ConstraintLayoutFragment -> { ConstraintLayoutFragment() }

            JUMP_TO_ViewPageFragment -> { ViewPageFragment() }

            else -> { HomeFragment() }
        }

        commonTitleBinding.tvPageTitle.text = fragment.javaClass.simpleName
        supportFragmentManager.beginTransaction().replace(R.id.info_content, fragment).commit()

    }

    override fun initClick(){
        commonTitleBinding.linearBack.singleClick { finish() }
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}