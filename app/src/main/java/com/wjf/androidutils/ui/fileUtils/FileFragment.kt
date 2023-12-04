package com.wjf.androidutils.ui.fileUtils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.wjf.androidutils.base.MVVMBaseFragment
import com.wjf.androidutils.databinding.FragmentFileBinding
import com.wjf.androidutils.utils.FILE_TYPE_2
import com.wjf.androidutils.utils.FileUtils
import com.wjf.androidutils.utils.LogUtils

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/1 14:14
 *
 */

class FileFragment : MVVMBaseFragment<FileViewModel,FragmentFileBinding>() {

    override fun initViewModel() = ViewModelProviders.of(this).get(FileViewModel::class.java)

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
    }

}