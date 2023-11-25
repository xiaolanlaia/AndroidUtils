package com.wjf.moduledesignpattern.actionType.chainPattern.okHttpChain;

import java.util.ArrayList;

public class RealInterceptorChain implements InterceptorCallBack.Chain {
    private ArrayList<InterceptorCallBack> interceptorList = new ArrayList<>();
    private int index = 0;
    private String lastResponse = "";

    public void addInterceptor(InterceptorCallBack interceptorCallBack){
        interceptorList.add(interceptorCallBack);
    }

    public void removeInterceptor(InterceptorCallBack leader){
        interceptorList.remove(leader);
    }

    @Override
    public String proceed(String requestStr) {
        lastResponse = requestStr;
        if (interceptorList == null || interceptorList.size() == 0) return lastResponse;
        if (index >= interceptorList.size()) return lastResponse;
        InterceptorCallBack nextInterceptor = interceptorList.get(index);
        index ++;

        lastResponse = nextInterceptor.handle(this);
        return lastResponse;
    }

    @Override
    public String getRequest() {
        return lastResponse;
    }
}
