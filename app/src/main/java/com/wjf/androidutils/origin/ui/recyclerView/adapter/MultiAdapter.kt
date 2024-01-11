package com.wjf.androidutils.origin.ui.recyclerView.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wjf.androidutils.R
import com.wjf.androidutils.origin.utils.JumpPageKey
import com.wjf.moduleutils.ToastUtils
import com.wjf.moduleutils.singleClick

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2024/1/6 15:35
 *
 */

class MultiAdapter(private var dataList: List<String> = JumpPageKey.keys.toList()) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object{
        const val VIEW_TYPE_NORMAL = 0
        const val VIEW_TYPE_MULTI = 1
    }

    private lateinit var mView: View

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            VIEW_TYPE_NORMAL -> {
                mView = LayoutInflater.from(parent.context).inflate(R.layout.layout_text_view, parent, false)
                NormalViewHolder(mView)
            }
            else -> {
                mView = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_multi, parent, false)
                MultiViewHolder(mView)
            }
        }
    }

    override fun getItemCount() = dataList.size

    override fun getItemViewType(position: Int): Int {
        return when(position % 2){
            0 -> VIEW_TYPE_NORMAL
            else -> VIEW_TYPE_MULTI
        }
    }

    override fun getItemId(position: Int): Long {
        return ("${dataList[position]}$position").hashCode().toLong()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is NormalViewHolder -> {
                holder.tvItem.text = dataList[position]
            }

            is MultiViewHolder -> {
                holder.tvItemMulti.text = dataList[position]
            }
        }
    }

    inner class NormalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvItem: TextView = itemView.findViewById(R.id.tv_item)

        init {
            tvItem.singleClick {
                ToastUtils.instance.show("click:${dataList[adapterPosition]}")
            }
        }
    }

    inner class MultiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvItemMulti: TextView = itemView.findViewById(R.id.tv_item_multi)

        init {
            itemView.singleClick {
                ToastUtils.instance.show("multi click:${dataList[adapterPosition]}")
            }
        }
    }
}