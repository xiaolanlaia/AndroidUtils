package com.wjf.androidutils.origin.ui.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.wjf.androidutils.databinding.FragmentRecyclerViewSelectBinding
import com.wjf.androidutils.origin.base.MVVMBaseFragment
import com.wjf.androidutils.origin.ui.home.HomeViewModel
import com.wjf.androidutils.origin.ui.recyclerView.adapter.NormalAdapter

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2024/1/6 15:13
 *
 */

class RecyclerViewSelectFragment : MVVMBaseFragment<HomeViewModel, FragmentRecyclerViewSelectBinding>() {

    override fun initViewModel() = ViewModelProviders.of(this).get(HomeViewModel::class.java)

    override fun initViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentRecyclerViewSelectBinding.inflate(inflater,container,false)

    override fun initView() {
        binding.rvSelect.layoutManager = GridLayoutManager(mView.context,4)
        binding.rvSelect.setHasFixedSize(true)
        val adapter = NormalAdapter()
        binding.rvSelect.adapter = adapter
    }
}