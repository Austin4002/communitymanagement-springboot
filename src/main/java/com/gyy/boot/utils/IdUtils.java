package com.gyy.boot.utils;

import java.util.UUID;

public class IdUtils {
    //生成uuid方法
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}
