package com.tool.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestController {

    public static void main(String[] args) throws Exception {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        pattern = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String date = sdf.format(new Date());
        System.out.println(date.substring(0,4));
        System.out.println(date.substring(5));

    }

}
