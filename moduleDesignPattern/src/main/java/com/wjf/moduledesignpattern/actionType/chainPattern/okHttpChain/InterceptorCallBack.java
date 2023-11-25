package com.wjf.moduledesignpattern.actionType.chainPattern.okHttpChain;

public interface InterceptorCallBack {
    String handle(Chain chain);

    interface Chain {
        String proceed(String requestStr);
        String getRequest();
    }
}
