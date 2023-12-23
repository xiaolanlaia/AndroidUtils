package com.wjf.androidutils.origin.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.wjf.androidutils.origin.base.MVVMBaseFragment
import com.wjf.androidutils.databinding.FragmentPersistentBinding
import com.wjf.androidutils.origin.ui.home.HomeViewModel
import com.wjf.moduleutils.persistent.DataStoreUtils
import com.wjf.moduleutils.persistent.MMKVUtils
import com.wjf.moduleutils.persistent.SPUtils

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/11/30 8:20
 *
 */

class PersistentFragment : MVVMBaseFragment<HomeViewModel, FragmentPersistentBinding>(), View.OnClickListener {

    override fun initViewModel() = ViewModelProviders.of(this).get(HomeViewModel::class.java)

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
            binding.btnSpSet -> { SPUtils.instance.commit(key,1)}
            binding.btnSpGet -> { binding.tvSpShow.text = SPUtils.instance.getInt(key).toString()}
            binding.btnDatastoreSet -> { DataStoreUtils.instance.putValue(key,2)}
            binding.btnDatastoreGet -> { DataStoreUtils.instance.getInt(key){ binding.tvDatastoreShow.text = it.toString()}}
            binding.btnMmkvSet -> { MMKVUtils.instance.putValue(key,3)}
            binding.btnMmkvGet -> { binding.tvMmkvShow.text = MMKVUtils.instance.getInt(key).toString()}
        }
    }
}