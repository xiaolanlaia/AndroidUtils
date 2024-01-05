package com.wjf.androidutils.origin.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wjf.androidutils.R
import com.wjf.androidutils.compose.ui.ComposeActivity
import com.wjf.androidutils.origin.base.transit.TitleBarActivity
import com.wjf.androidutils.origin.utils.ITEM_COMPOSE
import com.wjf.androidutils.origin.utils.JumpPageKey
import com.wjf.moduleutils.singleClick

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2024/1/5 8:25
 *
 */
class HomeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var dataList = ArrayList<String>().apply {

        JumpPageKey.forEach {
            if (it.value.isShow){
                add(it.key)
            }
        }
    }
    lateinit var mView: View
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        mView = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_home,parent,false)
        return HomeViewHolder(mView)
    }

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is HomeViewHolder -> {
                holder.tvItem.text = dataList[position]
                holder.tvItem.singleClick {
                    when(dataList[position]){
                        ITEM_COMPOSE  -> { ComposeActivity.newInstance(mView.context) }
                        else          -> { TitleBarActivity.newInstance(mView.context, "${JumpPageKey[dataList[position]]?.jumpFlag}") }
                    }
                }
            }
        }
    }

    inner class HomeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tvItem: TextView = itemView.findViewById(R.id.tv_item)
    }


}