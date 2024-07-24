package com.example.modulewidget.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Region;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * 圆形摄像头预览控件
 *
 * 支持暂停预览，圆形的摄像头预览
 */
public class CircleSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private static final String TAG = "CircleCameraPreview";

    /**
     * 半径
     */
    private int radius;

    /**
     * 中心点坐标
     */
    private Point centerPoint;

    /**
     * 剪切路径
     */
    private Path clipPath;


    public CircleSurfaceView(Context context) {
        super(context);
        init();
    }

    public CircleSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    @Override
    protected void onAttachedToWindow() {
        this.setZOrderOnTop(false);
        this.setZOrderMediaOverlay(false);
        super.onAttachedToWindow();
    }

    /**
     * 初始化
     */
    private void init() {
        this.setFocusable(true);
        this.setFocusableInTouchMode(true);
        getHolder().addCallback(this);
        clipPath = new Path();
        centerPoint = new Point();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 坐标转换为实际像素
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        // 计算出圆形的中心点
        centerPoint.x = widthSize >> 1;
        centerPoint.y = heightSize >> 1;
        // 计算出最短的边的一半作为半径
        radius = ( centerPoint.x > centerPoint.y) ? centerPoint.y : centerPoint.x;
        Log.i(TAG, "onMeasure: " + centerPoint.toString());
        clipPath.reset();
        clipPath.addCircle(centerPoint.x, centerPoint.y, radius, Path.Direction.CCW);
        setMeasuredDimension(widthSize, heightSize);
    }

    /**
     * 绘制
     *
     * @param canvas 画布
     */
    @Override
    public void draw(Canvas canvas) {
        //裁剪画布，并设置其填充方式
        if (Build.VERSION.SDK_INT >= 26) {
            canvas.clipPath(clipPath);
        } else {
            canvas.clipPath(clipPath, Region.Op.REPLACE);
        }
        super.draw(canvas);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        //可以去打开相机
        Log.d("__surfaceView-Created","1");
    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.d("__surfaceView-Changed","1");

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        //可以去关闭相机
        Log.d("__surfaceView-destroy","1");
    }
}
