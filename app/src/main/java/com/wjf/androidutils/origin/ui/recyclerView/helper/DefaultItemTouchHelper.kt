package com.wjf.androidutils.origin.ui.recyclerView.helper

import androidx.recyclerview.widget.ItemTouchHelper




/**
 * @Description
 * @Author WuJianFeng
 * @Date 2024/1/6 16:17
 *
 */

class DefaultItemTouchHelper(private val itemTouchHelpCallback: DefaultItemTouchHelpCallback): ItemTouchHelper(itemTouchHelpCallback) {


    /**
     * 设置是否可以被拖拽
     *
     * @param canDrag 是true，否false
     */
    fun setDragEnable(canDrag: Boolean) {
        itemTouchHelpCallback.setDragEnable(canDrag)
    }

    /**
     * 设置是否可以被滑动
     *
     * @param canSwipe 是true，否false
     */
    fun setSwipeEnable(canSwipe: Boolean) {
        itemTouchHelpCallback.setSwipeEnable(canSwipe)
    }
}