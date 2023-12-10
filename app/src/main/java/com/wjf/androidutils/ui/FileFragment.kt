package com.wjf.androidutils.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.wjf.androidutils.base.MVVMBaseFragment
import com.wjf.androidutils.databinding.FragmentFileBinding
import com.wjf.androidutils.ui.home.HomeViewModel
import com.wjf.moduleutils.FILE_TYPE_2
import com.wjf.moduleutils.FileUtils
import com.wjf.moduleutils.LogUtils

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/1 14:14
 *
 */

class FileFragment : MVVMBaseFragment<HomeViewModel,FragmentFileBinding>() {

    override fun initViewModel() = ViewModelProviders.of(this).get(HomeViewModel::class.java)

    override fun initViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentFileBinding {
        return FragmentFileBinding.inflate(inflater,container,false)
    }
    override fun initView() {
    }

    override fun initClick() {

        binding.btnInByte.setOnClickListener {
            val byteArray = byteArrayOf(1,2,3,4,5,6,7,89)
            FileUtils.writeByteArray2Txt(byteArray,"ByteArray","ByteArray.txt", FILE_TYPE_2)
        }

        binding.btnOutByte.setOnClickListener {
            val byteArray = FileUtils.readByteArrayFromTxt("ByteArray","ByteArray.txt", FILE_TYPE_2)
        }

        binding.btnDeleteFolder.setOnClickListener {
            val folderPath = FileUtils.getFolderPath(
                folderName = "SocketImg/005",
                fileType = FILE_TYPE_2
            )
            LogUtils.d("__delete-path",folderPath)

            FileUtils.deleteFolder(context = it.context, folderPath = folderPath){
                LogUtils.d("__delete-result","${it}")

            }
        }

        binding.btnPath2Uri.setOnClickListener {
            val uri = FileUtils.path2Uri(FileUtils.getFolderPath("SocketImg/002/success/0.jpeg", FILE_TYPE_2))
            LogUtils.d("__path2Uri","path = ${uri?.path}")
        }
    }

}