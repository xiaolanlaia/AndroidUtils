package com.wjf.moduledesignpattern.actionType.visitor.exam;

import android.util.Log;

import java.io.File;

public class ClassFileVisitor implements VisitorInterface {
    @Override
    public void visitDir(File file) {

    }

    @Override
    public void visitFile(File file) {
        if (file.getName().endsWith(".class")){
            Log.d("__ClassFileVisitor","file = "+file);
        }
    }
}
