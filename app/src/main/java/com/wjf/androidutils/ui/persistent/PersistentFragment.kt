package com.wjf.androidutils.ui.persistent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.wjf.androidutils.base.MVVMBaseFragment
import com.wjf.androidutils.databinding.FragmentPersistentBinding
import com.wjf.androidutils.utils.persistent.DataStoreUtils
import com.wjf.androidutils.utils.persistent.MMKVUtils
import com.wjf.androidutils.utils.persistent.SPUtils

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/11/30 8:20
 *
 */

class PersistentFragment : MVVMBaseFragment<PersistentViewModel,FragmentPersistentBinding>(), View.OnClickListener {

    override fun initViewModel() = ViewModelProviders.of(this).get(PersistentViewModel::class.java)

    override fun initViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentPersistentBinding {

        return FragmentPersistentBinding.inflate(inflater,container,false)
    }

    override fun initView() {
        binding.btnSpSet.setOnClickListener(this)
        binding.btnSpGet.setOnClickListener(this)
        binding.btnDatastoreSet.setOnClickListener(this)
        binding.btnDatastoreGet.setOnClickListener(this)
        binding.btnMmkvSet.setOnClickListener(this)
        binding.btnMmkvGet.setOnClickListener(this)
    }

    val key = "test"
    override fun onClick(v: View?) {
        when(v){
            binding.btnSpSet -> { SPUtils.commit(key,1)}
            binding.btnSpGet -> { binding.tvSpShow.text = SPUtils.getInt(key).toString()}
            binding.btnDatastoreSet -> { DataStoreUtils.putValue(key,2)}
            binding.btnDatastoreGet -> { DataStoreUtils.getInt(key){ binding.tvDatastoreShow.text = it.toString()}}
            binding.btnMmkvSet -> { MMKVUtils.putValue(key,3)}
            binding.btnMmkvGet -> { binding.tvMmkvShow.text = MMKVUtils.getInt(key).toString()}
        }
    }
}