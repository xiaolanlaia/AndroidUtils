package com.wjf.androidutils.origin.ui.home.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.wjf.androidutils.R
import com.wjf.androidutils.compose.ui.ComposeActivity
import com.wjf.androidutils.origin.base.transit.TitleBarActivity
import com.wjf.androidutils.origin.utils.ITEM_COMPOSE
import com.wjf.androidutils.origin.utils.ITEM_DESIGN
import com.wjf.androidutils.origin.utils.ITEM_PERSISTENT
import com.wjf.androidutils.origin.utils.JUMP_TO
import com.wjf.androidutils.origin.utils.JUMP_TO_DesignFragment
import com.wjf.androidutils.origin.utils.JUMP_TO_PersistentFragment
import com.wjf.androidutils.origin.utils.JumpPageKey
import com.wjf.androidutils.origin.utils.START_FOR_RESULT
import com.wjf.moduleutils.singleClick
import java.util.LinkedList

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2024/1/5 8:25
 *
 */
class HomeAdapter(private val dataList: LinkedList<String> = LinkedList(JumpPageKey.keys)) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    lateinit var mView: View
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        mView = LayoutInflater.from(parent.context).inflate(R.layout.layout_text_view,parent,false)
        return HomeViewHolder(mView)
    }

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is HomeViewHolder -> {
                holder.tvItem.text = dataList[position]
            }
        }
    }

    inner class HomeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tvItem: TextView = itemView.findViewById(R.id.tv_item)
        init {
            itemView.singleClick {
                when(dataList[adapterPosition]){
                    ITEM_COMPOSE  -> { ComposeActivity.newInstance(mView.context) }
                    ITEM_DESIGN   -> {
                        val intent = Intent(mView.context, TitleBarActivity::class.java)
                        intent.putExtra(JUMP_TO,JUMP_TO_DesignFragment)
                        (mView.context as Activity).startActivityForResult(intent,START_FOR_RESULT)
                    }
                    ITEM_PERSISTENT   -> {
                        val intent = Intent(mView.context, TitleBarActivity::class.java)
                        intent.putExtra(JUMP_TO, JUMP_TO_PersistentFragment)
                        ((mView.context as TitleBarActivity).fragment).startActivityForResult(intent,START_FOR_RESULT)
                    }
                    else          -> { TitleBarActivity.newInstance(mView.context, "${JumpPageKey[dataList[adapterPosition]]?.jumpFlag}") }
                }
            }
        }
    }


}