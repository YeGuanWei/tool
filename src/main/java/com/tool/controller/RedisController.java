package com.tool.controller;

import com.tool.utils.RedisUtil;

/**
 * Redis
 * 注意，这里需要在yml文件中指定端口
 */
public class RedisController {

    private static RedisUtil redisUtil;

    public static void main(String[] args) throws Exception {

    }

    private static void set(){
        redisUtil.set("key","value");
    }

    private static void get(String key){
        redisUtil.get(key);
    }


}
