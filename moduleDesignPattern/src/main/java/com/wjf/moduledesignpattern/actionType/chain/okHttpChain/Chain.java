package com.wjf.moduledesignpattern.actionType.chain.okHttpChain;

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/5 10:11
 */

interface Chain {

    String proceed(String requestStr);

    String getRequest();
}
