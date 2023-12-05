package com.wjf.moduledesignpattern.createType.Clone;

import android.util.Log;

import java.util.ArrayList;

public class WordDocument implements Cloneable {
    private String mText;
    private ArrayList<String> images = new ArrayList<>();
    private ArrayList<Integer> indexs = new ArrayList<>();

    public WordDocument(){
        Log.d("__WordDocument","WordDocument");
    }

    @Override
    public Object clone() {
        try {
            WordDocument document = (WordDocument)super.clone();
            document.mText = this.mText;
            //浅拷贝
            document.images = this.images;
            document.indexs = this.indexs;
            //深拷贝
            document.images = (ArrayList<String>) this.images.clone();
            document.indexs = (ArrayList<Integer>) this.indexs.clone();
            return document;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Integer> getIndexs() {
        return indexs;
    }

    public void setIndexs(ArrayList<Integer> indexs) {
        this.indexs = indexs;
    }

    public void addIndex(int index){
        this.indexs.add(index);
    }

    public String getmText() {
        return mText;
    }

    public void setmText(String mText) {
        this.mText = mText;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public void addImage(String image){
        this.images.add(image);
    }

    public void showDocument(){
        Log.d("__showDocument-start","---------------------------\r\n");
        Log.d("__showDocument-1","Text = "+mText);
        for (String image : images){
            Log.d("__showDocument-image","image = "+image);
        }

        for (int index : indexs){
            Log.d("__showDocument-index","index = "+index);

        }

        Log.d("__showDocument-end","---------------------------\r\n");
    }
}
