package com.wjf.androidutils.origin.ui.recyclerView

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.wjf.androidutils.databinding.FragmentRecyclerViewBinding
import com.wjf.androidutils.origin.base.MVVMBaseFragment
import com.wjf.androidutils.origin.ui.home.HomeViewModel
import com.wjf.androidutils.origin.ui.recyclerView.adapter.MultiAdapter
import com.wjf.androidutils.origin.ui.recyclerView.helper.DefaultItemTouchHelpCallback
import com.wjf.androidutils.origin.ui.recyclerView.helper.DefaultItemTouchHelper
import com.wjf.androidutils.origin.utils.JumpSealed
import java.util.Collections
import java.util.LinkedList
import kotlin.reflect.full.isSubclassOf

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2024/1/6 9:49
 *
 */

class RecyclerViewMultiFragment : MVVMBaseFragment<HomeViewModel, FragmentRecyclerViewBinding>() {

    override fun initViewModel() = ViewModelProviders.of(this).get(HomeViewModel::class.java)

    override fun initViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentRecyclerViewBinding.inflate(inflater,container,false)

    override fun initView() {
        val gridLayoutManager = GridLayoutManager(mView.context,3)
        binding.rvTest.layoutManager = gridLayoutManager
        binding.rvTest.setHasFixedSize(true)
        val dataList = LinkedList<String>()
        JumpSealed::class.sealedSubclasses.forEach {
            if (it.isSubclassOf(JumpSealed::class)){
                if (!TextUtils.isEmpty(it.objectInstance?.jumpTag) && it.objectInstance!!.showInHome){
                    dataList.add(it.objectInstance!!.jumpName)
                }
            }
        }
        val adapter = MultiAdapter(dataList)
        binding.rvTest.adapter = adapter

        val defaultItemTouchHelpCallback = DefaultItemTouchHelpCallback(object : DefaultItemTouchHelpCallback.OnItemTouchCallbackListener{
            override fun onSwiped(adapterPosition: Int) {
                adapter.notifyItemRemoved(adapterPosition)
                dataList.removeAt(adapterPosition)
            }

            override fun onMove(srcPosition: Int, targetPosition: Int): Boolean {
                // 更新UI中的Item的位置，主要是给用户看到交互效果
                adapter.notifyItemMoved(srcPosition, targetPosition)
                // 更换数据源中的数据Item的位置
                Collections.swap(dataList, srcPosition, targetPosition)
                return true
            }

        })
        val itemTouchHelper = DefaultItemTouchHelper(defaultItemTouchHelpCallback)
        itemTouchHelper.attachToRecyclerView(binding.rvTest)
        itemTouchHelper.setDragEnable(true)
        itemTouchHelper.setSwipeEnable(true)
    }
}