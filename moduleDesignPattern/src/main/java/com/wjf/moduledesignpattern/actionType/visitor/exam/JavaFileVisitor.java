package com.wjf.moduledesignpattern.actionType.visitor.exam;

import android.util.Log;

import java.io.File;

public class JavaFileVisitor implements VisitorInterface {
    @Override
    public void visitDir(File dir) {
        Log.d("__JavaFileVisitor","dir = "+dir);

    }

    @Override
    public void visitFile(File file) {
        if (file.getName().endsWith(".java")){
            Log.d("__JavaFileVisitor","file = "+file);
        }
    }
}
