package com.wjf.androidutils.origin.ui.service

import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.wjf.androidutils.databinding.FragmentServiceBindBinding
import com.wjf.androidutils.origin.base.MVVMBaseFragment
import com.wjf.androidutils.origin.service.MyBindService
import com.wjf.androidutils.origin.ui.home.HomeViewModel
import com.wjf.moduleutils.singleClick

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2024/1/5 17:13
 *
 */

class ServiceBindFragment : MVVMBaseFragment<HomeViewModel, FragmentServiceBindBinding>() {

    override fun initViewModel() = ViewModelProviders.of(this).get(HomeViewModel::class.java)

    override fun initViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentServiceBindBinding.inflate(inflater,container,false)

    override fun initView() {}

    override fun initClick() {

        binding.tvBind.singleClick {
            mView.context.bindService(
                Intent(mView.context, MyBindService::class.java),
                bindConnection,
                Service.BIND_AUTO_CREATE
            )
        }

        binding.tvUnbind.singleClick {
            if (myBindService != null){
                mView.context.unbindService(bindConnection)
                myBindService = null
            }
        }

        binding.tvBindGet.singleClick {
            binding.tvBindGet.text = "${binding.tvBindGet.text}${myBindService?.getContent()}"
        }
    }


    var myBindService: MyBindService? = null
    private val bindConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            //绑定成功
            val myBinder = service as MyBindService.MyBinder
            myBindService = myBinder.getService()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            //系统与服务通信异常中断时调用
            myBindService = null
        }
    }
}