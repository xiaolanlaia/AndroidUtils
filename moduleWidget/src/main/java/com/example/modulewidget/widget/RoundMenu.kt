package com.example.modulewidget.widget

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Outline
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PathMeasure
import android.graphics.Point
import android.graphics.Rect
import android.graphics.RectF
import android.os.Build
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.view.animation.OvershootInterpolator
import androidx.vectordrawable.graphics.drawable.ArgbEvaluator
import com.example.modulewidget.utils.WidgetUtils
import com.wjf.androidutils.R
import com.wjf.moduleutils.LogUtils

class RoundMenu(context: Context?, attrs: AttributeSet?) : ViewGroup(context, attrs) {

    private var centerPoint : Point
    private var inPaint : Paint
    private var outPaint : Paint
    private var inPaintColor = Color.parseColor("#ffff8800")
    private var outPainColor = Color.parseColor("#ffffbb33")
    private var expandProgress = 0f

    private lateinit var expendAnim : ValueAnimator
    private lateinit var colorAnim : ValueAnimator

    //收缩时半径
    private var collapsedRadius = resources.getDimension(R.dimen.dp22)
    //展开时半径
    private var expandedRadius = resources.getDimension(R.dimen.dp84)
    //展开状态
    private var expandState = false

    private var animDuration = 400L

    //子项的宽高
    private var itemSize = resources.getDimension(R.dimen.dp22)

    //子View之间的动画间隔
    private var mItemAnimIntervalTime = 50L

    private var mCenterDrawable = resources.getDrawable(R.mipmap.ic_menu,null)

    init {
        //降低图片放大时的颗粒感
        inPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        inPaint.color = outPainColor
        inPaint.style = Paint.Style.FILL

        outPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        outPaint.color = outPainColor
        outPaint.style = Paint.Style.FILL

        //开启onDraw
        setWillNotDraw(false)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            outlineProvider = OvalOutline()
            elevation = resources.getDimension(R.dimen.dp5)

        }
        centerPoint = Point()

