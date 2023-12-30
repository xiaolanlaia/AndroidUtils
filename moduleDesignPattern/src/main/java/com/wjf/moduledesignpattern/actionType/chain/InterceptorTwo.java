package com.wjf.moduledesignpattern.actionType.chain;

public class InterceptorTwo implements InterceptorCallBack {


    @Override
    public String handle(Chain chain) {
        String request = chain.getRequest();
        String result = request + "我Leader2处理过了，传给下一个拦截器";
        String response = chain.proceed(result);
        return response;
    }
}
