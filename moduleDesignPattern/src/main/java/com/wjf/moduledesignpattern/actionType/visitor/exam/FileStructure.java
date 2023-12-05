package com.wjf.moduledesignpattern.actionType.visitor.exam;

import java.io.File;

public class FileStructure {
    private File path;
    public FileStructure(File path){
        this.path = path;
    }

    private void scan(File file, VisitorInterface visitorInterface){
        if (file.isDirectory()){
            visitorInterface.visitDir(file);
            for (File sub : file.listFiles()){
                scan(sub,visitorInterface);
            }
        }else if (file.isFile()){
            visitorInterface.visitFile(file);
        }
    }
}
