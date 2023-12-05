package com.wjf.moduledesignpattern.actionType.visitor.exam;

import java.io.File;

public interface VisitorInterface {
    void visitDir(File file);
    void visitFile(File file);
}
