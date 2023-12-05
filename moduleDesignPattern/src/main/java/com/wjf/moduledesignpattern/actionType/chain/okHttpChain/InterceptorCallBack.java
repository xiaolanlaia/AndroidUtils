package com.wjf.moduledesignpattern.actionType.chain.okHttpChain;

public interface InterceptorCallBack {
    String handle(Chain chain);

    interface Chain {
        String proceed(String requestStr);
        String getRequest();
    }
}