        initAnim()

    }


    @SuppressLint("RestrictedApi")
    private fun initAnim(){

        expendAnim = ValueAnimator.ofFloat(0f,1f)
        expendAnim.interpolator = OvershootInterpolator()
        expendAnim.duration = animDuration
        expendAnim.addUpdateListener {
            expandProgress = it.animatedValue as Float
            outPaint.alpha = 255.coerceAtMost((expandProgress * 255).toInt())
            invalidate()
        }

        colorAnim = ValueAnimator.ofObject(ArgbEvaluator(),outPainColor,inPaintColor)
        colorAnim.duration = animDuration
        colorAnim.addUpdateListener {
            inPaint.color = it.animatedValue as Int
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        //绘制外圈圆
        if (expandProgress > 0){
            canvas.drawCircle(centerPoint.x.toFloat(), centerPoint.y.toFloat(), (collapsedRadius + (expandedRadius - collapsedRadius) * expandProgress), outPaint)
        }

        //绘制内圈圆
        canvas.drawCircle(centerPoint.x.toFloat(), centerPoint.y.toFloat(),collapsedRadius + (collapsedRadius *.2f * expandProgress), inPaint)

        //绘制中心图标
        val count = canvas.saveLayer(0f,0f,width.toFloat(),height.toFloat(),null)
        canvas.rotate(45 * expandProgress, centerPoint.x.toFloat(),centerPoint.y.toFloat())
        mCenterDrawable.draw(canvas)
        canvas.restoreToCount(count)

    }


    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {

        if (childCount == 0) return
        calculateMenuItemPosition()
        for (i in 0 until  childCount){
            val item = getChildAt(i)
            item.layout(
                (l + item.x).toInt(),
                (t + item.y).toInt(),
                ((l + item.x) + item.measuredWidth).toInt(),
                (t + item.y + item.measuredHeight).toInt()
            )
        }

    }

    /**
     * 计算子项坐标
     */
    private fun calculateMenuItemPosition(){
        val itemRadius = (expandedRadius - collapsedRadius) / 2 + collapsedRadius

        //获取圆弧的四个点
        val area = RectF(
            (centerPoint.x - itemRadius),
            (centerPoint.y - itemRadius),
            (centerPoint.x + itemRadius),
            (centerPoint.y + itemRadius))

        val path = Path()
        //将指定的圆弧作为轮廓添加到路径中
        path.addArc(area,0f,360f)
        //创建与指定路径对象（已创建并指定）关联的 PathMeasure 对象
        val measure = PathMeasure(path,false)
        //计算每段长度
        val divider = measure.length / childCount

        for(i in 0 until childCount){
            val itemPoint = FloatArray(2)
            //计算位置和切线
            measure.getPosTan(i * divider + divider * 0.5f,itemPoint,null)
            val item = getChildAt(i)
            //计算子项的中心坐标
            item.x = itemPoint[0] - itemSize / 2
            item.y = itemPoint[1] - itemSize / 2
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centerPoint.set(w / 2, h / 2)

        //中心图标padding设为10dp
        mCenterDrawable.setBounds(
            centerPoint.x - (collapsedRadius - resources.getDimension(R.dimen.dp10)).toInt(),
            centerPoint.y - (collapsedRadius - resources.getDimension(R.dimen.dp10)).toInt(),
            centerPoint.x + (collapsedRadius - resources.getDimension(R.dimen.dp10)).toInt(),
            centerPoint.y + (collapsedRadius - resources.getDimension(R.dimen.dp10)).toInt())
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        when(event?.actionMasked){

            MotionEvent.ACTION_DOWN -> {

                val touchPoint = Point(event.x.toInt(),event.y.toInt())
                val distance = WidgetUtils.pointDistance(touchPoint,centerPoint)
                if (distance <= collapsedRadius || expandState && distance > (collapsedRadius + (expandedRadius - collapsedRadius) * expandProgress)){
                    startAnim()
                    return true
                }
                return false

            }

        }
        return super.onTouchEvent(event)
    }

    private fun startAnim(){

        when(expandState) {

            //处于展开状态则收缩
            true -> {

                expendAnim.setFloatValues(expandProgress,0f)
                expendAnim.start()

                colorAnim.setObjectValues(if (colorAnim.animatedValue == null) inPaintColor else colorAnim.animatedValue, outPainColor)
                colorAnim.start()

                var delay = mItemAnimIntervalTime

                for (i in childCount- 1 downTo 0){
                    getChildAt(i).visibility = View.GONE
                    getChildAt(i)
                        .animate()
                        .setStartDelay(delay)
                        .setDuration(animDuration)
                        .scaleX(0f)
                        .scaleY(0f)
                        .alpha(0f)
                        .start()
                    delay += mItemAnimIntervalTime
                }
            }

            //处于收缩状态则展开
            false -> {

                expendAnim.setFloatValues(expandProgress,1f)
                expendAnim.start()

                colorAnim.setObjectValues(if (colorAnim.animatedValue == null) outPainColor else colorAnim.animatedValue, inPaintColor)
                colorAnim.start()


                LogUtils.d("__childCount","$childCount")

                var delay = mItemAnimIntervalTime

                for (i in 0 until  childCount){
                    getChildAt(i).visibility = View.VISIBLE

                    getChildAt(i)
                        .animate()
                        .setStartDelay(delay)
                        .setDuration(animDuration)
                        .alphaBy(0f)
                        .scaleXBy(0f)
                        .scaleYBy(0f)
                        .scaleX(1f)
                        .scaleY(1f)
                        .alpha(1f)
                        .start()
                    delay += mItemAnimIntervalTime
                }
            }
        }

        expandState = !expandState

    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        for (i in 0 until childCount){
            val item = getChildAt(i)
            item.visibility = View.GONE
            item.alpha = 0f
            item.scaleX = 1f
            item.scaleY = 1f

        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)
        setMeasuredDimension(width,height)
        measureChildren(widthMeasureSpec,heightMeasureSpec)
    }

    inner class OvalOutline : ViewOutlineProvider() {
        override fun getOutline(view: View?, outline: Outline?) {
            val radius = (collapsedRadius + (expandedRadius - collapsedRadius) * expandProgress)
            val area = Rect(
                (centerPoint.x - radius).toInt(),
                (centerPoint.y - radius).toInt(),
                (centerPoint.x + radius).toInt(),
                (centerPoint.y + radius).toInt())
            outline?.setRoundRect(area,radius)
        }

    }
}













