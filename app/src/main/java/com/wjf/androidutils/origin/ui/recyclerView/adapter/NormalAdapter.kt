package com.wjf.androidutils.origin.ui.recyclerView.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wjf.androidutils.R
import com.wjf.androidutils.origin.base.transit.TitleBarActivity
import com.wjf.androidutils.origin.utils.JUMP_TO_RecyclerViewMultiFragment
import com.wjf.moduleutils.singleClick

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2024/1/6 9:52
 *
 */

class NormalAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object{
        const val ITEM_1 = "多布局"
        const val ITEM_2 = "横向滚动"
        const val ITEM_3 = "局部刷新"
        const val ITEM_4 = "添加删除"
        const val ITEM_5 = "拖拽排序"
        const val ITEM_6 = "粘性标题"
        const val ITEM_7 = "顶格标题"
    }
    var dataList = ArrayList<String>().apply {
        add(ITEM_1)
        add(ITEM_2)
        add(ITEM_3)
        add(ITEM_4)
        add(ITEM_5)
        add(ITEM_6)
        add(ITEM_7)
    }
    private lateinit var mView: View

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        mView = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_home,parent,false)
        return NormalViewHolder(mView)
    }

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when(holder){
            is NormalViewHolder -> {
                holder.tvItem.text = dataList[position]
            }
        }
    }

    inner class NormalViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tvItem: TextView = itemView.findViewById(R.id.tv_item)
        init {
            tvItem.singleClick {
                when(dataList[adapterPosition]){

                    ITEM_1 -> { TitleBarActivity.newInstance(itemView.context, JUMP_TO_RecyclerViewMultiFragment) }

                    ITEM_2 -> {

                    }
                    ITEM_3 -> {

                    }
                    ITEM_4 -> {

                    }
                    ITEM_5 -> {

                    }
                    ITEM_6 -> {

                    }
                    ITEM_7 -> {

                    }
                }
            }
        }
    }
}