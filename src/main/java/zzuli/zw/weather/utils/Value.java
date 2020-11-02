package zzuli.zw.weather.utils;

import zzuli.zw.weather.factory.BeanFactory;

public class Value {
    public static String getValue(String name){
        System.out.println(BeanFactory.getApplication().getProperty(name));
        return BeanFactory.getApplication().getProperty(name);
    }
}
