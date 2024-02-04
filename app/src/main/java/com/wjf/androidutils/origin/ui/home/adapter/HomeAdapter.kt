package com.wjf.androidutils.origin.ui.home.adapter

import android.app.Activity
import android.content.Intent
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wjf.androidutils.R
import com.wjf.androidutils.compose.ui.ComposeActivity
import com.wjf.androidutils.origin.base.transit.TitleBarActivity
import com.wjf.androidutils.origin.utils.JUMP_TO
import com.wjf.androidutils.origin.utils.JumpSealed
import com.wjf.androidutils.origin.utils.START_FOR_RESULT
import com.wjf.moduleutils.singleClick
import java.util.LinkedList
import kotlin.reflect.full.isSubclassOf

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2024/1/5 8:25
 *
 */
class HomeAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val dataList = LinkedList<JumpSealed>()
    init {

        JumpSealed::class.sealedSubclasses.forEach {
            if (it.isSubclassOf(JumpSealed::class)){
                if (it.objectInstance != null && it.objectInstance!!.showInHome){
                    dataList.add(it.objectInstance!!)
                }
            }
        }
    }


    lateinit var mView: View
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        mView = LayoutInflater.from(parent.context).inflate(R.layout.layout_text_view,parent,false)
        return HomeViewHolder(mView)
    }

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is HomeViewHolder -> {
                holder.tvItem.text = dataList[position].jumpName
            }
        }
    }

    inner class HomeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tvItem: TextView = itemView.findViewById(R.id.tv_item)
        init {
            itemView.singleClick {
                when(dataList[adapterPosition].jumpTag){
                    JumpSealed.Compose.jumpTag  -> { ComposeActivity.newInstance(mView.context) }
                    JumpSealed.Design.jumpTag   -> {
                        val intent = Intent(mView.context, TitleBarActivity::class.java)
                        intent.putExtra(JUMP_TO,JumpSealed.Design.jumpTag)
                        (mView.context as Activity).startActivityForResult(intent,START_FOR_RESULT)
                    }
                    JumpSealed.Persistent.jumpTag   -> {
                        val intent = Intent(mView.context, TitleBarActivity::class.java)
                        intent.putExtra(JUMP_TO, JumpSealed.Persistent.jumpTag)
                        ((mView.context as TitleBarActivity).fragment).startActivityForResult(intent,START_FOR_RESULT)
                    }
                    else          -> { TitleBarActivity.newInstance(mView.context, dataList[adapterPosition].jumpTag) }
                }
            }
        }
    }
}