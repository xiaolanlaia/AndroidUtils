package com.wjf.moduledesignpattern.createType.Factory.reflect;

import java.util.HashMap;
import java.util.Map;

public class SimpleFactoryReflection {
    public enum EnumProductType {
        activityOne,activityTwo
    }

    private static final Map<EnumProductType,Class>activityIdMap = new HashMap<>();

    public static void addProductKey(EnumProductType enumProductType,Class product){
        activityIdMap.put(enumProductType,product);
    }
    public static Product product(EnumProductType type) throws IllegalAccessException,
            InstantiationException{
        Class productClass = activityIdMap.get(type);
        return (Product) productClass.newInstance();
    }
}
