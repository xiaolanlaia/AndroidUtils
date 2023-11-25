package com.wjf.moduledesignpattern.createType.BuilderPattern.learn;

public class MacBuilder extends Builder{
    private Computer mComputer = new MacBook();
    @Override
    public void builderBoard(String board) {
        mComputer.setmBoard(board);
    }

    @Override
    public void builderDiaplay(String diaplay) {
        mComputer.setmDisplay(diaplay);
    }

    @Override
    public void builderOS() {
        mComputer.setmOS();
    }

    @Override
    public Computer create() {
        return mComputer;
    }
}
