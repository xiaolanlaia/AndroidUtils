package com.wjf.moduledesignpattern.actionType.chain.okHttpChain;

public class InterceptorOne implements InterceptorCallBack{
    @Override
    public String handle(Chain chain) {
        String request = chain.getRequest();
        String result = request + "传给下一个拦截器";
        String response = chain.proceed(result);
        return response;
    }
}
