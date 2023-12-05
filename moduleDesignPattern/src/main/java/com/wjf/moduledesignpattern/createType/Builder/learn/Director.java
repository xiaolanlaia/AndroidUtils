package com.wjf.moduledesignpattern.createType.Builder.learn;

public class Director {
    Builder builder = null;
    public Director(Builder builder){
        this.builder = builder;
    }

    public void construct(String board,String display){
        builder.builderBoard(board);
        builder.builderDiaplay(display);
        builder.builderOS();
    }
}
