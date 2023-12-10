package com.example.modulewidget

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import android.util.AttributeSet
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import androidx.vectordrawable.graphics.drawable.ArgbEvaluator

class RoundMenu(context: Context?, attrs: AttributeSet?) : ViewGroup(context, attrs) {

    private lateinit var center : Point
    private var inPaint : Paint
    private var outPaint : Paint
    private var inPaintColor = Color.parseColor("#ffff8800")
    private var outPainColor = Color.parseColor("#ffffbb33")
    private var expandProgress = 0

    private lateinit var expendAnim : ValueAnimator
    private lateinit var colorAnim : ValueAnimator

    init {
        //降低图片放大时的颗粒感
        inPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        inPaint.color = inPaintColor
        inPaint.style = Paint.Style.FILL

        outPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        outPaint.color = outPainColor
        outPaint.style = Paint.Style.FILL

        //开启onDraw
        setWillNotDraw(false)

        initAnim()

    }


    @SuppressLint("RestrictedApi")
    private fun initAnim(){

        expendAnim = ValueAnimator.ofFloat(0f,1f)
        expendAnim.interpolator = OvershootInterpolator()
        expendAnim.duration = 400
        expendAnim.addUpdateListener {
            expandProgress = it.animatedValue as Int
            outPaint.alpha = expandProgress * 255
            invalidate()
        }

        colorAnim = ValueAnimator.ofObject(ArgbEvaluator(),outPainColor,inPaintColor)
        colorAnim.duration = 400
        colorAnim.addUpdateListener {
            inPaint.color = it.animatedValue as Int
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (expandProgress > 0){
            canvas.drawCircle(center.x.toFloat(), center.y.toFloat(), (22 + (84 - 22) * expandProgress).toFloat(), outPaint)
        }
    }

    fun startAnim(){
        expendAnim.start()
        colorAnim.start()
    }


    override fun onLayout(p0: Boolean, p1: Int, p2: Int, p3: Int, p4: Int) {

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        center.set(w / 2, h / 2)
    }
}