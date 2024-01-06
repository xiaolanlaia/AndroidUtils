package com.wjf.androidutils.origin.ui.recyclerView.helper

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


/**
 * @Description
 * @Author WuJianFeng
 * @Date 2024/1/6 16:18
 *
 */

class DefaultItemTouchHelpCallback(onItemTouchCallbackListener: OnItemTouchCallbackListener?) : ItemTouchHelper.Callback() {
    /**
     * Item操作的回调
     */
    private var onItemTouchCallbackListener: OnItemTouchCallbackListener?

    /**
     * 是否可以拖拽
     */
    private var isCanDrag = false

    /**
     * 是否可以被滑动
     */
    private var isCanSwipe = false

    init {
        this.onItemTouchCallbackListener = onItemTouchCallbackListener
    }

    /**
     * 设置Item操作的回调，去更新UI和数据源
     *
     * @param onItemTouchCallbackListener
     */
    fun setOnItemTouchCallbackListener(onItemTouchCallbackListener: OnItemTouchCallbackListener?) {
        this.onItemTouchCallbackListener = onItemTouchCallbackListener
    }

    /**
     * 设置是否可以被拖拽
     *
     * @param canDrag 是true，否false
     */
    fun setDragEnable(canDrag: Boolean) {
        isCanDrag = canDrag
    }

    /**
     * 设置是否可以被滑动
     *
     * @param canSwipe 是true，否false
     */
    fun setSwipeEnable(canSwipe: Boolean) {
        isCanSwipe = canSwipe
    }

    /**
     * 当Item被长按的时候是否可以被拖拽
     *
     * @return
     */
    override fun isLongPressDragEnabled(): Boolean {
        return isCanDrag
    }

    /**
     * Item是否可以被滑动(H：左右滑动，V：上下滑动)
     *
     * @return
     */
    override fun isItemViewSwipeEnabled(): Boolean {
        return isCanSwipe
    }

    /**
     * 当用户拖拽或者滑动Item的时候需要我们告诉系统滑动或者拖拽的方向
     *
     * @param recyclerView
     * @param viewHolder
     * @return
     */
    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
        val layoutManager = recyclerView.layoutManager

        // flag如果值是0，相当于这个功能被关闭
        var dragFlag = 0
        var swipeFlag = 0
        var orientation = 0
        when(layoutManager){
            is GridLayoutManager -> { orientation = layoutManager.orientation }
            is LinearLayoutManager -> { orientation = layoutManager.orientation }
        }

        when(orientation){
            RecyclerView.HORIZONTAL -> {
                dragFlag = ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
                swipeFlag = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            }
            RecyclerView.VERTICAL -> {
                dragFlag = ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
                swipeFlag = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            }
        }
        return makeMovementFlags(dragFlag, swipeFlag)
    }

    /**
     * 当Item被拖拽的时候被回调
     *
     * @param recyclerView     recyclerView
     * @param srcViewHolder    拖拽的ViewHolder
     * @param targetViewHolder 目的地的viewHolder
     * @return
     */
    override fun onMove(
        recyclerView: RecyclerView,
        srcViewHolder: RecyclerView.ViewHolder,
        targetViewHolder: RecyclerView.ViewHolder): Boolean {

        return if (onItemTouchCallbackListener != null) {
            onItemTouchCallbackListener!!.onMove(
                srcViewHolder.adapterPosition,
                targetViewHolder.adapterPosition
            )
        } else false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        if (onItemTouchCallbackListener != null) {
            onItemTouchCallbackListener!!.onSwiped(viewHolder.adapterPosition)
        }
    }

    interface OnItemTouchCallbackListener {
        /**
         * 当某个Item被滑动删除的时候
         *
         * @param adapterPosition item的position
         */
        fun onSwiped(adapterPosition: Int)

        /**
         * 当两个Item位置互换的时候被回调
         *
         * @param srcPosition    拖拽的item的position
         * @param targetPosition 目的地的Item的position
         * @return 开发者处理了操作应该返回true，开发者没有处理就返回false
         */
        fun onMove(srcPosition: Int, targetPosition: Int): Boolean
    }
}