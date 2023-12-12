package com.wjf.moduleutils;

import static android.content.Context.WINDOW_SERVICE;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * 使用 ScreenAdaptation(this, 1920f).register()
 */
public class ScreenAdaptUtils {


    private  Application.ActivityLifecycleCallbacks activityLifecycleCallbacks;
    private  Application mApplication;
    private  float mWidth;


    public ScreenAdaptUtils(Application application, float width) {
        mApplication = application;
        mWidth = width;

        activityLifecycleCallbacks = new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                //开启Activity才执行
                resetDensity(activity, mWidth);
            }

            @Override
            public void onActivityStarted(Activity activity) {
            }

            @Override
            public void onActivityResumed(Activity activity) {
            }

            @Override
            public void onActivityPaused(Activity activity) {
            }

            @Override
            public void onActivityStopped(Activity activity) {
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
            }
        };
    }

    /**
     * 注册
     */
    public void register(){
        resetDensity(mApplication, mWidth);
        mApplication.registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
    }

    /**
     * 注销
     */
    public void unregister(){
        //设置为默认
        mApplication.getResources().getDisplayMetrics().setToDefaults();
        mApplication.unregisterActivityLifecycleCallbacks(activityLifecycleCallbacks);
    }

    /**
     * dp适配 getResources().getDisplayMetrics().density
     * sp适配 getResources().getDisplayMetrics().scaledDensity
     * pt适配 getResources().getDisplayMetrics().xdpi
     * @param context
     * @param width ui设计图的宽度
     */
    private static void resetDensity(Context context, float width){
        Point point = new Point();
        //获取屏幕的数值
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        ((WindowManager)context.getSystemService(WINDOW_SERVICE)).getDefaultDisplay().getSize(point);
        float newDensity = dm.widthPixels / width;
        float newScaledDensity =  newDensity * (dm.density / dm.scaledDensity);
        int newDensityDpi =  (int) (newDensity * DisplayMetrics.DENSITY_DEFAULT);
        dm.density = newDensity;
        dm.scaledDensity = newScaledDensity;
        dm.densityDpi = newDensityDpi;
    }
}
