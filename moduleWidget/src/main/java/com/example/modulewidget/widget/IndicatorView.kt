package com.example.modulewidget.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.wjf.moduleWidget.R

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/11/24 10:59
 *
 * viewpage指示器控件
 */

class IndicatorView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {

    // 指示器容器 宽/高
    private var mIndicatorWidth = 0
    private var mIndicatorHeight = 0

    // 指示器item 宽/高
    private var mItemWidth = 0
    private var mItemHeight = 0

    // 指示器item的间隔
    private var mIndicatorItemDistance = 0

    // 指示器item的个数
    private var mIndicatorItemCount = 0

    // 首个item的起点
    private var mStartPos = 0f

    // item画笔 选中态/未选中态
    private val mSelectedPaint: Paint = Paint()
    private val mUnSelectedPaint: Paint = Paint()

    // item画笔颜色 选中态/未选中态
    private var mColorSelected = Color.WHITE
    private var mColorUnSelected = Color.GRAY

    // 当前选中的位置
    private var mCurrentSelectedPosition = 0

    // item是否为圆点
    private var isCircle = true

    init {
        // 自定义属性
        val a = context.obtainStyledAttributes(attrs, R.styleable.IndicatorView)
        mColorSelected = a.getColor(R.styleable.IndicatorView_colorSelected, Color.WHITE)
        mColorUnSelected = a.getColor(R.styleable.IndicatorView_colorUnSelected, Color.GRAY)
        a.recycle()

        // 配置paint画笔
        mSelectedPaint.apply {
            style = Paint.Style.FILL
            isAntiAlias = true
            color = mColorSelected
        }

        mUnSelectedPaint.apply {
            style = Paint.Style.FILL
            isAntiAlias = true
            color = mColorUnSelected
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mIndicatorWidth = MeasureSpec.getSize(widthMeasureSpec)
        mIndicatorHeight = MeasureSpec.getSize(heightMeasureSpec)
        // item宽度 = 指示器宽度 / (item个数 + 间隔个数)
        mItemWidth = mIndicatorWidth.div(mIndicatorItemCount + mIndicatorItemCount - 1)
        // item高度 = item宽度和指示器高度中的小值，避免绘制不全
        mItemHeight = mItemWidth.coerceAtMost(mIndicatorHeight)
        // 绘制item的起始位置 = 指示器宽度/2 - 绘制区域/2，保持绘制区域居中显示
        mStartPos =
            mIndicatorWidth.div(2f) - ((mIndicatorItemCount + mIndicatorItemCount - 1) * mItemHeight).div(
                2f
            )
        // 不需要改变原控件大小，此处不需要重绘
//        setMeasuredDimension(mIndicatorWidth, mIndicatorHeight)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val dy = mIndicatorHeight.div(2f)
        // 圆半径
        val cr = mItemHeight.div(2f)
        for (i in 0 until mIndicatorItemCount) {
            // 指示器为圆形
            mIndicatorItemDistance = mItemHeight
            // 动态计算每个item的起始绘制位置
            val dx = mStartPos + i * mItemHeight + i * mIndicatorItemDistance + cr
            // item选中态在大小和颜色上有所不同
            canvas.drawCircle(
                dx,
                dy,
                if (i == mCurrentSelectedPosition) cr else cr.div(1.5f),
                if (i == mCurrentSelectedPosition) mSelectedPaint else mUnSelectedPaint
            )
        }
    }

    /**
     * 控制指示器显示隐藏
     */
    private fun indicatorVisibility() {
        if (mCurrentSelectedPosition >= mIndicatorItemCount) {
            mCurrentSelectedPosition = mIndicatorItemCount - 1
        }
        // 小于1个不显示
        visibility = if (mIndicatorItemCount <= 1) GONE else VISIBLE
    }

    /**
     * 设置指示器item个数
     */
    fun setIndicatorItemCount(count: Int) {
        mIndicatorItemCount = count
        indicatorVisibility()
    }

    /**
     * 设置当前位置
     */
    fun setCurrentSelectedPosition(pos: Int) {
        this.mCurrentSelectedPosition = pos
    }
}
