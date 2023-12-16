package com.wjf.moduleutils;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.io.File;
import java.lang.reflect.Field;

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/11/15 10:42
 */
@SuppressLint("Range")
public class Test {

    void test() {
//        MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(v.getContext(), "myDB", null, 10);
    }

    private void testShareMedia() {
        //获取目录：/storage/emulated/0/
        File rootFile = Environment.getExternalStorageDirectory();
        // /storage/emulated/0/Pictures/myPic.png
        String imagePath = rootFile.getAbsolutePath() + File.separator + Environment.DIRECTORY_PICTURES + File.separator + "myPic.png";
        Log.d("__imagePath",imagePath);
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
    }

    private void getImagePath(Context context) {
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        while(cursor.moveToNext()) {
            String imagePath = cursor.getString(cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA));
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            break;
        }
    }

    public static String setRootDir() {
        String rootPath = "";
//        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        rootPath = UtilsConstant.utilsContext.getExternalFilesDir(null) + "/setRootDir/";
//        rootPath = Environment.getDataDirectory() + "/setRootDir/";
        Log.d("__setRootDir",rootPath + "result = "+createRootDir(rootPath));
        createRootDir(rootPath);
        return rootPath;
    }

    /**
     * 创建文件夹
     **/
    public static boolean createRootDir(String rootPath) {
        File dirRoot = new File(rootPath);
        if (!dirRoot.exists() || !dirRoot.isDirectory()) {
            boolean isCreateRoot = dirRoot.mkdirs();
            return isCreateRoot;
        }
        return true;
    }


    public static void  deleteMedieFile(File file){
        if (file.isFile()) {
            String filePath = file.getPath();
            if(filePath.endsWith(".mp4")){
                int res = UtilsConstant.utilsContext.getContentResolver().delete(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                        MediaStore.Audio.Media.DATA + "= \"" + filePath+"\"",
                        null);
                if (res>0){
                    file.delete();
                }else{
                }
            }else if (filePath.endsWith(".jpg")||filePath.endsWith(".png")||filePath.endsWith(".bmp")){
                int res = UtilsConstant.utilsContext.getContentResolver().delete(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        MediaStore.Audio.Media.DATA + "= \"" + filePath+"\"",
                        null);
                if (res>0){
                    file.delete();
                }
            }else{
                file.delete();
            }
            //删除多媒体数据库中的数据
            return;
        }



    }


    public static void setTabWidth(final TabLayout tabLayout, final int padding) {
        tabLayout.post(() -> {
            try {
                //拿到tabLayout的mTabStrip（低版本）/slidingTabIndicator（高版本）属性
                LinearLayout mTabStrip = (LinearLayout) tabLayout.getChildAt(0);

                for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                    View tabView = mTabStrip.getChildAt(i);

                    //这里需要根据源码版本做一个判断
                    String text;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        text = "textView";
                    } else {
                        text = "mTextView";
                    }
                    //拿到tabView的mTextView属性 tab的字数不固定一定用反射取mTextView
                    Field mTextViewField = tabView.getClass().getDeclaredField(text);
                    mTextViewField.setAccessible(true);

                    TextView mTextView = (TextView) mTextViewField.get(tabView);

                    tabView.setPadding(0, 0, 0, 0);

                    //字多宽线就多宽
                    int width = 0;
                    width = mTextView.getWidth();
                    if (width == 0) {
                        mTextView.measure(0, 0);
                        width = mTextView.getMeasuredWidth();
                    }

                    //设置tab左右间距 注意这里不能使用Padding 因为源码中线的宽度是根据 tabView的宽度来设置的
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                    params.width = width;
                    params.leftMargin = padding;
                    params.rightMargin = padding;
                    tabView.setLayoutParams(params);

                    tabView.invalidate();
                }

            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });

    }




}
