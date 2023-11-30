package com.wjf.androidutils.ui.persistent

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import com.wjf.androidutils.R
import com.wjf.androidutils.base.MVVMBaseFragment
import com.wjf.androidutils.utils.persistent.DataStoreUtils
import com.wjf.androidutils.utils.persistent.MMKVUtils
import com.wjf.androidutils.utils.persistent.SPUtils

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/11/30 8:20
 *
 */

class PersistentFragment : MVVMBaseFragment<PersistentViewModel>(), View.OnClickListener {
    lateinit var btnSpSet: Button
    lateinit var btnSpGet: Button
    lateinit var tvSpShow: TextView
    lateinit var btnDatastoreSet: Button
    lateinit var btnDatastoreGet: Button
    lateinit var tvDatastoreShow: TextView
    lateinit var btnMmkvSet: Button
    lateinit var btnMmkvGet: Button
    lateinit var tvMmkvShow: TextView
    override fun initViewModel() = ViewModelProviders.of(this).get(PersistentViewModel::class.java)

    override fun initContentViewID() = R.layout.fragment_persistent

    override fun initView() {
        btnSpSet = mView.findViewById(R.id.btn_sp_set)
        btnSpGet = mView.findViewById(R.id.btn_sp_get)
        tvSpShow = mView.findViewById(R.id.tv_sp_show)
        btnDatastoreSet = mView.findViewById(R.id.btn_datastore_set)
        btnDatastoreGet = mView.findViewById(R.id.btn_datastore_get)
        tvDatastoreShow = mView.findViewById(R.id.tv_datastore_show)
        btnMmkvSet = mView.findViewById(R.id.btn_mmkv_set)
        btnMmkvGet = mView.findViewById(R.id.btn_mmkv_get)
        tvMmkvShow = mView.findViewById(R.id.tv_mmkv_show)
        btnSpSet.setOnClickListener(this)
        btnSpGet.setOnClickListener(this)
        btnDatastoreSet.setOnClickListener(this)
        btnDatastoreGet.setOnClickListener(this)
        btnMmkvSet.setOnClickListener(this)
        btnMmkvGet.setOnClickListener(this)
    }

    val key = "test"
    override fun onClick(v: View?) {
        when(v){
            btnSpSet -> { SPUtils.commit(key,1)}
            btnSpGet -> { tvSpShow.text = SPUtils.getInt(key).toString()}
            btnDatastoreSet -> { DataStoreUtils.putValue(key,2)}
            btnDatastoreGet -> { DataStoreUtils.getInt(key){ tvDatastoreShow.text = it.toString()}}
            btnMmkvSet -> { MMKVUtils.putValue(key,3)}
            btnMmkvGet -> { tvMmkvShow.text = MMKVUtils.getInt(key).toString()}
        }
    }
}